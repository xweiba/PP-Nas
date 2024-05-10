package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.ClientContants;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.*;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.*;

import java.util.HashMap;

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
        String driveId = isBackupDrive ? ClientContants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : ClientContants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
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
        return postSrtExecute(UrlConstants.POST_RESOURCE_BATCH_URL, batchRequest, new TypeReference<BatchResponse>() {
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




}
