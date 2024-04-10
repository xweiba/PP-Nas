package pp.weiba.framework.resource.impl;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.IResourceOperation;
import pp.weiba.framework.resource.model.ResourceInfo;
import pp.weiba.framework.resource.model.UploadResourceInfo;

/**
 * 资源操作服务
 *
 * @author weiba
 * @date 2024/4/10 17:09
 */
@Log4j2
public class ResourceOperationService implements IResourceOperation {

    private final IResourceOperation adapter;

    public ResourceOperationService(IResourceOperation adapter) {
        this.adapter = adapter;
    }

    @Override
    public ResourceInfo createDir(String newDstPath) {
        return adapter.createDir(newDstPath);
    }

    @Override
    public <T> ResourceInfo createResource(UploadResourceInfo<T> uploadResourceInfo) {
        return adapter.createResource(uploadResourceInfo);
    }

    @Override
    public ResourceInfo get(String resourceId) {
        return adapter.get(resourceId);
    }

    @Override
    public Boolean delete(ResourceInfo resourceInfo) {
        return adapter.delete(resourceInfo);
    }

    @Override
    public Boolean rename(ResourceInfo resourceInfo, String newName) {
        return adapter.rename(resourceInfo, newName);
    }

    @Override
    public Boolean move(ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        return adapter.move(resourceInfo, newResourceInfo);
    }

    @Override
    public Boolean copy(ResourceInfo resourceInfo, ResourceInfo newResourceInfo) {
        return adapter.copy(resourceInfo, newResourceInfo);
    }


}
