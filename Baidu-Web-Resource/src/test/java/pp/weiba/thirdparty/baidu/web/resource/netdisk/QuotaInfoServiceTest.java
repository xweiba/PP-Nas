package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IQuotaManager;
import pp.weiba.framework.resource.QuotaInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.CapacityApiClient;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.WebNetDiskAuthenticationTest;

@Log4j2
class QuotaInfoServiceTest extends WebNetDiskAuthenticationTest {

    IQuotaManager resourceCapacity = new QuotaManagerService(new CapacityApiClient(httpClient));

    @Test
    void getCapacity() {
        QuotaInfo capacity = resourceCapacity.getCapacity();
        log.info("网盘容量：{}， 已使用：{}", capacity.getTotal(), capacity.getUse());
    }

}
