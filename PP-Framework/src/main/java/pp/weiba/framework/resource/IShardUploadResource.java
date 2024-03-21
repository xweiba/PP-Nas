package pp.weiba.framework.resource;

import java.util.List;

/**
 * 分片上传接口。
 *
 * @author weiba
 * @date 2024/3/20 14:38
 */
public interface IShardUploadResource<T, F> extends IUploadResource<T> {

    /**
     * 资源预创建接口
     *
     * @param uploadResourceInfo 上传资源信息
     * @return 返回对应的预创建资源id
     * @author weiba
     * @date 2024/3/21 9:55
     */
    String preCreateResource(UploadResourceInfo<T> uploadResourceInfo);


    /**
     * 构建分片文件
     *
     * @param uploadResourceInfo 上传资源信息
     * @return 分片实体
     * @author weiba
     * @date 2024/3/21 10:20
     */
    List<ShardResource<F>> shardResourceBuild(UploadResourceInfo<T> uploadResourceInfo);

    /**
     * 分片文件上传
     *
     * @param uploadResourceInfo 上传资源信息
     * @param shardResource      分片信息
     * @return 分片资源信息
     * @author weiba
     * @date 2024/3/21 9:59
     */
    ShardResource<F> shardResourceUpload(UploadResourceInfo<T> uploadResourceInfo, ShardResource<F> shardResource);


    /**
     * 分片上传完成后提交完成上传
     *
     * @param shards 分片信息
     * @return 资源创建后id
     * @author weiba
     * @date 2024/3/21 10:02
     */
    String completeResourceUpload(UploadResourceInfo<T> uploadResourceInfo, List<ShardResource<F>> shards);

}
