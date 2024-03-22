package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.core.client.IHttpClient;
import pp.weiba.framework.core.client.IHttpClientAuthentication;
import pp.weiba.framework.security.authentication.IAuthentication;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.framework.test.DefaultTest;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.baidu.web.resource.client.WebBaiduNetDiskHttpClient;
import pp.weiba.thirdparty.baidu.web.resource.client.ahc.AsyncHttpClientAdapter;
import pp.weiba.thirdparty.baidu.web.resource.client.authentication.WebHttpClientAuthentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials.ManualSetCredentials;

public class WebAuthenticationTest extends DefaultTest {

    private static final String BDUSS = "G5YM1dGWm93QmFONm10OGN2a2RKd0J0b1VIUVotNEZGTmJMQWM3SnlJVzVnU05tSUFBQUFBJCQAAAAAAAAAAAEAAADQP5MkcXExNzM4Mjg5OQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALn0-2W59Ptld";
    // 补齐认证信息
    protected static final IAuthentication<Authentication> baiduWebAuthentication = new BaiduWebAuthentication(businessId, businessType, authenticationApiClient, credential);

    // 设置客户端认证信息
    static String businessId = "1";
    static String businessType = "user";

    // 配置当前用户认证信息, 存储中间变量
    protected static IHttpClientAuthentication authentication = new WebHttpClientAuthentication(businessId, businessType);
    //    protected static IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new HutoolHttpClientAdapter(), authentication);

    // 用户认证信息获取接口
    private static final ICredential<Authentication> credential = new ManualSetCredentials(BDUSS, STOKEN);
    private static final String STOKEN = "98e61fd63f48bfbbb1a25243b904562040a2341acd053422d98b019749e68f01";

    // 创建API客户端, 补齐认证信息使用
    public static AuthenticationApiClient authenticationApiClient = new AuthenticationApiClient(httpClient);
    // 带授权的客户端
    protected static IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new AsyncHttpClientAdapter(), authentication);

    @BeforeAll
    static void initAuthentication() {
        Authentication authentication = baiduWebAuthentication.login();
        System.out.println(JSON.toJSONString(authentication));
    }

    @Test
    void login() {

    }

    //    @Test
    void logout() {
        baiduWebAuthentication.logout();
    }
}
