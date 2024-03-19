package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.IResourceOperation;
import pp.weiba.framework.resource.ResourceInfo;
import pp.weiba.framework.resource.ResourceState;
import pp.weiba.framework.resource.ResourceType;
import pp.weiba.thirdparty.baidu.web.api.netdisk.FileOperationApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.CreateDirResponse;

/**
 * 文件操作服务
 *
 * @author weiba
 * @date 2024/3/19 16:11
 */
@Log4j2
public class ResourceOperationService implements IResourceOperation {

    private final FileOperationApiClient fileOperationApiClient;

    public ResourceOperationService(FileOperationApiClient fileOperationApiClient) {
        this.fileOperationApiClient = fileOperationApiClient;
    }

    @Override
    public ResourceInfo createDir(String newDstPath) {
        CreateDirResponse dir = fileOperationApiClient.createDir(newDstPath);
        return new ResourceInfo(dir.getFsId(), ResourceType.FOLDER, dir.getName(), ResourceState.NORMAL, null, dir.getPath(), null, null, dir.getCtime(), dir.getMtime(), dir.getCtime(), dir.getMtime());
    }

    @Override
    public ResourceInfo get(String resourceId) {
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
