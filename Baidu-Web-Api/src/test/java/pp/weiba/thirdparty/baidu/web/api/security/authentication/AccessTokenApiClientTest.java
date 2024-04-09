package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.baidu.web.api.netdisk.XpanUrlConstants;

import java.net.HttpCookie;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;

@Log4j2
class AccessTokenApiClientTest {

    public static final WebOAuthLoginAuthentication WEB_O_AUTH_LOGIN_AUTHENTICATION = JSON.parseObject(FileUtil.readString("C:\\Users\\admin\\Documents\\code\\github\\PP-Nas\\Baidu-Web-Api\\src\\test\\resources\\oauthAuthentication.json", StandardCharsets.UTF_8), WebOAuthLoginAuthentication.class);

    AccessToken accessToken;
    private String locationUrl;

    public static HttpRequest buildHttpRequest(String url, Method method) {
        Collection<HttpCookie> cookies = WEB_O_AUTH_LOGIN_AUTHENTICATION.getCookieMap().values();
        // 调用 openapi authorize 页面获取 ACCESS_TOKEN
        HttpRequest httpRequest = HttpRequest.of(url).setMethod(method);
        httpRequest.cookie(cookies);
        return httpRequest;
    }

    @Test
    void getAccessToken() {
        // 调用 openapi authorize 页面获取 ACCESS_TOKEN
        HttpResponse httpResponse = buildHttpRequest(XpanUrlConstants.GET_ACCESS_TOKEN, Method.GET)
                .setFollowRedirects(true)
                .setFollowRedirectsCookie(true)
                .setMaxRedirectCount(2)
                .execute();

        log.debug("getAccessToken: {}", httpResponse.body());

        if (httpResponse.getStatus() == 302) {
            locationUrl = httpResponse.header("Location");
            // 有 OAUTHSTOKEN 才能获取到
            if (StrUtil.isNotBlank(locationUrl) && locationUrl.contains("access_token")) {
                // [https://openapi.baidu.com/oauth/2.0/login_success#expires_in=2592000&access_token=123.967deccce2d8ddcf833a88f23f839882.YDtogn0r5gj81Shl17DK5pP5D-S7-QhShgPnZZD.UKaJFA&session_secret=&session_key=&scope=basic+netdisk]
                log.debug("获取accessToken成功, locationUrl:{}", locationUrl);
                if (locationUrl.startsWith("[") && locationUrl.endsWith("]")) {
                    locationUrl = locationUrl.substring(1, locationUrl.length() - 1);
                }
                if (locationUrl.startsWith("https://openapi.baidu.com/oauth/2.0/login_success#")) {
                    locationUrl = locationUrl.replace("#", "?");
                }
                UrlQuery query = UrlBuilder.ofHttp(locationUrl).getQuery();
                String accessTokenStr = String.valueOf(query.get("access_token"));
                Long expiresIn = Long.valueOf(String.valueOf(query.get("expires_in")));
                String session_secret = String.valueOf(query.get("session_secret"));
                String scope = String.valueOf(query.get("scope"));
                accessToken = new AccessToken(new Date(expiresIn), accessTokenStr, session_secret, scope);
                return;
            }
            log.error("获取accessToken失败, 需要获取OAUTHSTOKEN，locationUrl：{}", locationUrl);
        }
        log.error("获取accessToken异常，未正常302");

    }
}
