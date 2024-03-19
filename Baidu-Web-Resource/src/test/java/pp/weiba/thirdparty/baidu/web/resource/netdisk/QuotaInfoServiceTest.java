package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IQuotaManager;
import pp.weiba.framework.resource.QuotaInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.CapacityApiClient;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.WebAuthenticationTest;

class QuotaInfoServiceTest extends WebAuthenticationTest {

    IQuotaManager resourceCapacity = new QuotaManagerService(new CapacityApiClient(httpClient));

    @Test
    void getCapacity() {
        QuotaInfo capacity = resourceCapacity.getCapacity();
        System.out.println(capacity.toString());
    }


}
