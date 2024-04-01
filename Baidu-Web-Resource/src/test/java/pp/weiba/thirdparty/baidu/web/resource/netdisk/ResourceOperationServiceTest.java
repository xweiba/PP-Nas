package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IResourceOperation;
import pp.weiba.framework.resource.ResourceInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.FileOperationApiClient;
import pp.weiba.utils.Log;

@Log4j2
class ResourceOperationServiceTest extends ShardUploadResourceServiceTest {

    IResourceOperation resourceOperation = new ResourceOperationService(new FileOperationApiClient(httpClient), shardUploadResourceService);

    @Test
    void createDir() {
        ResourceInfo dir = resourceOperation.createDir("/test/319");
        Log.info(log, "{}", dir);
    }

    @Test
    void get() {
        ResourceInfo resourceInfo = resourceOperation.get("891154745348288");
        Log.info(log, "{}", resourceInfo);
    }

    @Test
    void delete() {

    }

    @Test
    void rename() {
    }

    @Test
    void move() {
    }

    @Test
    void copy() {
    }
}
