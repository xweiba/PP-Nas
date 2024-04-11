package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IResourceOperation;
import pp.weiba.framework.resource.model.ResourceInfo;
import pp.weiba.thirdparty.baidu.web.client.netdisk.FileOperationApiClient;
import pp.weiba.utils.LogUtils;

@Log4j2
class ResourceOperationServiceTest extends ShardUploadResourceServiceTest {

    IResourceOperation resourceOperation = new ResourceOperationService(new FileOperationApiClient(httpClient), shardUploadResourceService);

    @Test
    void createDir() {
        LogUtils.info(log, resourceOperation.createDir("/test/319"));
    }


    ResourceInfo resourceInfo = null;

    @Test
    void get() {
        resourceInfo = resourceOperation.get("478735016803432");
        LogUtils.info(log, resourceInfo);
    }

    @Test
    void delete() {
        LogUtils.info(log, resourceOperation.delete(new ResourceInfo().setPath("/111/111/X1sL/test.txt")));
    }

    @Test
    void rename() {
        get();
        LogUtils.info(log, resourceOperation.rename(new ResourceInfo().setId(resourceInfo.getId()).setPath(resourceInfo.getPath()), "test.txt"));
    }

    @Test
    void move() {
        get();
        LogUtils.info(log, resourceOperation.move(new ResourceInfo().setPath(resourceInfo.getPath()), new ResourceInfo().setPath("/111/111/" + RandomUtil.randomString(4)).setName(resourceInfo.getName())));
    }

    @Test
    void copy() {
        get();
        LogUtils.info(log, resourceOperation.copy(new ResourceInfo().setPath(resourceInfo.getPath()), new ResourceInfo().setPath("/111/111/" + RandomUtil.randomString(4)).setName(resourceInfo.getName())));
    }
}
