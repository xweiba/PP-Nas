package pp.weiba.thirdparty.aliyun.web.client.security.authentication;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.IHttpClientAuthentication;
import pp.weiba.framework.net.client.adapters.ahc.AsyncHttpClientAdapter;
import pp.weiba.framework.net.client.adapters.hutool.HutoolHttpClientAdapter;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.framework.test.DefaultTest;
import pp.weiba.thirdparty.aliyun.web.client.WebAliYunNetDiskHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.WebHttpClientAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.credentials.JsonStrSetCredentials;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.TokenResponse;
import pp.weiba.thirdparty.aliyun.web.resource.security.authentication.AliYunNetDiskWebAuthentication;
import pp.weiba.utils.FileUtils;

@Log4j2
public class InitAuthenticationTest extends DefaultTest {

    // 设置客户端认证信息
    protected static String businessId = "1";
    protected static String businessType = "user";

    public static final String TOKEN_SAVE_DIR_PATH = "/src/test/resources/token/";

    public static AliYunNetDiskWebAuthentication baiduWebAuthentication;

    public static IHttpClient httpClient;

    public static boolean isHutoolHttpClient = false;

    static void initAhcClientBaiduWebAuthentication() {
        baiduWebAuthentication = buildAliYunNetDiskWebAuthentication();
    }

    static void initHutoolClientBaiduWebAuthentication() {
        isHutoolHttpClient = true;
        baiduWebAuthentication = buildAliYunNetDiskWebAuthentication();
    }

    protected static @NotNull AliYunNetDiskWebAuthentication buildAliYunNetDiskWebAuthentication() {
        // 用户认证信息获取接口
        ICredential<NetDiskAuthentication> netDiskAuthenticationCredential = new JsonStrSetCredentials(getTokenJsonString());
        return new AliYunNetDiskWebAuthentication(businessId, businessType, buildAuthenticationApiClient(), netDiskAuthenticationCredential);
    }

    protected static String getTokenJsonString() {
        return FileUtils.readJsonToWorkDir(TOKEN_SAVE_DIR_PATH, tokenJsonFileName());
    }

    protected static void saveTokenToJsonString(TokenResponse token) {
        FileUtils.saveJsonToWorkDir(token, TOKEN_SAVE_DIR_PATH, tokenJsonFileName());
    }

    protected static String tokenJsonFileName() {
        return businessType + "_" + businessId;
    }


    protected static IHttpClientAuthentication buildHttpClientAuthentication() {
        // 配置当前用户认证信息, 存储中间变量
        return new WebHttpClientAuthentication(businessId, businessType);
    }

    public static IHttpClient buildHutoolHttpClient() {
        return new WebAliYunNetDiskHttpClient(new HutoolHttpClientAdapter(), buildHttpClientAuthentication());
    }

    protected static IHttpClient buildAHCHttpClient() {
        return new WebAliYunNetDiskHttpClient(new AsyncHttpClientAdapter(), buildHttpClientAuthentication());
    }


    protected static AuthenticationApiClient buildAuthenticationApiClient() {
        initAuthentication();
        return new AuthenticationApiClient(httpClient);
    }

    protected static void initAuthentication() {
        if (isHutoolHttpClient) {
            httpClient = buildHutoolHttpClient();
        } else {
            httpClient = buildAHCHttpClient();
        }
    }
}
