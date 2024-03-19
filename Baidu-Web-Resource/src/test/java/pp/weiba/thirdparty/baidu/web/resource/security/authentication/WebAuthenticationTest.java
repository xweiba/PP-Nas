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
import pp.weiba.thirdparty.baidu.web.resource.client.AsyncHttpClientAdapter;
import pp.weiba.thirdparty.baidu.web.resource.client.WebBaiduNetDiskHttpClient;
import pp.weiba.thirdparty.baidu.web.resource.client.authentication.WebHttpClientAuthentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials.ManualSetCredentials;

public class WebAuthenticationTest extends DefaultTest {

    private static final String BDUSS = "xseE52MG5Kbk9rLUZ2RW50N3JhWGhDYWJ6MWZVcH5WSVJnWVFOaFdpVFBIeFptSUFBQUFBJCQAAAAAAAAAAAEAAADQP5MkcXExNzM4Mjg5OQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAM-S7mXPku5lSU";

    private static final String STOKEN = "0ccd79a263a30a809e052ae229388c5669cd5aa1b3ec8ffda4e5e86dd8c20cb1";

    // 设置客户端认证信息
    static String businessId = "1";
    static String businessType = "user";

    // 配置当前用户认证信息, 存储中间变量
    protected static IHttpClientAuthentication authentication = new WebHttpClientAuthentication(businessId, businessType);

    // 带授权的客户端
    protected static IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new AsyncHttpClientAdapter(), authentication);

    // 用户认证信息获取接口
    private static final ICredential<Authentication> credential = new ManualSetCredentials(BDUSS, STOKEN);

    // 创建API客户端, 补齐认证信息使用
    public static AuthenticationApiClient authenticationApiClient = new AuthenticationApiClient(httpClient);

    // 补齐认证信息
    private static final IAuthentication<Authentication> baiduWebAuthentication = new BaiduWebAuthentication(businessId, businessType, authenticationApiClient, credential);

    @BeforeAll
    static void initAuthentication() {
        Authentication authentication = baiduWebAuthentication.login();
        System.out.println(JSON.toJSONString(authentication));
    }

    @Test
    void logout() {
        baiduWebAuthentication.logout();
    }
}
