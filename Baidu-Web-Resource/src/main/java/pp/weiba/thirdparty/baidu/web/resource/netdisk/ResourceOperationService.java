package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.Digest;
import pp.weiba.framework.DigestType;
import pp.weiba.framework.resource.*;
import pp.weiba.thirdparty.baidu.web.api.netdisk.FileOperationApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.AsyncTaskType;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.FileChunk;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.MoveOrCopyRequest;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.RenameRequest;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.AsyncTaskResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.CreateDirResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.FileDetailByFSIdResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.FileOperaAsyncTaskResponse;
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
        return new ResourceInfo(dir.getFsId(), ResourceType.FOLDER, dir.getName(), ResourceState.NORMAL, null, dir.getPath(), null, null, dir.getCtime(), dir.getMtime(), dir.getCtime(), dir.getMtime());
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
            FileDetailByFSIdResponse.ListBO listBO = fileDetailByFsIds.getList().get(0);
            return new ResourceInfo().setId(listBO.getFsId())
                    .setType(listBO.getIsdir() == 0 ? ResourceType.FILE : ResourceType.FOLDER)
                    .setName(listBO.getFilename())
                    .setState(ResourceState.NORMAL)
                    .setSize(listBO.getSize())
                    .setPath(listBO.getPath())
                    .setExt(FileUtils.getExtByFileName(listBO.getFilename()))
                    .setDigests(new ArrayList<Digest>() {{
                        add(new Digest(DigestType.MD5, listBO.getMd5()));
                    }})
                    .setCreateTime(listBO.getServerCtime())
                    .setUpdateTime(listBO.getServerMtime())
                    .setServerCreateTime(listBO.getServerCtime())
                    .setServerUpdateTime(listBO.getServerMtime())
                    ;
        }
        return null;
    }

    @Override
    public Boolean delete(ResourceInfo resourceInfo) {
        String delPath = resourceInfo.getPath();
        if (StrUtil.isBlank(delPath)) {
            throw new IllegalArgumentException("path 不能为空");
        }

        AsyncTaskResponse<AsyncTaskResponse.Delete> responseFileManagerBO = fileOperaSynchronous(AsyncTaskType.DELETE, new HashMap<String, Object>() {{
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
        AsyncTaskResponse<AsyncTaskResponse.FromTo> filelist = fileOperaSynchronous(AsyncTaskType.RENAME, new HashMap<String, Object>() {{
            put("filelist", JSONUtils.toJsonStr(renameFiles));
        }});
        return Boolean.TRUE;
    }

    @Override
    public Boolean move(ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        if (resourceInfo == null || newResourceInfo == null || StrUtil.isBlank(resourceInfo.getPath()) || StrUtil.isBlank(newResourceInfo.getPath()) || StrUtil.isBlank(newResourceInfo.getName())) {
            throw new IllegalArgumentException("参数不能为空");
        }
        AsyncTaskResponse<AsyncTaskResponse.FromTo> filelist = fileOperaSynchronous(AsyncTaskType.MOVE, resourceInfo, newResourceInfo);
        return Boolean.TRUE;
    }


    @Override
    public Boolean copy(ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        if (resourceInfo == null || newResourceInfo == null || StrUtil.isBlank(resourceInfo.getPath()) || StrUtil.isBlank(newResourceInfo.getPath()) || StrUtil.isBlank(newResourceInfo.getName())) {
            throw new IllegalArgumentException("参数不能为空");
        }
        AsyncTaskResponse<AsyncTaskResponse.FromTo> filelist = fileOperaSynchronous(AsyncTaskType.COPY, resourceInfo, newResourceInfo);
        return Boolean.TRUE;
    }

    public <T> AsyncTaskResponse<T> fileOperaSynchronous(AsyncTaskType opera, ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        List<MoveOrCopyRequest> params = new ArrayList<MoveOrCopyRequest>() {{
            add(new MoveOrCopyRequest(resourceInfo.getPath(), newResourceInfo.getPath(), newResourceInfo.getName()));
        }};

        AsyncTaskResponse<T> filelist = fileOperaSynchronous(opera, new HashMap<String, Object>() {{
            put("filelist", JSONUtils.toJsonStr(params));
        }});
        return filelist;
    }

    public <T> AsyncTaskResponse<T> fileOperaSynchronous(AsyncTaskType opera, HashMap<String, Object> paramsMap) {
        FileOperaAsyncTaskResponse<T> responseFileManagerBO = fileOperationApiClient.fileOperaAsyncTask(opera, paramsMap);
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
