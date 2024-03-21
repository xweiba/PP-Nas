package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IShardUploadResource;
import pp.weiba.framework.resource.UploadResourceInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.UploadFileApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.FileChunk;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.WebAuthenticationTest;

import java.io.File;

class ShardUploadResourceServiceTest extends WebAuthenticationTest {

    IShardUploadResource<UploadEntity, FileChunk> shardUploadResourceService = new ShardUploadResourceService(new UploadFileApiClient(httpClient));

    String dstUploadFileDir = "/test/319/";
    String uploadFilePath = "C:\\Users\\admin\\Downloads\\graalvm-ee-java11-windows-amd64-21.2.0.1.zip";

    UploadEntity uploadEntity = new UploadEntity().setFile(new File(uploadFilePath));
    UploadResourceInfo<UploadEntity> uploadResourceInfo = new UploadResourceInfo<UploadEntity>().setEntity(uploadEntity).setDstDirPath(dstUploadFileDir);

    @Test
    void upload() {
        //
        uploadEntity.setUk(baiduWebAuthentication.login().getLoginInfo().getUkStr());
        String upload = shardUploadResourceService.upload(uploadResourceInfo);
        System.out.println(upload);
    }

    @Test
    void checkResourceExist() {
        /*
         * 秒传策略（现象猜测）：
         * 1. 当目录下存在这个文件，秒传成功。
         * 2. 当目录下存在这个文件，但是被删除了，返回404。
         * 3. 当目录从未存在这个文件，但服务器有数据，秒传成功（首次上传的公开大文件大概率会秒传成功）。
         * */
    }

    @Test
    void preCreateResource() {

    }

    @Test
    void shardResourceBuild() {
    }

    @Test
    void shardResourceUpload() {
    }

    @Test
    void completeResourceUpload() {
    }
}
