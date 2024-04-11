package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.Digest;
import pp.weiba.framework.DigestType;
import pp.weiba.framework.resource.IResourceOperation;
import pp.weiba.framework.resource.IShardUploadResource;
import pp.weiba.framework.resource.model.ResourceInfo;
import pp.weiba.framework.resource.model.ResourceStatus;
import pp.weiba.framework.resource.model.ResourceType;
import pp.weiba.framework.resource.model.UploadResourceInfo;
import pp.weiba.thirdparty.baidu.web.client.netdisk.FileOperationApiClient;
import pp.weiba.thirdparty.baidu.web.client.netdisk.request.*;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.AsyncTaskResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.CreateDirResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.FileDetailByFSIdResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.FileOperaAsyncTaskResponse;
import pp.weiba.utils.FileUtils;
import pp.weiba.utils.JSONUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 文件操作服务
 *
 * @author weiba
 * @date 2024/3/19 16:11
 */
@Log4j2
public class ResourceOperationService implements IResourceOperation {

    private final FileOperationApiClient fileOperationApiClient;

    private final IShardUploadResource<UploadEntity, FileChunk> shardUploadResource;

    public ResourceOperationService(FileOperationApiClient fileOperationApiClient, IShardUploadResource<UploadEntity, FileChunk> shardUploadResource) {
        this.fileOperationApiClient = fileOperationApiClient;
        this.shardUploadResource = shardUploadResource;
    }

    @Override
    public ResourceInfo createDir(String newDstPath) {
        CreateDirResponse dir = fileOperationApiClient.createDir(newDstPath);
        return new ResourceInfo(dir.getFsId(), ResourceType.FOLDER, dir.getName(), ResourceStatus.NORMAL, null, dir.getPath(), null, null, dir.getCtime(), dir.getMtime(), dir.getCtime(), dir.getMtime());
    }

    @Override
    public ResourceInfo createResource(UploadResourceInfo uploadResourceInfo) {
        String resourceId = shardUploadResource.upload(uploadResourceInfo);
        return get(resourceId);
    }

    @Override
    public ResourceInfo get(String resourceId) {
        FileDetailByFSIdResponse fileDetailByFsIds = fileOperationApiClient.getFileInfoByFsIds(resourceId);
        if (fileDetailByFsIds != null && fileDetailByFsIds.getList() != null && fileDetailByFsIds.getList().size() > 0) {
            FileDetailByFSIdResponse.ListBO item = fileDetailByFsIds.getList().get(0);
            return new ResourceInfo().setId(item.getFsId())
                    .setType(item.getIsdir() == 0 ? ResourceType.FILE : ResourceType.FOLDER)
                    .setName(item.getFilename())
                    .setState(ResourceStatus.NORMAL)
                    .setSize(item.getSize())
                    .setPath(item.getPath())
                    .setExt(FileUtils.getExtByFileName(item.getFilename()))
                    .setDigests(new ArrayList<Digest>() {{
                        add(new Digest(DigestType.MD5, item.getMd5()));
                    }})
                    .setCreateTime(item.getServerCtime())
                    .setUpdateTime(item.getServerMtime())
                    .setServerCreateTime(item.getServerCtime())
                    .setServerUpdateTime(item.getServerMtime());
        }
        return null;
    }

    @Override
    public Boolean delete(ResourceInfo resourceInfo) {
        String delPath = resourceInfo.getPath();
        if (StrUtil.isBlank(delPath)) {
            throw new IllegalArgumentException("path 不能为空");
        }

        FileOperaAsyncTaskResponse<AsyncTaskResponse.Delete> responseFileManagerBO = fileOperaSync(AsyncTaskType.DELETE, new HashMap<String, Object>() {{
            put("filelist", JSONUtils.toJsonStr(Arrays.asList(delPath.split(","))));
        }});
        return Boolean.TRUE;
    }

    @Override
    public Boolean rename(ResourceInfo resourceInfo, String newName) {
        if (resourceInfo == null || StrUtil.isBlank(resourceInfo.getId()) || StrUtil.isBlank(resourceInfo.getPath()) || StrUtil.isBlank(newName)) {
            throw new IllegalArgumentException("参数不能为空");
        }
        List<RenameRequest> renameFiles = new ArrayList<RenameRequest>() {{
            add(new RenameRequest(resourceInfo.getId(), resourceInfo.getPath(), newName));
        }};
        FileOperaAsyncTaskResponse<AsyncTaskResponse.FromTo> filelist = fileOperaSync(AsyncTaskType.RENAME, new HashMap<String, Object>() {{
            put("filelist", JSONUtils.toJsonStr(renameFiles));
        }});
        return Boolean.TRUE;
    }

    @Override
    public Boolean move(ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        if (resourceInfo == null || newResourceInfo == null || StrUtil.isBlank(resourceInfo.getPath()) || StrUtil.isBlank(newResourceInfo.getPath()) || StrUtil.isBlank(newResourceInfo.getName())) {
            throw new IllegalArgumentException("参数不能为空");
        }
        FileOperaAsyncTaskResponse<AsyncTaskResponse.FromTo> filelist = fileOperaSync(AsyncTaskType.MOVE, resourceInfo, newResourceInfo);
        return Boolean.TRUE;
    }


    @Override
    public Boolean copy(ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        if (resourceInfo == null || newResourceInfo == null || StrUtil.isBlank(resourceInfo.getPath()) || StrUtil.isBlank(newResourceInfo.getPath()) || StrUtil.isBlank(newResourceInfo.getName())) {
            throw new IllegalArgumentException("参数不能为空");
        }
        FileOperaAsyncTaskResponse<AsyncTaskResponse.FromTo> filelist = fileOperaSync(AsyncTaskType.COPY, resourceInfo, newResourceInfo);
        return Boolean.TRUE;
    }

    public <T> FileOperaAsyncTaskResponse<T> fileOperaSync(AsyncTaskType opera, ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        List<MoveOrCopyRequest> params = new ArrayList<MoveOrCopyRequest>() {{
            add(new MoveOrCopyRequest(resourceInfo.getPath(), newResourceInfo.getPath(), newResourceInfo.getName()));
        }};

        FileOperaAsyncTaskResponse<T> filelist = fileOperaSync(opera, new HashMap<String, Object>() {{
            put("filelist", JSONUtils.toJsonStr(params));
        }});
        return filelist;
    }

    public <T> FileOperaAsyncTaskResponse<T> fileOperaSync(AsyncTaskType opera, HashMap<String, Object> paramsMap) {
        // 同步返回的 没有 taskid
        FileOperaAsyncTaskResponse<T> result = fileOperationApiClient.fileOperaAsyncTask(opera, TaskExecuteType.SYNC, paramsMap);
        return result;
    }

    public <T> AsyncTaskResponse<T> fileOperaASync(AsyncTaskType opera, HashMap<String, Object> paramsMap) {
        FileOperaAsyncTaskResponse<T> responseFileManagerBO = fileOperationApiClient.fileOperaAsyncTask(opera, TaskExecuteType.ASYNC, paramsMap);
        AsyncTaskResponse<T> responseAsyncTaskBO = null;
        while (true) {
            responseAsyncTaskBO = fileOperationApiClient.getAsyncTaskResult(responseFileManagerBO.getTaskid());
            if (responseAsyncTaskBO.getStatus().equals("success")) {
                break;
            }
            log.info("任务执行中...");
            ThreadUtil.sleep(50);
        }
        return responseAsyncTaskBO;
    }

}
