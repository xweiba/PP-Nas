package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.*;
import pp.weiba.thirdparty.baidu.web.api.netdisk.XpanUrlConstants;

import java.util.Date;

/**
 * access_token 相关接口, XPan 接口都需要该参数, 登录后使用
 *
 * @author weiba
 * @date 2024/3/27 11:26
 */
@Log4j2
public class AccessTokenApiClient extends AbstractApiHttpClient {

    public AccessTokenApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    public AccessToken getAccessToken() {
        // 必须扫码或短信等方式登录拿到完整Cookie，再获取，否则会获取失败，因为它中间会跳转oath。
        HttpRequest request = HttpRequest.urlFormatBuilder(Method.GET, XpanUrlConstants.GET_ACCESS_TOKEN)
                .setFollowRedirect(true)
                .setFollowRedirectsCookie(true)
                .setMaxRedirectCount(2);
        HttpResponse httpResponse = executeResponse(request);
        if (httpResponse.getStatusCode() == 302) {
            String locationUrl = httpResponse.getHeaders().get("Location");
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
                String accessToken = String.valueOf(query.get("access_token"));
                long expiresIn = Long.parseLong(String.valueOf(query.get("expires_in")));
                String session_secret = String.valueOf(query.get("session_secret"));
                String scope = String.valueOf(query.get("scope"));
                return new AccessToken(new Date(expiresIn), accessToken, session_secret, scope);
            }
            log.error("获取accessToken失败, locationUrl异常：{}", locationUrl);
        }
        String msg = "获取accessToken失败, 账号登录状态异常, 请核实Cookies是否正常！";
        log.error(msg);
        throw new RuntimeException(msg);
    }

}
