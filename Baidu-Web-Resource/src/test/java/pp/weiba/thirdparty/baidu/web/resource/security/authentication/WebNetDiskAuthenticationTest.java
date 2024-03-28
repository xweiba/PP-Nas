package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.core.client.IHttpClient;
import pp.weiba.framework.core.client.IHttpClientAuthentication;
import pp.weiba.framework.security.authentication.IAuthentication;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.framework.test.DefaultTest;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.baidu.web.resource.client.WebBaiduNetDiskHttpClient;
import pp.weiba.thirdparty.baidu.web.resource.client.ahc.AsyncHttpClientAdapter;
import pp.weiba.thirdparty.baidu.web.resource.client.authentication.WebHttpClientAuthentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials.JSONCredentials;

@Slf4j
public class WebNetDiskAuthenticationTest extends DefaultTest {

    // 设置客户端认证信息
    static String businessId = "1";
    static String businessType = "user";

    // 配置当前用户认证信息, 存储中间变量
    protected static IHttpClientAuthentication authentication = new WebHttpClientAuthentication(businessId, businessType);
    // 用户认证信息获取接口
    private static final ICredential<NetDiskAuthentication> credential = new JSONCredentials("C:\\Users\\admin\\Documents\\code\\github\\PP-Nas\\Baidu-Web-Api\\src\\test\\resources\\loginAuthentication.json");
    //    protected static IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new HutoolHttpClientAdapter(), authentication);
    // 带授权的客户端
    protected static IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new AsyncHttpClientAdapter(), authentication);

    // 创建API客户端, 补齐认证信息使用
    public static AuthenticationApiClient authenticationApiClient = new AuthenticationApiClient(httpClient);

    public static final IAuthentication<NetDiskAuthentication> baiduWebAuthentication = new BaiduNetDiskWebAuthentication(businessId, businessType, authenticationApiClient, credential);

    @BeforeAll
    static void initAuthentication() {
        NetDiskAuthentication netDiskAuthentication = baiduWebAuthentication.login();
        log.info("登录认证信息：{}", netDiskAuthentication.toString());
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
