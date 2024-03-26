package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.*;
import pp.weiba.thirdparty.baidu.web.api.netdisk.FileOperationApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.FileChunk;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.CreateDirResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.FileDetailByFSIdResponse;

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
        FileDetailByFSIdResponse fileDetailByFsIds = fileOperationApiClient.getFileDetailByFsIds(resourceId);
        return null;
    }

    @Override
    public Boolean delete(ResourceInfo resourceInfo) {
        return null;
    }

    @Override
    public Boolean rename(ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        return null;
    }

    @Override
    public Boolean move(ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        return null;
    }

    @Override
    public Boolean copy(ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        return null;
    }

}
