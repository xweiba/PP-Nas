package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IResourceOperation;
import pp.weiba.framework.resource.ResourceInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.FileOperationApiClient;

class ResourceOperationServiceTest extends ShardUploadResourceServiceTest {

    IResourceOperation resourceOperation = new ResourceOperationService(new FileOperationApiClient(httpClient), shardUploadResourceService);

    @Test
    void createDir() {
        ResourceInfo dir = resourceOperation.createDir("/test/319");
        System.out.println(dir.toString());
    }

    @Test
    void get() {
        resourceOperation.get("891154745348288");
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
