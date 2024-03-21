package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.AbstractShardUploadResource;
import pp.weiba.framework.resource.ShardResource;
import pp.weiba.framework.resource.UploadResourceInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.UploadFileApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.FileChunk;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.BaiduNetDiskWebUploadFileResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.CreateFileResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.FileDuplicateDetectionResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.PreCreateFileResponse;

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
        PreCreateFileResponse preCreateFileResponse = uploadFileApiClient.preCreateFile(entity.getDstFilePath(), entity.getDstDirPath(), file.length(), FileUtil.lastModifiedTime(file));
        return preCreateFileResponse.getUploadid();
    }

    @Override
    public List<ShardResource<FileChunk>> shardResourceBuild(UploadResourceInfo<UploadEntity> uploadResourceInfo) {
        UploadEntity entity = uploadResourceInfo.getEntity();
        List<FileChunk> fileChunks = uploadFileApiClient.generateFileChunks(entity.getFile());
        List<ShardResource<FileChunk>> shardResources = new ArrayList<>();
        for (FileChunk fileChunk : fileChunks) {
            shardResources.add(new ShardResource<>(fileChunk, fileChunk.getPartSeq()));
        }
        return shardResources;
    }

    @Override
    public ShardResource<FileChunk> shardResourceUpload(UploadResourceInfo<UploadEntity> uploadResourceInfo, ShardResource<FileChunk> shardResource) {
        FileChunk entity = shardResource.getEntity();
        BaiduNetDiskWebUploadFileResponse baiduNetDiskWebUploadFileResponse = uploadFileApiClient.shardResourceUpload(uploadResourceInfo.getUploadId(), uploadResourceInfo.getEntity().getFile(), entity, uploadResourceInfo.getDstDirPath());
        entity.setMd5(baiduNetDiskWebUploadFileResponse.getMd5());
        return shardResource;
    }

    @Override
    public String completeResourceUpload(UploadResourceInfo<UploadEntity> uploadResourceInfo, List<ShardResource<FileChunk>> shards) {
        CreateFileResponse createFileResponse = uploadFileApiClient.completeCreateFile(uploadResourceInfo.getUploadId(), uploadResourceInfo.getEntity().getDstFilePath(), uploadResourceInfo.getEntity().getDstDirPath(), FileUtil.lastModifiedTime(uploadResourceInfo.getEntity().getFile()), uploadResourceInfo.getEntity().getFile().length(), shards.stream().map(shard -> shard.getEntity().getMd5()).collect(Collectors.toList()));
        return createFileResponse.getFsId();
    }


    @Override
    public String checkResourceExist(UploadResourceInfo<UploadEntity> uploadResourceInfo) {
        UploadEntity entity = uploadResourceInfo.getEntity();
        FileDuplicateDetectionResponse duplicateDetectionResponse = uploadFileApiClient.uploadFileDuplicateCheck(entity.getDstFilePath(), entity.getDstDirPath(), entity.getFile(), entity.getUk());
        if (duplicateDetectionResponse != null && duplicateDetectionResponse.getInfo() != null && StrUtil.isNotEmpty(duplicateDetectionResponse.getInfo().getFsId())) {
            return duplicateDetectionResponse.getInfo().getFsId();
        }
        return null;
    }
}
