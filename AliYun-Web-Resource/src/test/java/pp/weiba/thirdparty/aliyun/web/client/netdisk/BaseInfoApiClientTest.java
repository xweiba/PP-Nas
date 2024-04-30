package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.PersonalInfoResponse;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.WebNetDiskAuthenticationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseInfoApiClientTest extends WebNetDiskAuthenticationTest {

    private final BaseInfoApiClient baseInfoApiClient = new BaseInfoApiClient(httpClient);

    @Test
    void getPersonalInfo() {
        PersonalInfoResponse personalInfo = baseInfoApiClient.getPersonalInfo();
        assertNotNull(personalInfo);
    }
}
