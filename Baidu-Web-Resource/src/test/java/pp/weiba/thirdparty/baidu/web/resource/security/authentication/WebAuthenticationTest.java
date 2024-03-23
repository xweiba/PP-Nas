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
import pp.weiba.thirdparty.baidu.web.resource.client.AsyncHttpClientAdapter;
import pp.weiba.thirdparty.baidu.web.resource.client.WebBaiduNetDiskHttpClient;
import pp.weiba.thirdparty.baidu.web.resource.client.authentication.WebHttpClientAuthentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials.ManualSetCredentials;

@Slf4j
public class WebAuthenticationTest extends DefaultTest {

    private static final String BDUSS = "2N-WVdvZERXMDhiNG1mZ2V-bDFmblJTV34xZlhtWkpaQ2xwNno1all2LW40Q0JtSUFBQUFBJCQAAAAAAAAAAAEAAADQP5MkcXExNzM4Mjg5OQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKdT-WWnU~lld";

    private static final String STOKEN = "f8881dc720e7dc90d5e15543df1fce72f09ba55988a731876207fb437a0916cf";

    // 设置客户端认证信息
    static String businessId = "1";
    static String businessType = "user";

    // 配置当前用户认证信息, 存储中间变量
    protected static IHttpClientAuthentication authentication = new WebHttpClientAuthentication(businessId, businessType);
    // 带授权的客户端
    protected static IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new AsyncHttpClientAdapter(), authentication);
//    protected static IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new HutoolHttpClientAdapter(), authentication);

    // 创建API客户端, 补齐认证信息使用
    public static AuthenticationApiClient authenticationApiClient = new AuthenticationApiClient(httpClient);

    // 用户认证信息获取接口
    private static final ICredential<Authentication> credential = new ManualSetCredentials(BDUSS, STOKEN);

    public static final IAuthentication<Authentication> baiduWebAuthentication = new BaiduWebAuthentication(businessId, businessType, authenticationApiClient, credential);

    @BeforeAll
    static void initAuthentication() {
        Authentication authentication = baiduWebAuthentication.login();
        log.info("登录认证信息：{}", authentication.toString());
    }

    @Test
    void login() {

    }

    //    @Test
    void logout() {
        baiduWebAuthentication.logout();
    }
}
