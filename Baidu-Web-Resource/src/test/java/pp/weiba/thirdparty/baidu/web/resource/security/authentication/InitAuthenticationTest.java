package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import cn.hutool.core.io.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.core.client.IHttpClient;
import pp.weiba.framework.core.client.IHttpClientAuthentication;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.framework.test.DefaultTest;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.baidu.web.client.WebBaiduNetDiskHttpClient;
import pp.weiba.thirdparty.baidu.web.resource.client.HutoolHttpClientAdapter;
import pp.weiba.thirdparty.baidu.web.resource.client.ahc.AsyncHttpClientAdapter;
import pp.weiba.thirdparty.baidu.web.resource.client.authentication.WebHttpClientAuthentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials.NetDiskJSONCredentials;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials.OAuthJSONCredentials;
import pp.weiba.utils.JSONUtils;

import java.nio.charset.Charset;

@Log4j2
public class InitAuthenticationTest extends DefaultTest {

    // Web OAUTH 认证信息, 可通过此信息登录百度其他应用
    public static final String OAUTH_AUTHENTICATION_JSON_FILE_PATH = "C:\\Users\\admin\\Documents\\code\\github\\PP-Nas\\Baidu-Web-Api\\src\\test\\resources\\oauthAuthentication.json";

    // 百度网盘认证信息，需要通过此接口初始化
    public static final String NET_DISK_AUTHENTICATION_JSON_FILE_PATH = "C:\\Users\\admin\\Documents\\code\\github\\PP-Nas\\Baidu-Web-Resource\\src\\test\\resources\\netDiskAuthentication.json";

    // 设置客户端认证信息
    protected static String businessId = "1";
    protected static String businessType = "user";

    public static BaiduNetDiskWebAuthentication baiduWebAuthentication;

    public static IHttpClient httpClient;

    public static boolean isHutoolHttpClient = false;

    static void initAhcClientBaiduWebAuthentication() {
        baiduWebAuthentication = buildBaiduNetDiskWebAuthentication();
    }

    static void initHutoolClientBaiduWebAuthentication() {
        isHutoolHttpClient = true;
        baiduWebAuthentication = buildBaiduNetDiskWebAuthentication();
    }

    protected static @NotNull BaiduNetDiskWebAuthentication buildBaiduNetDiskWebAuthentication() {
        // 用户认证信息获取接口
        ICredential<NetDiskAuthentication> netDiskAuthenticationCredential = new NetDiskJSONCredentials(NET_DISK_AUTHENTICATION_JSON_FILE_PATH);
        return new BaiduNetDiskWebAuthentication(businessId, businessType, buildAuthenticationApiClient(), netDiskAuthenticationCredential);
    }

    /**
     * 生成网盘认证json信息，可以通过二维码扫描测试生成。
     *
     * @author weiba
     * @date 2024/3/29 14:19
     */
    @Test
    @Disabled
    void generateNetDiskAuthenticationJsonFile() {
        // 通过 oauth 获取网盘认证信息
        ICredential<NetDiskAuthentication> oauthCredentials = new OAuthJSONCredentials(OAUTH_AUTHENTICATION_JSON_FILE_PATH);

        // 使用oauthCredentials获取网盘认证信息
        baiduWebAuthentication = new BaiduNetDiskWebAuthentication(businessId, businessType, buildAuthenticationApiClient(), oauthCredentials);

        NetDiskAuthentication netDiskAuthentication = baiduWebAuthentication.login();

        FileUtil.writeString(JSONUtils.toJsonPrettyStr(netDiskAuthentication), NET_DISK_AUTHENTICATION_JSON_FILE_PATH, Charset.defaultCharset());
        log.debug("登录认证信息：{}", netDiskAuthentication);
    }

    protected static IHttpClientAuthentication buildHttpClientAuthentication() {
        // 配置当前用户认证信息, 存储中间变量
        return new WebHttpClientAuthentication(businessId, businessType);
    }

    public static IHttpClient buildHutoolHttpClient() {
        return new WebBaiduNetDiskHttpClient(new HutoolHttpClientAdapter(), buildHttpClientAuthentication());
    }

    protected static IHttpClient buildAHCHttpClient() {
        return new WebBaiduNetDiskHttpClient(new AsyncHttpClientAdapter(), buildHttpClientAuthentication());
    }


    protected static AuthenticationApiClient buildAuthenticationApiClient() {
        if (isHutoolHttpClient) {
            httpClient = buildHutoolHttpClient();
        } else {
            httpClient = buildAHCHttpClient();
        }
        return new AuthenticationApiClient(httpClient);
    }

}
