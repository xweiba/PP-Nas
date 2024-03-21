package pp.weiba.thirdparty.baidu.web.api.netdisk;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.*;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.FileChunk;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.FileDuplicateDetectionRequest;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.*;
import pp.weiba.thirdparty.baidu.web.api.netdisk.utils.BaiduNetDiskWebScript;
import pp.weiba.utils.FileUtils;
import pp.weiba.utils.JSONUtils;
import pp.weiba.utils.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件传输客户端
 *
 * @author weiba
 * @date 2024/3/20 9:41
 */
@Log4j2
public class UploadFileApiClient extends AbstractApiHttpClient {

    /* 4Mb */
    public static final Integer UPLOAD_FILE_CHUNK_SIZE = 4194304;

    /* 256Kb */
    public static final Integer UPLOAD_FILE_RAPID_CHUNK_SIZE = 262144;

    /* 超过4Mb 使用次标识，分片上传 */
    public static final String SPLIT_PART_UPLOAD_FILE_BLOCKLIST_4MB_MARK = "[\"5910a591dd8fc18c32a8f3df4fdc1761\",\"a5fc157d78e6ad1c7e114b056c92821e\"]";

    /* 未超过4Mb 使用标识 */
    public static final String UPLOAD_FILE_BLOCKLIST_MARK = "[\"5910a591dd8fc18c32a8f3df4fdc1761\"]";
    private static final String fastestUploadPcsServiceTagSplit = "@-@";
    private static String fastestUploadPcsServiceTag = "";

    public UploadFileApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 新增文件
     *
     * @param dstDirPath : dstDirPath 目标文件夹
     * @param file       : file 新增文件
     * @return weiba.pp.components.netdisc.bd.web.bo.ResponseAddFileBO
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/11 14:21
     */
    public CreateFileResponse createFile(String dstDirPath, String uk, File file) {
        if (FileUtil.isEmpty(file) || StrUtil.isEmpty(uk) || StrUtil.isBlank(dstDirPath) || file.isFile()) {
            throw new IllegalArgumentException("uk || file || dirPath 不能为空且必须是文件！");
        }

        long fileSize = file.length();

        if (!dstDirPath.endsWith("/")) dstDirPath += "/";
        String dstFilePath = dstDirPath + file.getName();
        Date lastModifiedTime = FileUtil.lastModifiedTime(file);

        // 1. 秒传检测， 404 即文件不存在, 小于 262144 不检测
        if (fileSize >= UPLOAD_FILE_RAPID_CHUNK_SIZE) {
            FileDuplicateDetectionResponse duplicateDetectionResponse = uploadFileDuplicateCheck(dstFilePath, dstDirPath, file, uk);
            // 秒传返回
            if (duplicateDetectionResponse != null && duplicateDetectionResponse.getErrno() != null && duplicateDetectionResponse.getErrno() == 0) {
                log.debug("文件已存在，忽略上传！ dstDirPath: {}, file: {} uk: {}", dstDirPath, file.toString(), uk);
                CreateFileResponse addFile = BeanUtil.toBean(duplicateDetectionResponse.getInfo(), CreateFileResponse.class);
                addFile.setErrno(0);
                addFile.setName(addFile.getPath().split(dstDirPath)[1]);
                return addFile;
            }
        }

        // 2， 文件预创建
        PreCreateFileResponse addFilePre = preCreateFile(dstFilePath, dstDirPath, fileSize, lastModifiedTime);

        // 3. 文件分片上传
        List<String> blockList = uploadFile(addFilePre.getUploadid(), file, fileSize, dstFilePath, dstDirPath).stream().map(responseUploadFileBO -> responseUploadFileBO.getMd5()).collect(Collectors.toList());

        // 4. 创建文件
        return completeCreateFile(addFilePre.getUploadid(), dstFilePath, dstDirPath, lastModifiedTime, fileSize, blockList);
    }

    public FileDuplicateDetectionResponse uploadFileDuplicateCheck(FileDuplicateDetectionRequest checkUploadDuplicateInfo) {

        // rtype=3覆盖文件, rtype=0则返回报错, 不覆盖文件, 默认为rtype=1 (自动重命名, 1和2是两种不同的重命名策略)
        HashMap<String, Object> requestParams = new HashMap<String, Object>() {{
            put("path", checkUploadDuplicateInfo.getDstFilePath());
            put("content-length", checkUploadDuplicateInfo.getFileSize());
            put("content-md5", checkUploadDuplicateInfo.getFileMd5());
            put("slice-md5", checkUploadDuplicateInfo.getSliceMd5());
            put("rtype", 1);
        }};
        // -8 文件已存在 rtype=0时会报错
        if (checkUploadDuplicateInfo.getDataOffset() != 0 || checkUploadDuplicateInfo.getDataTime() != 0 && StringUtils.isNotBlank(checkUploadDuplicateInfo.getDataContentBase64())) {
            requestParams.put("target_path", checkUploadDuplicateInfo.getDstDirPath());
            requestParams.put("local_mtime", checkUploadDuplicateInfo.getUpdateTime());
            requestParams.put("data_time", checkUploadDuplicateInfo.getDataTime());
            requestParams.put("data_offset", checkUploadDuplicateInfo.getDataOffset());
            requestParams.put("data_content", checkUploadDuplicateInfo.getDataContentBase64());
        }
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, UrlConstants.POST_UPLOAD_FILE_DUPLICATE_CHECK).requestParams(requestParams).setContentType("text/plain;charset=UTF-8").addheader("User-Agent", "netdisk;");

        return execute(httpRequest, new TypeReference<FileDuplicateDetectionResponse>() {
        });
    }

    /**
     * 文件预创建
     *
     * @param dstFilePath      : disFilePath
     * @param dstDirPath       : dstDirPath
     * @param fileSize         : fileSize
     * @param lastModifiedTime : fileChangeEndTime
     * @return weiba.pp.components.netdisc.bd.web.bo.ResponseAddFilePreBO
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/11 14:32
     */
    public PreCreateFileResponse preCreateFile(String dstFilePath, String dstDirPath, long fileSize, Date lastModifiedTime) {
        return postExecute(UrlConstants.POST_PRE_UPLOAD_FILE, new HashMap<String, Object>() {{
            put("path", dstFilePath);
            put("autoinit", 1);
            put("target_path", dstDirPath);
            put("local_mtime", tranLastModifiedTime(lastModifiedTime));
            put("block_list", fileSize <= UPLOAD_FILE_CHUNK_SIZE ? UPLOAD_FILE_BLOCKLIST_MARK : SPLIT_PART_UPLOAD_FILE_BLOCKLIST_4MB_MARK);
        }}, new TypeReference<PreCreateFileResponse>() {
        });
    }

    /**
     * 文件分片上传
     *
     * @param uploadId    : uploadId 预上传id
     * @param file        : file
     * @param fileSize    : fileSize
     * @param dstFilePath : dstFilePath 目标路径，不分片时使用
     * @param dstDirPath  : dstDirPath 目标文件夹，分片时使用
     * @return java.util.List<weiba.pp.components.netdisc.bd.web.bo.ResponseUploadFileBO>
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/11 15:31
     */
    private List<BaiduNetDiskWebUploadFileResponse> uploadFile(String uploadId, File file, Long fileSize, String dstFilePath, String dstDirPath) {
        List<BaiduNetDiskWebUploadFileResponse> responseUploadFiles = new ArrayList<>();
        if (fileSize <= UPLOAD_FILE_CHUNK_SIZE) {
            BaiduNetDiskWebUploadFileResponse uploadBO = multiPartUpload(uploadId, file, dstFilePath);
            responseUploadFiles.add(uploadBO);
        } else {
            try {
                int partseq = 0;
                BufferedInputStream inputStream = FileUtil.getInputStream(file);
                int fileOverLength = inputStream.available();
                int blockSize = UPLOAD_FILE_CHUNK_SIZE;
                byte[] partialByte = new byte[blockSize];
                while (fileOverLength > 0) {
                    if (fileOverLength - blockSize < 0) {
                        blockSize = fileOverLength;
                        partialByte = new byte[blockSize];
                    }
                    inputStream.read(partialByte, 0, blockSize);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(partialByte);

                    int finalBlockSize = blockSize;
                    log.debug(() -> StrUtil.format("blockSize:{} md5: {}", finalBlockSize, SecureUtil.md5(byteArrayInputStream)));

                    // 可尝试开线程上传
                    BaiduNetDiskWebUploadFileResponse uploadBO = multiPartUpload(uploadId, new InputStreamResource(new ByteArrayInputStream(partialByte), file.getName()), dstDirPath, partseq++);
                    responseUploadFiles.add(uploadBO);
                    fileOverLength -= blockSize;
                }
            } catch (IOException e) {
                log.error("文件分片上传失败！Exception：{}", ExceptionUtil.getMessage(e));
                throw new RuntimeException("文件分片上传失败！");
            }
        }
        return responseUploadFiles;
    }


    public List<FileChunk> generateFileChunks(File file) {
        List<FileChunk> chunkList = new ArrayList<>();
        long fileLength = file.length();
        long chunkCount = (fileLength + UPLOAD_FILE_CHUNK_SIZE - 1) / UPLOAD_FILE_CHUNK_SIZE;
        for (long i = 0; i < chunkCount; i++) {
            long start = i * UPLOAD_FILE_CHUNK_SIZE;
            long length = Math.min(fileLength - start, UPLOAD_FILE_CHUNK_SIZE);
            chunkList.add(new FileChunk(start, length, (int) i, null));
        }
        return chunkList;
    }

    // 修改后的方法，用于根据分片信息即时读取并上传数据
    public BaiduNetDiskWebUploadFileResponse shardResourceUpload(String uploadId, File file, FileChunk chunk, String dstDirPath) {
        if (file.length() <= UPLOAD_FILE_CHUNK_SIZE) {
            String dstFilePath = dstDirPath + file.getName();
            return multiPartUpload(uploadId, file, dstFilePath);
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            byte[] buffer = new byte[(int) chunk.getLength()];
            randomAccessFile.seek(chunk.getStart());
            randomAccessFile.readFully(buffer);

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer)) {
                log.debug(() -> StrUtil.format("Chunk size: {}, MD5: {}", chunk.getLength(), SecureUtil.md5(byteArrayInputStream)));
                return multiPartUpload(uploadId, new InputStreamResource(byteArrayInputStream, file.getName()), dstDirPath, chunk.getPartSeq());
            }
        } catch (IOException e) {
            log.error("文件分片上传失败！Exception：{}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("文件分片上传失败！", e);
        }
    }

    /**
     * 文件上传
     *
     * @param uploadid   : uploadid
     * @param file       : file
     * @param dstDirPath : disFilePath
     * @param partseq    : partseq 分片序号
     * @return weiba.pp.components.netdisc.bd.web.bo.ResponseUploadFileBO
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/11 15:32
     */
    public BaiduNetDiskWebUploadFileResponse multiPartUpload(String uploadid, Object file, String dstDirPath, int partseq) {
        return postExecute(StrUtil.format(UrlConstants.POST_UPLOAD_FILE, getFastestUploadPCSServiceUrl(), dstDirPath, uploadid, partseq), new HashMap<String, Object>() {{
            put("file", file);
        }}, new TypeReference<BaiduNetDiskWebUploadFileResponse>() {
        });
    }

    public BaiduNetDiskWebUploadFileResponse multiPartUpload(String uploadid, String dstDirPath, File file, FileChunk chunk) {
        UploadFile uploadFile = new UploadFile().setFile(file);
        int partseq = 0;
        if (chunk != null) {
            partseq = chunk.getPartSeq();
            uploadFile.setChunk(new UploadFileChunk(chunk.getStart(), chunk.getLength(), partseq));
        }
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, StrUtil.format(UrlConstants.POST_UPLOAD_FILE, getFastestUploadPCSServiceUrl(), dstDirPath, uploadid, partseq)).setUploadFile(uploadFile);
        return execute(httpRequest, new TypeReference<BaiduNetDiskWebUploadFileResponse>() {
        });
    }

    /**
     * 创建文件信息
     *
     * @param dstFilePath       : disFilePath
     * @param dstDirPath        : dstDirPath
     * @param file              : file
     * @param fileSize          : fileSize
     * @param fileChangeEndTime : fileChangeEndTime
     * @param uploadId          : uploadId
     * @param blockList         : blockList 文件分片md集合
     * @return weiba.pp.components.netdisc.bd.web.bo.ResponseAddFileBO
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/11 15:36
     */
    public CreateFileResponse completeCreateFile(String uploadId, String dstFilePath, String dstDirPath, Date lastModifiedTime, long fileSize, List<String> blockList) {
        return postExecute(UrlConstants.POST_COMPLETE_CREATE_FILE, new HashMap<String, Object>() {{
            put("path", dstFilePath);
            put("size", fileSize);
            put("uploadid", uploadId);
            put("block_list", JSONUtils.toJsonStr(blockList));
            put("target_path", dstDirPath);
            put("local_mtime", tranLastModifiedTime(lastModifiedTime));
        }}, new TypeReference<CreateFileResponse>() {
        });
    }

    /**
     * 查询文件是否已上传过（秒传查询）
     *
     * @param dstFilePath       : disFilePath
     * @param dstDirPath        : dstDirPath
     * @param file              : file
     * @param fileSize          : fileSize
     * @param fileChangeEndTime : fileChangeEndTime
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/11 14:33
     */
    public FileDuplicateDetectionResponse uploadFileDuplicateCheck(String dstFilePath, String dstDirPath, File file, String uk) {
        return uploadFileDuplicateCheck(buildFileDuplicateDetectionRequest(dstFilePath, dstDirPath, file, uk));
    }

    public BaiduNetDiskWebUploadFileResponse multiPartUpload(String uploadid, File file, String disFilePath) {
        return multiPartUpload(uploadid, file, disFilePath, 0);
    }

    public FileDuplicateDetectionRequest buildFileDuplicateDetectionRequest(String dstFilePath, String dstDirPath, File file, String uk) {
        int fileChangeEndTime = (int) (FileUtil.lastModifiedTime(file).getTime() / 1000);
        long fileSize = file.length();
        String fileMd5 = BaiduNetDiskWebScript.encryptMd5(SecureUtil.md5(file));
        String sliceMd5 = BaiduNetDiskWebScript.encryptMd5(FileUtils.getSliceMd5(file, UPLOAD_FILE_RAPID_CHUNK_SIZE));

        int dataTime = (int) Math.floor(new Date().getTime() / 1000);
        int dataOffset = getDataOffset(uk, fileSize, dataTime, fileMd5);
        String dataContentBase64 = FileUtils.encryptToBase64(FileUtils.getSliceFile(file, dataOffset, UPLOAD_FILE_RAPID_CHUNK_SIZE));

        return FileDuplicateDetectionRequest.builder().dstFilePath(dstFilePath).dstDirPath(dstDirPath).fileSize(fileSize).updateTime(fileChangeEndTime).fileMd5(fileMd5).sliceMd5(sliceMd5).dataTime(dataTime).dataOffset(dataOffset).dataContentBase64(dataContentBase64).build();
    }

    private int getDataOffset(String uk, long fileSize, int dataTime, String fileMd5) {
        String makeMD5Source = uk + fileMd5 + dataTime;
        return BaiduNetDiskWebScript.getDataOffset(makeMD5Source, String.valueOf(fileSize)).intValue();
    }

    private Long tranLastModifiedTime(Date uploadTim) {
        return uploadTim.getTime() / 1000;
    }

    public String getFastestUploadPCSServiceUrl() {
        int hour = DateUtil.hour(new Date(), true);
        if (StrUtil.isBlank(fastestUploadPcsServiceTag) || (Math.abs((Integer.valueOf(fastestUploadPcsServiceTag.split(fastestUploadPcsServiceTagSplit)[1])) - hour) > 6)) {
            synchronized (UrlConstants.GET_QUERY_UPLOAD_FILE_SERVICES) {
                HttpResponse execute = executeResponse(UrlConstants.GET_QUERY_UPLOAD_FILE_SERVICES);
                PSCUploadFileServerResponse baiduUploadPSCInfoBO = JSONUtils.toBean(execute.getBody(), PSCUploadFileServerResponse.class);

                long time = 0L;
                String serverUrl = "";
                // 选择延时最低的上传节点
                for (String serverUrlTemp : baiduUploadPSCInfoBO.getServer()) {
                    long start = System.currentTimeMillis();
                    executeResponse(serverUrlTemp);
                    long timeTemp = System.currentTimeMillis() - start;
                    log.debug("当前上传节点：{}， 延时：{}", serverUrlTemp, timeTemp);
                    if (time != 0 && timeTemp > time) {
                        continue;
                    }
                    serverUrl = serverUrlTemp;
                    time = timeTemp;
                }
                fastestUploadPcsServiceTag = "https://" + serverUrl + fastestUploadPcsServiceTagSplit + hour;
            }
        }
        return fastestUploadPcsServiceTag.split(fastestUploadPcsServiceTagSplit)[0];
    }
}
