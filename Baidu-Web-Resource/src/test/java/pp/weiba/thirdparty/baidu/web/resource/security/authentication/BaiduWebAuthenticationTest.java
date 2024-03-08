package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import org.junit.jupiter.api.Test;
import pp.weiba.framework.core.client.IHttpClient;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.baidu.web.api.netdisk.base.BaseApiClient;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;
import pp.weiba.thirdparty.baidu.web.resource.client.AsyncHttpClientAdapter;
import pp.weiba.thirdparty.baidu.web.resource.client.WebBaiduNetDiskHttpClient;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials.ManualSetCredentials;

class BaiduWebAuthenticationTest {

    private static final String BDUSS = "29WLVYwNHFjVGh5MzlGeEdkTH5qQkV6ZmZET2phaUpaMH5LSlBPbkdaVVBDcDVsSUFBQUFBJCQAAAAAAAAAAAEAAADQP5MkcXExNzM4Mjg5OQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA99dmUPfXZlb";

    private static final String STOKEN = "c106caae345100264695bd4b59ca8158c517b8a9c5fb077061679b497194e90e";

    protected ICredential<Authentication> credential = new ManualSetCredentials(BDUSS, STOKEN);

    protected IHttpClient httpClient = new WebBaiduNetDiskHttpClient(new AsyncHttpClientAdapter());

    protected BaseApiClient baseApiClient = new BaseApiClient(httpClient);

    protected BaiduWebAuthentication baiduWebAuthentication = new BaiduWebAuthentication("1", "user", baseApiClient, credential);


    @Test
    void test() {
        Authentication authentication = baiduWebAuthentication.buildAuthentication();
    }
}
