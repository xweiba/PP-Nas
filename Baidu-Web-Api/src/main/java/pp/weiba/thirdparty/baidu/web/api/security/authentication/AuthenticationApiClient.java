package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.*;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.thirdparty.baidu.web.api.netdisk.UrlConstants;
import pp.weiba.thirdparty.baidu.web.api.netdisk.XpanUrlConstants;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.LoginStatusResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.TemplateVariableResponse;

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

    public HttpResponse getAuth() {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, XpanUrlConstants.POST_AUTH_API)
                .addheader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .setContentType("application/x-www-form-urlencoded")
                .addheader("Host", "openapi.baidu.com")
                .addRequestParams("jumpurl", "https://openapi.baidu.com/static/oauth/html/bdstoken_jump.html")
                .addRequestParams("etken", "{STOKEN}");
        return executeResponse(httpRequest);
    }

    public void initAccessToken() {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.GET, XpanUrlConstants.POST_ACCESS_TOKEN)
                .addCookie("newlogin", "1");
        HttpResponse httpResponse1 = executeResponse(httpRequest);


    }


    public AccessToken getAccessToken() {
        HttpResponse getAuth = getAuth();
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.GET, XpanUrlConstants.POST_ACCESS_TOKEN)
                .setContentType("application/x-www-form-urlencoded")
                .addheader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .setCookies(getAuth.getCookies())
                .addheader("Host", "openapi.baidu.com")
                .addheader("Referer", XpanUrlConstants.POST_ACCESS_TOKEN)
                .addRequestParams("grant_permissions_arr", "netdisk")
                .addRequestParams("bdstoken", "{BDSTOKEN}")
                .addRequestParams("client_id", XpanUrlConstants.CLIENT_ID)
                .addRequestParams("response_type", "token")
                .addRequestParams("display", "page")
                .addRequestParams("grant_permissions", "basic,netdisk");
        HttpResponse httpResponse = executeResponse(httpRequest);
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
                Long expiresIn = Long.valueOf(String.valueOf(query.get("expires_in")));
                String session_secret = String.valueOf(query.get("session_secret"));
                String scope = String.valueOf(query.get("scope"));
                return new AccessToken(new Date(expiresIn), accessToken, session_secret, scope);
            }
            log.error("获取accessToken失败, locationUrl异常：{}", locationUrl);
        } else if (httpResponse.getStatusCode() == 200) {
            log.error("获取accessToken失败, 账号还未登录");
        }
        return null;
    }

    /*
     * 二维码生成参数分析
     * */

}
