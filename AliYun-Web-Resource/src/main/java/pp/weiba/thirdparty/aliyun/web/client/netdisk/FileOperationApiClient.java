package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.ClientConstants;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.model.*;
import pp.weiba.thirdparty.aliyun.web.client.AliYunClientConstants;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.*;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.*;
import pp.weiba.utils.model.FileChunk;
import pp.weiba.utils.FileUtils;
import pp.weiba.utils.JSONUtils;
import pp.weiba.utils.StringUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 文件管理API
 *
 * @author weiba
 * @date 2024/4/30 9:54
 */
@Log4j2
public class FileOperationApiClient extends AbstractApiHttpClient {

    public FileOperationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 新建文件夹
     *
     * @param addFolderParam 新建文件夹参数
     * @return 新建文件夹信息
     * @author weiba
     * @date 2024/4/30 10:57
     */
    public AddFolderResponse createDir(AddFolderRequest addFolderParam) {
        return postSrtExecute(UrlConstants.POST_ADD_FOLDER_URL, addFolderParam, new TypeReference<AddFolderResponse>() {
        });
    }


    /**
     * 获取文件信息
     *
     * @param getFileInfoParam 获取文件信息参数
     * @return 文件信息
     * @author weiba
     * @date 2024/4/30 10:57
     */
    public GetFileInfoResponse getFileInfo(GetFileInfoRequest getFileInfoParam) {
        return postSrtExecute(UrlConstants.POST_GET_FILE_INFO_URL, getFileInfoParam, new TypeReference<GetFileInfoResponse>() {
        });
    }

    /**
     * 资源删除, 不进回收站
     *
     * @param fileId 资源id
     * @author weiba
     * @date 2024/5/10 13:44
     */
    public BatchResponse delele(String fileId) {
        return batch(new BatchRequest(new BatchOperationRequest(UrlConstants.RESOURCE_BATCH_FILE_DELETE_URL, fileId)));
    }

    /**
     * 资源移动到指定文件夹
     *
     * @param fileId 资源id
     * @param toParentFileId 目标文件夹id
     * @return
     * @author weiba
     * @date 2024/5/10 15:15
     */
    public BatchResponse move(String fileId, String toParentFileId) {
        return batch(new BatchRequest(new BatchOperationRequest(UrlConstants.RESOURCE_BATCH_FILE_MOVE_URL, fileId, toParentFileId)));
    }

    // 重命名
    public RenameResponse rename(String fileId, String newName) {
        return postSrtExecute(UrlConstants.POST_RENAME_URL, new RenameRequest(fileId, newName), new TypeReference<RenameResponse>() {
        });
    }

    /**
     * 获取文件下载地址，不支持文件夹，不能下载大于100MB的文件
     *
     * 下载时要带头 Referer: https://www.alipan.com/
     *
     * @param fileId
     * @return
     * @author weiba
     * @date 2024/5/10 16:55
     */
    public GetFileDownloadUrlResponse getFileDownloadUrl(String fileId) {
        return getFileDownloadUrl(fileId, true);
    }

    public GetFileDownloadUrlResponse getFileDownloadUrl(String fileId, boolean isBackupDrive) {
        String driveId = isBackupDrive ? AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
        return postSrtExecute(UrlConstants.POST_GET_FILE_DOWNLOAD_URL, new HashMap<String, String>() {{
            put("file_id", fileId);
            put("drive_id", driveId);
        }}, new TypeReference<GetFileDownloadUrlResponse>() {
        });
    }

    // 搜索
    public SearchResponse search(String keyword, String category, String nextMarker) {
        return search(new SearchRequest(keyword, category, nextMarker));
    }

    public SearchResponse search(SearchRequest searchRequest) {
        return postSrtExecute(UrlConstants.POST_RESOURCE_SEARCH_URL, searchRequest, new TypeReference<SearchResponse>() {
        });
    }


    /**
     * 资源批量处理
     *
     * @param batchRequest 待处理信息
     * @return 处理结果
     * @author weiba
     * @date 2024/5/10 13:43
     */
    public BatchResponse batch(BatchRequest batchRequest) {
        return batch(batchRequest, null);
    }

    public BatchResponse batch(BatchRequest batchRequest, Map<String, String> headerMap) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, UrlConstants.POST_RESOURCE_BATCH_URL)
                .setRequestBody(JSONUtils.toJsonStr(batchRequest)).handler(headerMap);
        return execute(httpRequest, new TypeReference<BatchResponse>() {
        });
    }

    /**
     * 资源移动到回收站， 10天自动删除
     *
     * @param fileId 资源id
     * @author weiba
     * @date 2024/5/10 13:44
     */
    public BatchResponse fileToRecycle(String fileId) {
        return batch(new BatchRequest(new BatchOperationRequest(UrlConstants.RESOURCE_BATCH_FILE_TO_RECYCLE_URL, fileId)));
    }

    /**
     * 资源从回收站还原
     *
     * @param fileId 资源id
     * @author weiba
     * @date 2024/5/10 13:44
     */
    public BatchResponse recycleToFile(String fileId) {
        return batch(new BatchRequest(new BatchOperationRequest(UrlConstants.RESOURCE_BATCH_RECYCLE_RESTORE_URL, fileId)));
    }


    /**
     * 获取回收站列表
     *
     * @param params 查询参数
     * @return 列表信息
     * @author weiba
     * @date 2024/5/10 14:28
     */
    public GetRecycleResponse getRecycles(GetRecycleRsequest params) {
        return postSrtExecute(UrlConstants.POST_GET_RECYCLE_LIST_URL, params, new TypeReference<GetRecycleResponse>() {
        });
    }


    /**
     * 将文件在两个盘中同步
     *
     * @param fileId 来源盘的资源id
     * @param toParentFileId 目标盘的文件夹id
     * @param toResourceDrive 备份盘 -> 资源盘 toResourceDrive = true
     * @return
     * @author weiba
     * @date 2024/5/10 17:47
     */
    public CopyToResourceResponse copyToResource(String toParentFileId, boolean toResourceDrive, String... fileIds) {
        if (StrUtil.isEmpty(toParentFileId) || ArrayUtil.isEmpty(fileIds)) {
            return null;
        }
        String fromDriveId = !toResourceDrive ? AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG : AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG;
        String toDriveId = toResourceDrive ? AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG : AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG;

        List<CopyToResourceRequest.FilesResponse> fileInfos = new ArrayList<>();
        for (String fileId : fileIds) {
            GetFileInfoResponse fileInfo = getFileInfo(new GetFileInfoRequest(fromDriveId, fileId));
            CopyToResourceRequest.FilesResponse filesResponse = BeanUtil.copyProperties(fileInfo, CopyToResourceRequest.FilesResponse.class);
            filesResponse.setRawFile(fileInfo).setTookAt(filesResponse.getCreatedAt().getTime()).setExtension(fileInfo.getFileExtension());
            fileInfos.add(filesResponse);
        }


        CopyToResourceRequest copyToResourceRequest = new CopyToResourceRequest(fromDriveId, toDriveId, toParentFileId, fileInfos);

        CopyToResourceResponse copyToResourceResponse = postSrtExecute(UrlConstants.POST_RESOURCE_COPY_TO_RESOURCE_URL, copyToResourceRequest, new TypeReference<CopyToResourceResponse>() {
        });

        for (CopyToResourceResponse.ItemsResponse item : copyToResourceResponse.getItems()) {
            if (StringUtils.isNotBlank(item.getAsyncTaskId())) {
                while (true) {
                    BatchResponse asyncTaskIdResult = getAsyncTaskIdResult(item.getAsyncTaskId());
                    String taskStatus = asyncTaskIdResult.getResponses().get(0).getBody().getStatus();
                    if ("Succeed".equals(taskStatus)) {
                        break;
                    }
                }
            }
        }
        return copyToResourceResponse;
    }

    private BatchResponse getAsyncTaskIdResult(String asyncTaskId) {
        BatchOperationRequest batchOperationRequest = new BatchOperationRequest(UrlConstants.RESOURCE_BATCH_GET_ASYNC_TASK_URL, asyncTaskId)
                .setBodyRequest(new HashMap<String, Object>() {{
                    put("async_task_id", asyncTaskId);
                }});
        return batch(new BatchRequest(batchOperationRequest));
    }

    /**
     * 创建分享
     *
     * @param request 分享对象
     * @return 分享信息
     * @author weiba
     * @date 2024/5/15 16:50
     */
    public CreateShareResponse createShare(CreateShareRequest request) {
        return postSrtExecute(UrlConstants.POST_RESOURCE_CREATE_SHARE_URL, JSON.toJSONString(request, SerializerFeature.UseISO8601DateFormat), new TypeReference<CreateShareResponse>() {
        });
    }

    /**
     * 获取我分享的列表
     *
     * @param request 分享对象
     * @return 分享信息
     * @author weiba
     * @date 2024/5/15 16:50
     */
    public GetMyShareListResponse getMyShareList(GetMyShareListRequest request) {
        return postSrtExecute(UrlConstants.POST_RESOURCE_GET_SHARE_URL, JSON.toJSONString(request, SerializerFeature.UseISO8601DateFormat), new TypeReference<GetMyShareListResponse>() {
        });
    }


    /**
     * 取消分享
     *
     * @param shareId 分享id
     * @return
     * @author weiba
     * @date 2024/5/17 9:29
     */
    public BatchResponse cancelShare(String shareId) {
        return batch(new BatchRequest(new BatchOperationRequest(UrlConstants.RESOURCE_BATCH_CANCEL_SHARE_URL, shareId).setBodyRequest(new HashMap<String,Object>(){{
            put("share_id", shareId);
        }})));
    }

    /*
    * 获取分享token
    * */
    public GetShareTokenResponse getShareToken(String shareId, String sharePwd) {
        return postSrtExecute(UrlConstants.POST_GET_SHARE_TOKEN_URL, new HashMap<String,Object>(){{
            put("share_id", shareId);
            put("share_pwd", sharePwd);
        }}, new TypeReference<GetShareTokenResponse>() {
        });
    }

    /*
    * 获取分享资源的文件列表
    * */
    public GetShareFilesResponse getShareFiles(GetShareFilesRequest queryParams, String shareToken) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, UrlConstants.POST_GET_SHARE_FILE_LIST_URL)
                .setRequestBody(JSONUtils.toJsonStr(queryParams)).addheader("X-Share-Token", shareToken);
        return execute(httpRequest, new TypeReference<GetShareFilesResponse>() {});
    }


    /*
    * 获取分享文件详情
    * */
    public GetShareFileDetailResponse getShareFileDetail(GetShareFileDetailRequest queryParams, String shareToken) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, UrlConstants.POST_GET_SHARE_FILE_DETAIL_URL)
                .setRequestBody(JSONUtils.toJsonStr(queryParams)).addheader("X-Share-Token", shareToken);
        return execute(httpRequest, new TypeReference<GetShareFileDetailResponse>() {});
    }


    public BatchResponse saveShareFile(String shareToken, SaveShareFileBody ...saveShareFileBodies) {
        BatchOperationRequest[] fileList = new BatchOperationRequest[saveShareFileBodies.length];
        for (int i = 0; i < saveShareFileBodies.length; i++) {
            SaveShareFileBody saveShareFileBody = saveShareFileBodies[i];
            fileList[i] = new BatchOperationRequest(UrlConstants.RESOURCE_BATCH_SAVE_SHARE_FILE_URL)
                    .setBodyRequest(saveShareFileBody)
                    .setId("0")
            ;
        }

        return batch(new BatchRequest(fileList), new HashMap<String, String>() {{
            put("X-Share-Token", shareToken);
        }});
    }

    /*
    * 获取子目录信息
    * */
    /**
     * 获取文件夹的子目录信息
     *
     * @param request 查询参数
     * @return 子文件夹信息，没有子文件信息
     * @author weiba
     * @date 2024/5/17 10:23
     */
    public GetFileChildDirResponse getFileChildDir(GetFileChildDirRequest request) {
        return postSrtExecute(UrlConstants.POST_GET_FILE_CHILD_DIR_URL, request, new TypeReference<GetFileChildDirResponse>() {
        });
    }

    /*
    * 获取子目录信息
    * */
    /**
     * 获取文件夹的子目录信息
     *
     * @param request 查询参数
     * @return 子文件夹信息，没有子文件信息
     * @author weiba
     * @date 2024/5/17 10:23
     */
    public GetFileSimpleResponse getFileSimple(GetFileSimpleRequest request) {
        return postSrtExecute(UrlConstants.POST_GET_FILE_SIMPLE_URL, request, new TypeReference<GetFileSimpleResponse>() {
        });
    }


    /**
     * 文件上传预创建
     *
     * @param params
     * @return 返回待上传信息
     * @author weiba
     * @date 2024/5/21 11:19
     */
    public CreateWithFoldersResponse createWithFolders(CreateWithFoldersRequest params) {
        return postSrtExecute(UrlConstants.POST_RESOURCE_CREATE_WITH_FOLDERS_URL, params, new TypeReference<CreateWithFoldersResponse>() {
        });
    }


    public CreateWithFoldersResponse checkFileExist(boolean isBackupDrive, String parentFileId, String name, String filePath) {
        CreateWithFoldersRequest createWithFoldersRequest = buildCheckFileExistParams(isBackupDrive, parentFileId, name, filePath);
        return createWithFolders(createWithFoldersRequest);
    }

    @NotNull
    private static CreateWithFoldersRequest buildCheckFileExistParams(boolean isBackupDrive, String parentFileId, String name, String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new RuntimeException("不支持文件夹或文件不存在");
        }
        CreateWithFoldersRequest createWithFoldersRequest = new CreateWithFoldersRequest(isBackupDrive, parentFileId, name, filePath, file.length());
        String preHash = AliYunUtils.preHash(filePath);
        createWithFoldersRequest.setPreHash(preHash);
        return createWithFoldersRequest;
    }


    public CreateWithFoldersResponse fileTransferInSecond(boolean isBackupDrive, String parentFileId, String name, String filePath) {
        CreateWithFoldersRequest createWithFoldersRequest = buildFileTransferInSecondParams(isBackupDrive, parentFileId, name, filePath);
        return createWithFolders(createWithFoldersRequest);
    }

    @NotNull
    private static CreateWithFoldersRequest buildFileTransferInSecondParams(boolean isBackupDrive, String parentFileId, String name, String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new RuntimeException("不支持文件夹或文件不存在");
        }
        CreateWithFoldersRequest createWithFoldersRequest = new CreateWithFoldersRequest(isBackupDrive, parentFileId, name, filePath, file.length());
        // 必须传ContentHash，不然秒传会失败，得走正常上传
        createWithFoldersRequest.setContentHash(DigestUtil.sha1Hex(new File(filePath)));
        createWithFoldersRequest.setContentHashName("sha1");
        createWithFoldersRequest.setProofCode(AliYunClientConstants.REQUEST_UPLOAD_FILE_PROOF_CODE_TAG+ filePath);
        createWithFoldersRequest.setProofVersion("v1");
        return createWithFoldersRequest;
    }

    public CreateWithFoldersResponse uploadFile(boolean isBackupDrive, String parentFileId, String name, String filePath) {

        CreateWithFoldersRequest params = buildCheckFileExistParams(isBackupDrive, parentFileId, name, filePath);
        // 检测是否可以秒传
        CreateWithFoldersResponse response = createWithFolders(params);

        if (StrUtil.isNotBlank(response.getCode()) && response.getCode().equals("PreHashMatched")) {
            response = fileTransferInSecond(isBackupDrive, parentFileId, name, filePath);
            if (response.getPartInfoList() == null) {
                return response;
            }
        }

        // 必须按顺序上传
        List<CreateWithFoldersResponse.PartInfoListResponse> partInfoList = response.getPartInfoList();

        // 构建分片参数
        List<FileChunk> fileChunks = FileUtils.buildFileChunks(new File(filePath), AliYunClientConstants.FILE_SPLIT_SIZE);
        LinkedHashMap<Integer, FileChunk> collect = fileChunks.stream().collect(Collectors.toMap(key -> key.getPartSeq(), value -> value, (k1, k2) -> k1, LinkedHashMap::new));
        for (int i = 0; i < partInfoList.size(); i++) {
            CreateWithFoldersResponse.PartInfoListResponse partInfoListResponse = partInfoList.get(i);
            String uploadUrl = partInfoListResponse.getUploadUrl();
            FileChunk fileChunk = collect.get(partInfoListResponse.getPartNumber() - 1);
            uploadFileChunk(uploadUrl, new File(filePath), fileChunk);
        }
        return null;
    }

    public Boolean uploadFileChunk(String uploadUrl, File file, FileChunk chunk) {
        UploadFile uploadFile = new UploadFile().setFile(file);
        int partseq = 0;
        if (chunk != null) {
            partseq = chunk.getPartSeq();
            uploadFile.setChunk(new FileChunk(chunk.getStart(), chunk.getLength(), partseq, null)).setUploadType(UploadType.BYTE);
        }
//        ALi_HttpClientUtil.uploadFile(uploadUrl, file, uploadFile);

        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.PUT, uploadUrl).setUploadFile(uploadFile)
                .addBuildParams(ClientConstants.REQUEST_PARAM_NEW_SESSION_TAG, true)
                .addheader("Accept", "*")
                .addheader("Origin", "https://www.aliyundrive.com")
                .addheader("Referer", "https://www.aliyundrive.com")
                .addheader("Content-Type", "")
                .addheader("Connection", "keep-alive")
                .addheader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 Edg/124.0.0.0")
                ;
        HttpResponse httpResponse = executeResponse(httpRequest);
        return true;
    }
}
