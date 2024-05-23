package pp.weiba.thirdparty.baidu.web.client.security.authentication;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.net.client.model.HttpResponse;
import pp.weiba.framework.net.client.model.Method;
import pp.weiba.thirdparty.baidu.web.client.netdisk.UrlConstants;
import pp.weiba.thirdparty.baidu.web.client.netdisk.XpanUrlConstants;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.LoginStatusResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.TemplateVariableResponse;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.WebOAuthLoginAuthentication;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.response.AccessToken;

import java.util.Date;
import java.util.HashMap;

/**
 * 认证信息
 *
 * @author weiba
 * @date 2024/3/7 9:50
 */
@Log4j2
public class AuthenticationApiClient extends AbstractApiHttpClient {

    public AuthenticationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 获取页面默认模板变量
     *
     * @return 模板变量
     * @author weiba
     * @date 2024/3/7 14:57
     */
    public TemplateVariableResponse getTemplateVariable() {
        String pageTemplateVariableFields = "\"bdstoken\",\"token\",\"uk\",\"isdocuser\",\"servertime\"";
        return getPageTemplateVariable(pageTemplateVariableFields, new TypeReference<TemplateVariableResponse>() {
        });
    }

    /**
     * 获取页面模板变量
     *
     * @param fields 模板变量
     * @return 模板变量
     * @author weiba
     * @date 2024/3/7 14:57
     */
    public <T> T getPageTemplateVariable(String fields, TypeReference<T> typeReference) {
        return execute(UrlConstants.GET_TEMPLATE_VARIABLE, new HashMap<String, String>() {{
            put("fields", fields);
        }}, typeReference);
    }

    /**
     * 获取用户登录状态
     *
     * @return 用户登录状态信息
     * @author weiba
     * @date 2024/3/7 17:39
     */
    public LoginStatusResponse getLoginStatus() {
        return execute(UrlConstants.GET_LOGIN_STATUS, new TypeReference<LoginStatusResponse>() {
        });
    }


    public void signOut(String authenticationId) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.GET, UrlConstants.GET_LOGOUT).addheader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        HttpResponse httpResponse = executeResponse(httpRequest);
        if (httpResponse.getStatusCode() == 302) {
            log.info("退出登录成功，authenticationId:{}", authenticationId);
        }
    }

    public AccessToken getAccessToken(WebOAuthLoginAuthentication webOAuthLoginAuthentication) {
        // 必须扫码或短信等方式登录拿到完整Cookie，再获取，否则会获取失败，因为它中间会跳转oath。
        HttpResponse httpResponse = oauthDomain(webOAuthLoginAuthentication, XpanUrlConstants.GET_ACCESS_TOKEN);
        AccessToken accessToken = buildAccessToken(httpResponse);
        String oauthstokenStr = null;
        if (httpResponse.getCookieMap().containsKey("OAUTHSTOKEN")) {
            oauthstokenStr = httpResponse.getCookieMap().get("OAUTHSTOKEN").getValue();
        } else if (httpResponse.getCookieMap().containsKey("OAUTHSTOKEN_BFESS")) {
            oauthstokenStr = httpResponse.getCookieMap().get("OAUTHSTOKEN_BFESS").getValue();
        }
        if (accessToken == null && StrUtil.isNotBlank(oauthstokenStr)) {
            HttpRequest request = HttpRequest.urlFormatBuilder(Method.GET, XpanUrlConstants.GET_ACCESS_TOKEN)
                    .setFollowRedirect(true)
                    .setCookieMap(webOAuthLoginAuthentication.getCookieMap())
                    .addCookie("OAUTHSTOKEN", oauthstokenStr)
                    .addCookie("OAUTHSTOKEN_BFESS", oauthstokenStr)
                    .setFollowRedirect(false)
                    .setHtmlRequest(true);
            httpResponse = executeResponse(request);
            accessToken = buildAccessToken(httpResponse);
        }

        if (accessToken == null) {
            String msg = "获取accessToken失败, 账号登录状态异常, 请核实Cookies是否正常！";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        return accessToken;
    }

    public AccessToken buildAccessToken(HttpResponse httpResponse) {
        if (httpResponse.getStatusCode() == 302) {
            String locationUrl = httpResponse.getHeaderMap().get("Location");
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
        }
        return null;
    }

    public HttpResponse oauthDomain(WebOAuthLoginAuthentication webOAuthLoginAuthentication, String domain) {
        return oauthDomain(webOAuthLoginAuthentication, domain, 100);
    }

    public HttpResponse oauthDomain(WebOAuthLoginAuthentication webOAuthLoginAuthentication, String domain, int maxRedirectCount) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(domain);
        httpRequest.setCookieMap(webOAuthLoginAuthentication.getCookieMap())
                .setFollowRedirect(true)
                .setFollowRedirectsCookie(true)
                .setMaxRedirectCount(maxRedirectCount)
                .setTimeout(600000000)
                .setHtmlRequest(true);
        return executeResponse(httpRequest);
    }
}
