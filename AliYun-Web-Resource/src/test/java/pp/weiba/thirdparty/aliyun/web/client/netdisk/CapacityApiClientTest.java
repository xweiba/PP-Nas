package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.PersonalInfoResponse;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.WebNetDiskAuthenticationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CapacityApiClientTest extends WebNetDiskAuthenticationTest {

    private final CapacityApiClient capacityApiClient = new CapacityApiClient(httpClient);

    @Test
    void getPersonalInfo() {
        PersonalInfoResponse personalInfo = capacityApiClient.getPersonalInfo();
        assertNotNull(personalInfo);
    }
}
