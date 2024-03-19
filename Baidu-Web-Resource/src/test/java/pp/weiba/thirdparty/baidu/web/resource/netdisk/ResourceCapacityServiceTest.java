package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IResourceCapacity;
import pp.weiba.framework.resource.ResourceCapacity;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.BaiduWebAuthenticationTest;

class ResourceCapacityServiceTest extends BaiduWebAuthenticationTest {

    IResourceCapacity resourceCapacity = new ResourceCapacityService(authenticationApiClient);

    @Test
    void getCapacity() {
        ResourceCapacity capacity = resourceCapacity.getCapacity();
        System.out.println(capacity.toString());
    }


}
