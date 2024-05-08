package pp.weiba.thirdparty.aliyun.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.IResourceOperation;
import pp.weiba.framework.resource.model.ResourceInfo;
import pp.weiba.framework.resource.model.UploadResourceInfo;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.FileOperationApiClient;

/**
 * 阿里云盘文件
 *
 * @author weiba
 * @date 2024/5/8 15:33
 */
@Log4j2
public class ResourceOperation implements IResourceOperation {

    private final FileOperationApiClient fileOperationApiClient;

    public ResourceOperation(FileOperationApiClient fileOperationApiClient) {
        this.fileOperationApiClient = fileOperationApiClient;
    }

    @Override
    public ResourceInfo createDir(String newDstPath) {
        return null;
    }

    @Override
    public <T> ResourceInfo createResource(UploadResourceInfo<T> uploadResourceInfo) {
        return null;
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
    public Boolean rename(ResourceInfo resourceInfo, String newName) {
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
