package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IShardUploadResource;
import pp.weiba.framework.resource.ShardResource;
import pp.weiba.framework.resource.UploadResourceInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.UploadFileApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.FileChunk;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.WebAuthenticationTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
class ShardUploadResourceServiceTest extends WebAuthenticationTest {

    IShardUploadResource<UploadEntity, FileChunk> shardUploadResourceService = new ShardUploadResourceService(new UploadFileApiClient(httpClient));

    static String dstUploadFileDir = "/111/111/";
    static String uploadDir = "C:\\Users\\admin\\Downloads\\";
    static String uploadFilePath = uploadDir + "b3405a0364693f72c1051dfa967c00cb_1_5.pdf";
    static File uploadFile = new File(uploadFilePath);

    static UploadEntity uploadEntity = new UploadEntity().setFile(uploadFile).setDstFilePath(dstUploadFileDir + uploadFile.getName());
    UploadResourceInfo<UploadEntity> uploadResourceInfo = new UploadResourceInfo<UploadEntity>().setEntity(uploadEntity).setDstDirPath(dstUploadFileDir);

    // 单测时使用手动指定id
    private final String manuallySpecifyUploadId = "P1-MTAuMTUxLjQzLjIyOjE3MTEzMzM4OTU6ODg3NTg5ODU2OTc1OTkyMjMwNg==";
    private List<ShardResource<FileChunk>> shardResources;

    @BeforeAll
    static void init() {
        uploadEntity.setUk(baiduWebAuthentication.login().getLoginInfo().getUkStr());
    }

    @Test
    void checkResourceExist() {
        /*
         * 秒传策略（现象猜测）：
         * 1. 当目录下存在这个文件，秒传成功。
         * 2. 当你的账号从未存在这个文件，但服务器有数据，会秒传成功。
         * 3. 当你的账号曾经存在在这个文件，但是被手动删除了，会返回404，秒传过后应该是插入了一条记录，估计是为了防止有人用秒传做无限空间这种事, 可能有过期时间？。
         * */
        String checkResourceExist = shardUploadResourceService.checkResourceExist(uploadResourceInfo);
        log.info("秒传返回信息:{} (null 表示没有这个文件)", checkResourceExist);
    }

    @Test
    void preCreateResource() {
        String uploadId = shardUploadResourceService.preCreateResource(uploadResourceInfo);
        uploadResourceInfo.setUploadId(uploadId);
        log.info("预创建返回uploadId:{}", uploadId);
    }

    void initUploadResourceInfo() {
        if (StrUtil.isBlank(uploadResourceInfo.getUploadId())) {
            if (StrUtil.isBlank(manuallySpecifyUploadId)) {
                throw new IllegalArgumentException("UploadId 不能为空");
            }
            uploadResourceInfo.setUploadId(manuallySpecifyUploadId);
        }
    }

    @Test
    void shardResourceBuild() {
        initUploadResourceInfo();
        shardResources = shardUploadResourceService.shardResourceBuild(uploadResourceInfo);
        if (CollUtil.isNotEmpty(shardResources)) {
            log.info("分片信息:{}", shardResources.toString());
        } else {
            log.info("文件过小不分片");
        }
    }


    List<ShardResource<FileChunk>> fileChunkShardResources;
    @Test
    void shardResourceUpload() {
        initUploadResourceInfo();
        shardResourceBuild();
        fileChunkShardResources = new ArrayList<>();
        if (CollUtil.isEmpty(shardResources)) {
            fileChunkShardResources.add(shardUploadResourceService.shardResourceUpload(uploadResourceInfo, null));
        } else {
            for (ShardResource<FileChunk> shardResource : shardResources) {
                fileChunkShardResources.add(shardUploadResourceService.shardResourceUpload(uploadResourceInfo, shardResource));
            }
        }
        log.info("分片上传信息:{}", fileChunkShardResources.toString());
    }

    @Test
    void completeResourceUpload() {
        shardResourceUpload();
        String completeResourceUpload = shardUploadResourceService.completeResourceUpload(uploadResourceInfo, fileChunkShardResources);
        log.info("完成上传返回信息:{}", completeResourceUpload);
    }

    @Test
    void upload() {
        String upload = shardUploadResourceService.upload(uploadResourceInfo);
        log.info("上传完成：{}", upload);
    }

}
