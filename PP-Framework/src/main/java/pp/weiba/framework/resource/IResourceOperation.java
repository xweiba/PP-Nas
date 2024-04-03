package pp.weiba.framework.resource;

/**
 * 资源操作接口，用于提供资源的管理功能，包括删除、重命名、移动和复制等操作。
 *
 * @author weiba
 * @date 2024/3/5 15:39
 */
public interface IResourceOperation {

    /**
     * 创建文件夹
     *
     * @param newDstPath 文件夹路径
     * @return 返回信息
     * @author weiba
     * @date 2024/3/19 16:16
     */
    ResourceInfo createDir(String newDstPath);

    /**
     * 资源上传接口
     *
     * @param uploadResourceInfo 上传资源信息
     * @return 资源上传完成后返回资源
     * @author weiba
     * @date 2024/3/20 14:31
     */
    <T> ResourceInfo createResource(UploadResourceInfo<T> uploadResourceInfo);

    /**
     * 根据资源id获取资源
     *
     * @param resourceId 资源id
     * @return 返回资源id对应资源
     * @author weiba
     * @date 2024/3/5 16:03
     */
    ResourceInfo get(String resourceId);
    
    /**
     * 删除指定资源
     *
     * @param resourceInfo 要删除的资源
     * @return 删除操作是否成功
     * @author weiba
     * @date 2024/3/5 15:43
     */
    Boolean delete(ResourceInfo resourceInfo);

    /**
     * 对指定资源进行重命名。
     *
     * @param resourceInfo    要重命名的资源
     * @param newName 新命名
     * @return 重命名操作是否成功
     * @author weiba
     * @date 2024/3/5 15:43
     */
    Boolean rename(ResourceInfo resourceInfo, String newName);

    /**
     * 将资源移动到新的位置。
     *
     * @param resourceInfo    要移动的资源
     * @param newResourceInfo 移动后的新位置
     * @return 移动操作是否成功
     * @author weiba
     * @date 2024/3/5 15:43
     */
    Boolean move(ResourceInfo resourceInfo, ResourceInfo newResourceInfo);

    /**
     * 复制资源到指定的位置。
     *
     * @param resourceInfo    要复制的资源
     * @param newResourceInfo 复制后的新资源
     * @return 复制操作是否成功
     * @author weiba
     * @date 2024/3/5 15:43
     */
    Boolean copy(ResourceInfo resourceInfo, ResourceInfo newResourceInfo);

}
