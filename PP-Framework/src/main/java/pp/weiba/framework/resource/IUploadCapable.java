package pp.weiba.framework.resource;

/**
 * 上传接口
 *
 * @author weiba
 * @date 2024/3/5 15:56
 */
public interface IUploadCapable {

    /**
     * 资源上传接口
     *
     * @param resourceInfo 资源信息
     * @param data     资源文件
     * @return 返回资源信息
     * @author weiba
     * @date 2024/3/5 15:58
     */
    ResourceInfo upload(ResourceInfo resourceInfo, byte[] data);

}
