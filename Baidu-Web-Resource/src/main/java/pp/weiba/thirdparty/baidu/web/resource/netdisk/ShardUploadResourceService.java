package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.AbstractShardUploadResource;
import pp.weiba.framework.resource.model.ShardResource;
import pp.weiba.framework.resource.model.UploadResourceInfo;
import pp.weiba.thirdparty.baidu.web.client.netdisk.UploadFileApiClient;
import pp.weiba.thirdparty.baidu.web.client.netdisk.request.FileChunk;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.CheckFileExistResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.CompleteUploadFileResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.PreCreateFileResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.UploadFileChunkResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分片上传服务
 *
 * @author weiba
 * @date 2024/3/21 11:23
 */
@Log4j2
public class ShardUploadResourceService extends AbstractShardUploadResource<UploadEntity, FileChunk> {

    private final UploadFileApiClient uploadFileApiClient;

    public ShardUploadResourceService(UploadFileApiClient uploadFileApiClient) {
        this.uploadFileApiClient = uploadFileApiClient;
    }


    @Override
    public String preCreateResource(UploadResourceInfo<UploadEntity> uploadResourceInfo) {
        UploadEntity entity = uploadResourceInfo.getEntity();
        File file = entity.getFile();
        PreCreateFileResponse preCreateFileResponse = uploadFileApiClient.preCreateFile(entity.getDstFilePath(), uploadResourceInfo.getDstDirPath(), file.length(), FileUtil.lastModifiedTime(file));
        return preCreateFileResponse.getUploadid();
    }

    @Override
    public List<ShardResource<FileChunk>> shardResourceBuild(UploadResourceInfo<UploadEntity> uploadResourceInfo) {
        UploadEntity entity = uploadResourceInfo.getEntity();
        List<FileChunk> fileChunks = uploadFileApiClient.buildFileChunks(entity.getFile());
        List<ShardResource<FileChunk>> shardResources = null;
        if (CollUtil.isNotEmpty(fileChunks)) {
            shardResources = new ArrayList<>();
            for (FileChunk fileChunk : fileChunks) {
                shardResources.add(new ShardResource<>(fileChunk, fileChunk.getPartSeq()));
            }
        }
        return shardResources;
    }

    @Override
    public ShardResource<FileChunk> shardResourceUpload(UploadResourceInfo<UploadEntity> uploadResourceInfo, ShardResource<FileChunk> shardResource) {
        FileChunk entity = shardResource != null ? shardResource.getEntity() : null;
        UploadFileChunkResponse uploadFileChunkResponse = uploadFileApiClient.uploadFileChunk(uploadResourceInfo.getUploadId(), uploadResourceInfo.getDstDirPath(), uploadResourceInfo.getEntity().getFile(), entity);
        if (entity == null) {
            entity = new FileChunk();
            shardResource = new ShardResource<FileChunk>().setEntity(entity).setPartSeq(0);
        }
        entity.setMd5(uploadFileChunkResponse.getMd5());
        return shardResource;
    }

    @Override
    public String completeResourceUpload(UploadResourceInfo<UploadEntity> uploadResourceInfo, List<ShardResource<FileChunk>> shards) {
        CompleteUploadFileResponse completeUploadFileResponse = uploadFileApiClient.completeUploadFile(uploadResourceInfo.getUploadId(), uploadResourceInfo.getEntity().getDstFilePath(), uploadResourceInfo.getDstDirPath(), FileUtil.lastModifiedTime(uploadResourceInfo.getEntity().getFile()), uploadResourceInfo.getEntity().getFile().length(), shards.stream().map(shard -> shard.getEntity().getMd5()).collect(Collectors.toList()));
        return completeUploadFileResponse.getFsId();
    }


    @Override
    public String checkResourceExist(UploadResourceInfo<UploadEntity> uploadResourceInfo) {
        UploadEntity entity = uploadResourceInfo.getEntity();

        String dstFilePath = uploadResourceInfo.getDstDirPath() + entity.getFile().getName();
        entity.setDstFilePath(dstFilePath);

        CheckFileExistResponse duplicateDetectionResponse = uploadFileApiClient.checkFileExist(entity.getDstFilePath(), uploadResourceInfo.getDstDirPath(), entity.getFile(), entity.getUk());
        if (duplicateDetectionResponse != null && duplicateDetectionResponse.getInfo() != null && StrUtil.isNotEmpty(duplicateDetectionResponse.getInfo().getFsId())) {
            return duplicateDetectionResponse.getInfo().getFsId();
        }
        return null;
    }
}
