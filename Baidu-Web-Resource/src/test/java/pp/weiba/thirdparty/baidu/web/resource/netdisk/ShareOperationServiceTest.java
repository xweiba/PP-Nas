package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IShareOperation;
import pp.weiba.framework.resource.ShareInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.ShareOperationApiClient;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.WebNetDiskAuthenticationTest;
import pp.weiba.utils.LogUtils;

@Log4j2
class ShareOperationServiceTest extends WebNetDiskAuthenticationTest {


    IShareOperation resourceRecycle = new ShareOperationService(new ShareOperationApiClient(httpClient));

    @Test
    void create() {
        ShareInfo result = resourceRecycle.create("891154745348288", RandomUtil.randomString(4), 1);
        LogUtils.info(log, result);
    }

    @Test
    void cancel() {
    }

    @Test
    void get() {
    }

    @Test
    void save() {
    }
}
