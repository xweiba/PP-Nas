package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SignInInfoListResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SignInInfoResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SignInRewardResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SignInStatusResponse;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.WebNetDiskAuthenticationTest;

import static org.junit.jupiter.api.Assertions.*;

class SignInApiClientTest extends WebNetDiskAuthenticationTest {

    private SignInInfoResponse signInInfo;

    private SignInInfoListResponse signInInfos;

    @Test
    void getSignInStatusInfo() {
        SignInStatusResponse signInStatusInfo = signInApiClient.todaySignIn();
        assertNotNull(signInStatusInfo);
    }

    @Test
    void getSignInInfo() {
        signInInfo = signInApiClient.getTodaySignInStatusInfo();
        assertNotNull(signInInfo);
    }

    @Test
    void getSignInInfoList() {
        signInInfos = signInApiClient.getSignInInfoList();
        assertNotNull(signInInfos);
    }

    @Test
    void signInReward() {
        getSignInInfo();
        SignInRewardResponse signInReward = signInApiClient.signInReward(signInInfo.getResult().getSignInDay());
        assertNotNull(signInReward);
    }

    @Test
    void signInRewardAll() {
        boolean b = signInApiClient.signInRewardAll();
        assertTrue(b);
    }
}
