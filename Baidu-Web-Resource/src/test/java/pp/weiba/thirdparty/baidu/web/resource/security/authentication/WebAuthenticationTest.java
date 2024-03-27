package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.core.client.IHttpClient;
import pp.weiba.framework.core.client.IHttpClientAuthentication;
import pp.weiba.framework.security.authentication.IAuthentication;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.framework.test.DefaultTest;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.baidu.web.resource.client.HutoolHttpClientAdapter;
import pp.weiba.thirdparty.baidu.web.resource.client.WebBaiduNetDiskHttpClient;
import pp.weiba.thirdparty.baidu.web.resource.client.authentication.WebHttpClientAuthentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials.ManualSetCredentials;

@Slf4j
public class WebAuthenticationTest extends DefaultTest {

    private static final String BDUSS = "k1pTkNVdm9Ib01zNE5yWmRLZmJ2MU5oRkFoR09PWFM0NEhDV1BHdEQxNTl5U2htSUFBQUFBJCQAAAAAAAAAAAEAAADQP5MkcXExNzM4Mjg5OQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH08AWZ9PAFmY";

    private static final String STOKEN = "85af6a69e0cc286989d75c1e43f9a2c5c8830cefc0f59352847eac97d6c2fc7f";

    private static final String BAIDUID = "4231181926BBEE655529ED3D6E52E67B:FG=1";

    // 设置客户端认证信息
    static String businessId = "1";
    static String businessType = "user";

    // 配置当前用户认证信息, 存储中间变量
    protected static IHttpClientAuthentication authentication = new WebHttpClientAuthentication(businessId, businessType);
    // 用户认证信息获取接口
    private static final ICredential<Authentication> credential = new ManualSetCredentials(BDUSS, STOKEN, BAIDUID);
    // 带授权的客户端
    //    protected static IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new AsyncHttpClientAdapter(), authentication);
    protected static IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new HutoolHttpClientAdapter(), authentication);

    // 创建API客户端, 补齐认证信息使用
    public static AuthenticationApiClient authenticationApiClient = new AuthenticationApiClient(httpClient);

    public static final IAuthentication<Authentication> baiduWebAuthentication = new BaiduWebAuthentication(businessId, businessType, authenticationApiClient, credential);

    @BeforeAll
    static void initAuthentication() {
        Authentication authentication = baiduWebAuthentication.login();
        log.info("登录认证信息：{}", authentication.toString());
    }

    @Test
    void login() {

    }

    //    @AfterAll
    static void logout() {
        baiduWebAuthentication.logout();
    }


    @Test
    void test() {

    }
}
