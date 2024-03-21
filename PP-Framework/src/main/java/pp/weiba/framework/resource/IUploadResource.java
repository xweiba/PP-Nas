package pp.weiba.framework.resource;

/**
 * 上传接口
 *
 * @author weiba
 * @date 2024/3/5 15:56
 */
public interface IUploadResource {
    
    /**
     * 资源上传接口
     *
     * @param uploadResourceInfo 上传资源信息
     * @return 资源上传完成后返回资源id
     * @author weiba
     * @date 2024/3/20 14:31
     */
    <T> String upload(UploadResourceInfo<T> uploadResourceInfo);

    /**
     * 检测资源是否存在，存在直接返回id，不存在返回空
     *
     * @param uploadResourceInfo 待上传资源信息
     * @return 资源id
     * @author weiba
     * @date 2024/3/20 14:39
     */
    <T> String checkResourceExist(UploadResourceInfo<T> uploadResourceInfo);

}
