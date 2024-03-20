package pp.weiba.framework.resource;

/**
 * 分片上传接口，该接口应该是异步的。
 *
 * @author weiba
 * @date 2024/3/20 14:38
 */
public interface IShardUploadResource extends IUploadResource {

    // 文件预创建接口
    <T> ResourceInfo preCreateFile(UploadResourceInfo<T> uploadResourceInfo);


}
