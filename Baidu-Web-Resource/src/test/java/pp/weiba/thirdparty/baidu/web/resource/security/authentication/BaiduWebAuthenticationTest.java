package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.core.client.IHttpClient;
import pp.weiba.framework.core.client.IHttpClientAuthentication;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.baidu.web.api.netdisk.base.BaseApiClient;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;
import pp.weiba.thirdparty.baidu.web.resource.client.AsyncHttpClientAdapter;
import pp.weiba.thirdparty.baidu.web.resource.client.WebBaiduNetDiskHttpClient;
import pp.weiba.thirdparty.baidu.web.resource.client.authentication.WebBaiduHttpClientAuthentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials.ManualSetCredentials;

class BaiduWebAuthenticationTest {

    private static final String BDUSS = "29WLVYwNHFjVGh5MzlGeEdkTH5qQkV6ZmZET2phaUpaMH5LSlBPbkdaVVBDcDVsSUFBQUFBJCQAAAAAAAAAAAEAAADQP5MkcXExNzM4Mjg5OQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA99dmUPfXZlb";

    private static final String STOKEN = "c106caae345100264695bd4b59ca8158c517b8a9c5fb077061679b497194e90e";

    // 设置客户端认证信息
    static String businessId = "1";
    static String businessType = "user";

    // 配置当前用户认证信息, 存储中间变量
    protected static IHttpClientAuthentication authentication = new WebBaiduHttpClientAuthentication(businessId, businessType);

    // 带授权的客户端
    protected static IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new AsyncHttpClientAdapter(), authentication);

    @BeforeAll
    static void initAuthentication() {
        // 用户认证信息获取接口
        ICredential<Authentication> credential = new ManualSetCredentials(BDUSS, STOKEN);

        // 创建API客户端, 补齐认证信息使用
        BaseApiClient baseApiClient = new BaseApiClient(httpClient);

        // 补齐认证信息
        BaiduWebAuthenticationBuilder baiduWebAuthentication = new BaiduWebAuthenticationBuilder(businessId, businessType, baseApiClient, credential);
        Authentication authentication = baiduWebAuthentication.buildAuthentication();
        System.out.println(JSON.toJSONString(authentication));

    }

    @Test
    void test() {
    }
}
