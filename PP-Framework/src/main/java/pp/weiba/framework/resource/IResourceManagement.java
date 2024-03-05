package pp.weiba.framework.resource;

/**
 * 资源管理接口，用于提供资源的管理功能，包括删除、重命名、移动和复制等操作。
 *
 * @author weiba
 * @date 2024/3/5 15:39
 */
public interface IResourceManagement {

    /**
     * 根据资源id获取资源
     *
     * @param resourceId 资源id
     * @return 返回资源id对应资源
     * @author weiba
     * @date 2024/3/5 16:03
     */
    Resource get(String resourceId);
    
    /**
     * 删除指定资源
     *
     * @param resource 要删除的资源
     * @return 删除操作是否成功
     * @author weiba
     * @date 2024/3/5 15:43
     */
    Boolean delete(Resource resource);

    /**
     * 对指定资源进行重命名。
     *
     * @param resource    要重命名的资源
     * @param newResource 重命名后的新资源
     * @return 重命名操作是否成功
     * @author weiba
     * @date 2024/3/5 15:43
     */
    Boolean rename(Resource resource, Resource newResource);

    /**
     * 将资源移动到新的位置。
     *
     * @param resource    要移动的资源
     * @param newResource 移动后的新位置
     * @return 移动操作是否成功
     * @author weiba
     * @date 2024/3/5 15:43
     */
    Boolean move(Resource resource, Resource newResource);

    /**
     * 复制资源到指定的位置。
     *
     * @param resource    要复制的资源
     * @param newResource 复制后的新资源
     * @return 复制操作是否成功
     * @author weiba
     * @date 2024/3/5 15:43
     */
    Boolean copy(Resource resource, Resource newResource);

}
