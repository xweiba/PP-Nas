package pp.weiba.thirdparty.baidu.web.api.netdisk;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.model.*;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.CheckFileExistRequest;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.FileChunk;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.*;
import pp.weiba.thirdparty.baidu.web.api.netdisk.utils.BaiduNetDiskWebScript;
import pp.weiba.utils.FileUtils;
import pp.weiba.utils.JSONUtils;
import pp.weiba.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 文件上传客户端
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

    /* 缓存上传服务器 */
    private static final String fastestUploadPcsServiceTagSplit = "@-@";
    private static String fastestUploadPcsServiceTag = "";

    public UploadFileApiClient(IHttpClient httpClient) {
        super(httpClient);
    }


    /**
     * 检测服务器是否存在准备上传的文件（秒传）
     *
     * @param checkFileExistRequest 检测参数
     * @return 返回服务器上对应的文件信息
     * @author weiba
     * @date 2024/3/25 10:48
     */

    public CheckFileExistResponse checkFileExist(CheckFileExistRequest checkFileExistRequest) {

        // rtype=3覆盖文件, rtype=0则返回报错, 不覆盖文件, 默认为rtype=1 (自动重命名, 1和2是两种不同的重命名策略)
        HashMap<String, Object> requestParams = new HashMap<String, Object>() {{
            put("path", checkFileExistRequest.getDstFilePath());
            put("content-length", checkFileExistRequest.getFileSize());
            put("content-md5", checkFileExistRequest.getFileMd5());
            put("slice-md5", checkFileExistRequest.getSliceMd5());
            put("rtype", 1);
        }};
        // -8 文件已存在 rtype=0时会报错
        if (checkFileExistRequest.getDataOffset() != 0 || checkFileExistRequest.getDataTime() != 0 && StringUtils.isNotBlank(checkFileExistRequest.getDataContentBase64())) {
            requestParams.put("target_path", checkFileExistRequest.getDstDirPath());
            requestParams.put("local_mtime", checkFileExistRequest.getUpdateTime());
            requestParams.put("data_time", checkFileExistRequest.getDataTime());
            requestParams.put("data_offset", checkFileExistRequest.getDataOffset());
            requestParams.put("data_content", checkFileExistRequest.getDataContentBase64());
        }
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, UrlConstants.POST_UPLOAD_FILE_DUPLICATE_CHECK).requestParams(requestParams).setContentType("text/plain;charset=UTF-8").addheader("User-Agent", "netdisk;");

        return execute(httpRequest, new TypeReference<CheckFileExistResponse>() {
        });
    }

    /**
     *
     *
     * @param dstFilePath 目标文件路径
     * @param dstDirPath 目标文件夹
     * @param fileSize  文件大小
     * @param lastModifiedTime 文件最后修改时间
     * @return 返回预创建信息 uploadid
     * @author weiba
     * @date 2024/3/25 10:50
     */
    public PreCreateFileResponse preCreateFile(String dstFilePath, String dstDirPath, long fileSize, Date lastModifiedTime) {
        if (StrUtil.isBlank(dstFilePath) || StrUtil.isBlank(dstDirPath) || fileSize <= 0 || lastModifiedTime == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
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
     * 将文件分片
     *
     * @param file 文件信息
     * @return 文件分片信息，不需要分片的返回null
     * @author weiba
     * @date 2024/3/25 10:52
     */
    public List<FileChunk> buildFileChunks(File file) {
        List<FileChunk> chunkList = null;
        long fileLength = file.length();
        if (fileLength > UPLOAD_FILE_CHUNK_SIZE) {
            chunkList = new ArrayList<>();
            long chunkCount = (fileLength + UPLOAD_FILE_CHUNK_SIZE - 1) / UPLOAD_FILE_CHUNK_SIZE;
            for (long i = 0; i < chunkCount; i++) {
                long start = i * UPLOAD_FILE_CHUNK_SIZE;
                long length = Math.min(fileLength - start, UPLOAD_FILE_CHUNK_SIZE);
                chunkList.add(new FileChunk(start, length, (int) i, null));
            }
        }

        return chunkList;
    }

    /**
     * 分片文件上传
     *
     * @param uploadid 上传id
     * @param dstDirPath 目标文件夹路径
     * @param file 文件信息
     * @param chunk 文件分片信息，分片信息为空表示文件不需要分片，直接上传整个文件
     * @return 文件上传成功返回信息，文件md5
     * @author weiba
     * @date 2024/3/25 10:53
     */
    public UploadFileChunkResponse uploadFileChunk(String uploadid, String dstDirPath, File file, FileChunk chunk) {
        UploadFile uploadFile = new UploadFile().setFile(file);
        int partseq = 0;
        if (chunk != null) {
            partseq = chunk.getPartSeq();
            uploadFile.setChunk(new UploadFileChunk(chunk.getStart(), chunk.getLength(), partseq));
        }
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, StrUtil.format(UrlConstants.POST_UPLOAD_FILE, getFastestUploadPCSServiceUrl(), dstDirPath, uploadid, partseq)).setUploadFile(uploadFile).addheader("Accept", "*");
        return execute(httpRequest, new TypeReference<UploadFileChunkResponse>() {
        });
    }

    /**
     * 完成文件上传
     *
     * @param uploadId 上传id
     * @param dstFilePath 目标文件路径
     * @param dstDirPath 目标文件夹
     * @param lastModifiedTime 文件最后修改时间
     * @param fileSize 文件大小
     * @param chunkDataMd5s 分片md5集合
     * @return 文件上传完成信息
     * @author weiba
     * @date 2024/3/25 10:56
     */
    public CompleteUploadFileResponse completeUploadFile(String uploadId, String dstFilePath, String dstDirPath, Date lastModifiedTime, long fileSize, List<String> chunkDataMd5s) {
        return postExecute(UrlConstants.POST_COMPLETE_CREATE_FILE, new HashMap<String, Object>() {{
            put("path", dstFilePath);
            put("size", fileSize);
            put("uploadid", uploadId);
            put("block_list", JSONUtils.toJsonStr(chunkDataMd5s));
            put("target_path", dstDirPath);
            put("local_mtime", tranLastModifiedTime(lastModifiedTime));
        }}, new TypeReference<CompleteUploadFileResponse>() {
        });
    }

    public CheckFileExistResponse checkFileExist(String dstFilePath, String dstDirPath, File file, String uk) {
        return checkFileExist(buildCheckFileExistRequest(dstFilePath, dstDirPath, file, uk));
    }

    public CheckFileExistRequest buildCheckFileExistRequest(String dstFilePath, String dstDirPath, File file, String uk) {
        int fileChangeEndTime = (int) (FileUtil.lastModifiedTime(file).getTime() / 1000);
        long fileSize = file.length();
        String fileMd5 = BaiduNetDiskWebScript.encryptMd5(SecureUtil.md5(file));
        String sliceMd5 = BaiduNetDiskWebScript.encryptMd5(FileUtils.getSliceMd5(file, UPLOAD_FILE_RAPID_CHUNK_SIZE));

        int dataTime = (int) Math.floor(tranLastModifiedTime(new Date()));
        int dataOffset = getDataOffset(uk, fileSize, dataTime, fileMd5);
        String dataContentBase64 = FileUtils.encryptToBase64(FileUtils.getSliceFile(file, dataOffset, UPLOAD_FILE_RAPID_CHUNK_SIZE));

        return CheckFileExistRequest.builder().dstFilePath(dstFilePath).dstDirPath(dstDirPath).fileSize(fileSize).updateTime(fileChangeEndTime).fileMd5(fileMd5).sliceMd5(sliceMd5).dataTime(dataTime).dataOffset(dataOffset).dataContentBase64(dataContentBase64).build();
    }


    /**
     * 根据用户uk和时间及文件指定部分md5来计算偏移量
     *
     * @param uk       用户id
     * @param fileSize 文件总大小
     * @param dataTime 当前时间/1000
     * @param fileMd5  文件前256Kb的md5
     * @return 偏移量
     * @author weiba
     * @date 2024/3/25 10:45
     */
    private int getDataOffset(String uk, long fileSize, int dataTime, String fileMd5) {
        String makeMD5Source = uk + fileMd5 + dataTime;
        return BaiduNetDiskWebScript.getDataOffset(makeMD5Source, String.valueOf(fileSize)).intValue();
    }

    private Long tranLastModifiedTime(Date uploadTim) {
        return uploadTim.getTime() / 1000;
    }


    /**
     * 获取上传速度最快的上传服务器
     *
     * @return 上传服务器地址
     * @author weiba
     * @date 2024/3/25 10:42
     */
    public String getFastestUploadPCSServiceUrl() {
        int hour = DateUtil.hour(new Date(), true);
        if (StrUtil.isBlank(fastestUploadPcsServiceTag) || (Math.abs((Integer.parseInt(fastestUploadPcsServiceTag.split(fastestUploadPcsServiceTagSplit)[1])) - hour) > 6)) {
            synchronized (UrlConstants.GET_QUERY_UPLOAD_FILE_SERVICES) {
                HttpResponse execute = executeResponse(UrlConstants.GET_QUERY_UPLOAD_FILE_SERVICES);
                PSCUploadFileServerResponse baiduUploadPSCInfoBO = JSONUtils.toBean(execute.getBody(), PSCUploadFileServerResponse.class);

                long time = 0L;
                String serverUrl = "";
                // 选择延时最低的上传节点
                for (String serverUrlTemp : baiduUploadPSCInfoBO.getServer()) {
                    long start = System.currentTimeMillis();
                    executeResponse("https://" + serverUrlTemp);
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
