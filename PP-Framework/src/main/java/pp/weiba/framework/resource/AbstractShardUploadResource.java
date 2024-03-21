package pp.weiba.framework.resource;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 分片上传
 *
 * @author weiba
 * @date 2024/3/21 10:07
 */
@Log4j2
public abstract class AbstractShardUploadResource extends AbstractUploadResource implements IShardUploadResource {

    // 分片上传线程池
    private final ExecutorService shardResourceUploadExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    protected <T> String doUpload(UploadResourceInfo<T> uploadResourceInfo) {
        // 资源预创建
        String uploadId = doPreCreateResource(uploadResourceInfo);
        uploadResourceInfo.setUploadId(uploadId);

        // 构建分片
        List<ShardResource<T>> shardResources = doShardResourceBuild(uploadResourceInfo);

        // 分片上传
        List<ShardResource<T>> shardResourceUploads = doShardResourceUpload(uploadResourceInfo, shardResources);

        // 完成分片资源上传
        return doCompleteResourceUpload(shardResourceUploads);
    }

    private <T> List<ShardResource<T>> doShardResourceBuild(UploadResourceInfo<T> uploadResourceInfo) {
        return shardResourceBuild(uploadResourceInfo);
    }

    private <T> String doCompleteResourceUpload(List<ShardResource<T>> shardResourceUploadIds) {
        return completeResourceUpload(shardResourceUploadIds);
    }

    private <T> List<ShardResource<T>> doShardResourceUpload(UploadResourceInfo<T> uploadResourceInfo, List<ShardResource<T>> shardResources) {
        List<ShardResource<T>> tShardResources = new ArrayList<>();
        // 并发上传
        List<Future<ShardResource<T>>> tasks = new ArrayList<>();
        for (ShardResource<T> shardResource : shardResources) {
            tasks.add(this.shardResourceUploadExecutor.submit(() -> shardResourceUpload(uploadResourceInfo, shardResource)));
        }
        boolean isFailed = false;
        for (Future<ShardResource<T>> task : tasks) {
            try {
                if (isFailed) {
                    task.cancel(true);
                    continue;
                }
                tShardResources.add(task.get());
            } catch (Exception e) {
                log.error("分片上传任务执行异常，取消相关上传，异常详情：{}", ExceptionUtil.getMessage(e));
                isFailed = true;
            }
        }
        if (isFailed) {
            throw new RuntimeException("分片上传任务执行异常");
        }
        return tShardResources;
    }

    protected String doPreCreateResource(UploadResourceInfo<?> uploadResourceInfo) {
        return preCreateResource(uploadResourceInfo);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.shardResourceUploadExecutor.shutdown();
    }
}
