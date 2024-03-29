package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.baidu.web.api.netdisk.utils.BaiduNetDiskWebScript;
import pp.weiba.utils.JSONUtils;
import pp.weiba.utils.QRUtils;

import java.io.File;
import java.net.HttpCookie;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
class WebQRScanLonginApiClientTest {


    QrInfo qrResponse;
    LoginQRParams loginQRParams;
    CheckLoginResponse checkLoginResponse;

    private String decode = "https://wappass.baidu.com/wp/?qrlogin&t=1711424040&error=0&sign=e565566abb3969216b11e204e1453072&cmd=login&lp=pc&tpl=netdisk&adapter=3&logPage=pc_loginv5_1711424038%2ClogPage%3Aloginv5&qrloginfrom=pc&local=%E6%AD%A6%E6%B1%89";

    // Web OAUTH 认证信息, 可通过此信息登录百度其他应用
    public static final String OAUTH_AUTHENTICATION_JSON_FILE_PATH = "C:\\Users\\admin\\Documents\\code\\github\\PP-Nas\\Baidu-Web-Api\\src\\test\\resources\\oauthAuthentication.json";

    public static final String NET_DISK_LOGIN_AUTHENTICATION_FILE_PATH = "C:\\Users\\admin\\Documents\\code\\github\\PP-Nas\\Baidu-Web-Api\\src\\test\\resources\\netDiskLoginAuthentication.json";

    @Test
    void getQRImage() {
        String gid = BaiduNetDiskWebScript.loginGid();
        long tt = new Date().getTime();
        String callback = "tangram_guid_" + (tt + 1);
        String logPage = BaiduNetDiskWebScript.loginTraceId() + ",logPage:loginv5";
        long _ = tt + 2;
        loginQRParams = new LoginQRParams()
                .setGid(gid)
                .setTt(tt)
                .setCallback(callback)
                .setLogPage(logPage)
                .set_(_);
        String getQRImageHttpUrlResponseBody = HttpUtil.get(WebQRScanLonginApiClient.getQRImageHttpUrl(loginQRParams));
        qrResponse = WebQRScanLonginApiClient.getQRImageUrl(getQRImageHttpUrlResponseBody);
        // 解析二维码
        decode = QRUtils.decode("https://" + qrResponse.getImgurl());
        // 打印到控制台
        log.info(decode);
        QRUtils.printQr(decode);
    }
    LonginParams longinParams;
    HttpResponse qrLogInResponse;

    @Test
    void loginCheck() {
        getQRImage();
        log.info("等待扫码....");
        while (true) {
            String channelId = qrResponse.getSign();
            String gid = loginQRParams.getGid();
            String callback = loginQRParams.getCallback();
            HttpResponse loginCheck = HttpRequest.get(WebQRScanLonginApiClient.checkScanQRCallbackUrl(channelId, gid, callback)).timeout(600000).execute();
            String responseBodyStr = WebQRScanLonginApiClient.getResponseBodyFormat(loginCheck.body());
            if (responseBodyStr.contains("\"errno\":1")) {
                log.info("等待扫码....");
            }
            if (responseBodyStr.contains("\"errno\":0")) {
                if (responseBodyStr.contains("\\\"status\\\":0")) {
                    log.info("登录成功");
                    checkLoginResponse = WebQRScanLonginApiClient.buildCheckLoginResponse(responseBodyStr);
                    break;
                } else {
                    log.info("已扫码，等待确认....");
                }
            }
        }
    }

    @Test
    void buildQRLoginParams() {
        loginCheck();
        longinParams = WebQRScanLonginApiClient.buildQRLoginParams(checkLoginResponse.getChannelV().getV());
    }

    @Test
    void qrLogIn() {
        buildQRLoginParams();
        String loginUrl = WebQRScanLonginApiClient.qrLogInUrl(longinParams);
        qrLogInResponse = HttpRequest.get(loginUrl).timeout(600000).execute();
        log.info(qrLogInResponse.body());

    }

    @Test
    @Disabled
    void generateLoginAuthentication() {
        qrLogIn();
        String body = WebQRScanLonginApiClient.getResponseBodyFormat(qrLogInResponse.body()).replace("'data'", "\"data\"");
        LoginResponse bean = JSONUtils.toBean(body, LoginResponse.class);
        Map<String, HttpCookie> cookieMap = qrLogInResponse.getCookies().stream().collect(Collectors.toMap(HttpCookie::getName, item -> item));
        WebOAuthLoginAuthentication webOAuthLoginAuthentication = new WebOAuthLoginAuthentication(bean, cookieMap);
        FileUtil.writeString(JSONUtils.toJsonPrettyStr(webOAuthLoginAuthentication), new File(OAUTH_AUTHENTICATION_JSON_FILE_PATH), StandardCharsets.UTF_8);
    }

    @Test
    @Disabled
    void generateBaiduNetDiskLoginAuthentication() {
        HttpRequest httpRequest = AccessTokenApiClientTest.buildHttpRequest("https://pan.baidu.com/disk/home", Method.GET);
        httpRequest.setFollowRedirects(true);
        httpRequest.setFollowRedirectsCookie(true);
        httpRequest.setMaxRedirectCount(100);
        HttpResponse execute = httpRequest.execute();
        List<HttpCookie> cookies = execute.getCookies();
        FileUtil.writeString(JSONUtils.toJsonPrettyStr(cookies), new File(NET_DISK_LOGIN_AUTHENTICATION_FILE_PATH), StandardCharsets.UTF_8);
    }
}