package pp.weiba.thirdparty.aliyun.web.client.authentication;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.ClientConstants;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.net.client.model.HttpResponse;
import pp.weiba.framework.net.client.model.Method;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.authentication.request.DeviceOfflineRequest;
import pp.weiba.thirdparty.aliyun.web.client.authentication.request.IVRequest;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.*;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.CreateSessionRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SBoxInfo;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.UserInfo;
import pp.weiba.utils.JSONUtils;
import pp.weiba.utils.StringUtils;

import java.net.HttpCookie;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证信息接口
 *
 * @author weiba
 * @date 2024/4/29 17:29
 */
@Log4j2
public class AuthenticationApiClient extends AbstractApiHttpClient {

    public AuthenticationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    public LoginMainResponse initLoginMain() {
        // 初始化auth, 获取SESSIONID Cookie， 无此Cookie则无法通过token换取code
        HttpRequest authHttpRequest = HttpRequest.urlFormatBuilder(UrlConstants.HTML_INIT_AUTH_ALIPAN_URL);
        HttpResponse authHttpResponse = executeResponse(authHttpRequest);
        Map<String, HttpCookie> cookieMap = authHttpResponse.getCookieMap();

        String domain = UrlConstants.HTML_INIT_LOGIN_MAIN_URL + RandomUtil.randomNumbers(17);
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(domain).setHtmlRequest(true).setCookieMap(cookieMap);
        HttpResponse httpResponse = executeResponse(httpRequest);
        String body = httpResponse.getBody();
        String viewDataJsonStr = StringUtils.substring(body, "window.viewData = ", ";\n" +
                "    window._lang");
        LoginMainViewData viewData = JSONUtils.toBean(viewDataJsonStr, LoginMainViewData.class);
        String configDataJsonStr = StringUtils.substring(body, "window.viewConfig = ", ";\n" +
                "    window.viewData");
        LonginMainConfig config = JSONUtils.toBean(configDataJsonStr, LonginMainConfig.class);
        cookieMap = httpResponse.getCookieMap();
        return new LoginMainResponse(viewData, config, cookieMap);
    }

    public GenerateQrCodeResponse generateQRcode(LoginMainResponse loginMainResponse) {
        String getQRCodeApi = loginMainResponse.getConfig().getApi().getGetQRCodeApi();
        String url = UrlConstants.PASSPORT_DOMAIN + getQRCodeApi;
        LoginMainViewData.LoginFormDataResponse loginFormData = loginMainResponse.getViewData().getLoginFormData();
        // 将User对象转换为Map
        Map<String, Object> requestParams = BeanUtil.beanToMap(loginFormData);
        requestParams.put("umidTag", "SERVER");
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.GET, url).requestParams(requestParams).setCookieMap(loginMainResponse.getCookieMap());
        HttpResponse httpResponse = executeResponse(httpRequest);
        loginMainResponse.setCookieMap(httpResponse.getCookieMap());
        return convert(httpResponse, new TypeReference<GenerateQrCodeResponse>() {
        });
    }

    public HttpRequest buildQrLoginCheckHttpRequest(GenerateQrCodeResponse generateQrCodeResponse, LoginMainResponse loginMainResponse) {
        String checkQRCodeApi = loginMainResponse.getConfig().getApi().getCheckQRCodeApi();
        String url = UrlConstants.PASSPORT_DOMAIN + checkQRCodeApi;
        LoginMainViewData.LoginFormDataResponse loginFormData = loginMainResponse.getViewData().getLoginFormData();
        // 将User对象转换为Map
        Map<String, Object> requestParams = BeanUtil.beanToMap(loginFormData);
        requestParams.put("t", generateQrCodeResponse.getContent().getData().getT());
        requestParams.put("ck", loginFormData.getHsiz());
        requestParams.put("umidTag", "SERVER");
        requestParams.put("navlanguage", "zh-CN");
        requestParams.put("navUserAgent", ClientConstants.USER_AGENT);
        requestParams.put("navPlatform", "Win32");
        requestParams.put("isIframe", true);
        requestParams.put("documentReferer", UrlConstants.AUTH_ALIPAN_DOMAIN);
        requestParams.put("defaultView", "qrcode");
        requestParams.put("deviceId", "");
        requestParams.put("pageTraceId", SecureUtil.md5(JSONUtils.toJsonStr(requestParams)));

        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, url).requestParams(requestParams)
                .addheader("Content-Type", "application/x-www-form-urlencoded").setCookieMap(loginMainResponse.getCookieMap());
        return httpRequest;
    }

    public QrLonginCheckResponse qrLoginCheck(HttpRequest httpRequest) {
        TypeReference<QrLonginCheckResponse> typeReference = new TypeReference<QrLonginCheckResponse>() {
        };
        HttpResponse httpResponse = executeResponse(httpRequest);
        httpRequest.setCookieMap(httpResponse.getCookieMap());
        return convert(httpResponse, typeReference);
    }

    /**
     * 使用token换code
     *
     * @param token 扫描或其他方式登录获取的token
     * @return code 信息
     * @author weiba
     * @date 2024/5/7 15:56
     */
    public CodeByInitTokenResponse getCodeByInitToken(String token, Map<String, HttpCookie> cookieMap) {
        // 解码Base64
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String result = new String(decodedBytes, Charset.forName("GBK"));
        // 使用GBK解码
        Object accessToken = ((JSONObject) JSONUtils.toJSONObj(result).get("pds_login_result")).get("accessToken");

        HashMap<String, Object> requestParams = new HashMap<String, Object>() {{
            put("token", accessToken);
        }};
        TypeReference<CodeByInitTokenResponse> typeReference = new TypeReference<CodeByInitTokenResponse>() {
        };
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, UrlConstants.POST_TOKEN_LONGIN_URL).setRequestBody(JSONUtils.toJsonStr(requestParams))
                .setCookieMap(cookieMap).addheader("Referer", "https://auth.aliyundrive.com/v2/oauth/authorize?login_type=custom&response_type=code&redirect_uri=https%3A%2F%2Fwww.aliyundrive.com%2Fsign%2Fcallback&client_id=25dzX3vbYqktVxyX&sid=lvw28b5agjce0&state=%7B%22origin%22%3A%22https%3A%2F%2Fwww.aliyundrive.com%22%7D");
        HttpResponse httpResponse = executeResponse(httpRequest);
        httpRequest.setCookieMap(httpResponse.getCookieMap());
        return convert(httpResponse, typeReference);
    }

    /**
     * 使用code获取业务token
     *
     * @param code     getCodeByInitToken 返回的code
     * @param deviceId 当前登录设备id IdUtil.simpleUUID()
     * @return Token 信息
     * @author weiba
     * @date 2024/5/7 16:18
     */
    public TokenResponse getTokenByCode(String code, String deviceId) {
        return postSrtExecute(UrlConstants.POST_TOKEN_GET_BY_CODE_URL, new HashMap<String, Object>() {{
            put("code", code);
            put("deviceId", deviceId);
            put("loginType", "unnormal");
        }}, new TypeReference<TokenResponse>() {
        });
    }

    /**
     * 获取设备列表，最多十台
     *
     * @return
     * @author weiba
     * @date 2024/5/10 11:33
     */
    public DeviceListResponse getDeviceList() {
        return postSrtExecute(UrlConstants.POST_DEVICE_LIST_URL, new HashMap<>(), new TypeReference<DeviceListResponse>() {
        });
    }

    /**
     * 指定设备退出登录
     *
     * @return
     * @author weiba
     * @date 2024/5/10 11:33
     */
    public IVResponse deviceOfflineIV(IVRequest params) {
        return postSrtExecute(UrlConstants.POST_GET_IV_URL, params, new TypeReference<IVResponse>() {
        });
    }

    /**
     * 指定设备退出登录
     *
     * @return
     * @author weiba
     * @date 2024/5/10 11:33
     */
    public boolean deviceOffline(String deviceId) {
        // "url": "https://passport.aliyundrive.com/iv/remote/h5/request.htm?havana_iv_token=CN-SPLIT-AQigvcWXrUAQ2AQiDWhhdmFuYV9hcHBfaXYyAQE42qfEhPYxQAFKEAUgkf75E2qc7C5WIvaXQ9KEybjAg7oSjsvgIPwXeDwF-zJjIw"
        IVResponse ivResponse = deviceOfflineIV(new IVRequest("device_offline", "message"));

        String url = ivResponse.getUrl();
        // 打开该url 会302，点击获取短信验证码，最后会302到下面
        // https://passport.aliyundrive.com/iv/message_callback.htm?havana_iv_token=CN-SPLIT-AQigvcWXrUAQ2AQiDWhhdmFuYV9hcHBfaXYyAQE48pLfh_YxQAFKEC3Y0QQS6QkGCyFicR826zvbtcbpEw_S-1j3w0DhW8de07x3Eg&appName=
        // havana_iv_token 才是的
        throw new RuntimeException("还未开发完毕");

        /*String token = url.substring(url.indexOf("havana_iv_token=") + "havana_iv_token=".length());
        HttpResponse httpResponse = postStrExecuteResponse(UrlConstants.POST_DEVICE_OFFLINE_URL, new DeviceOfflineRequest(deviceId, token));
        return httpResponse.getStatusCode() == 200;*/
    }

    /**
     * web接口， token 刷新，两小时过期
     *
     * @param refreshToken refreshToken
     * @return 刷新后的token， refreshToken 不变
     * @author weiba
     * @date 2024/5/8 13:49
     */
    public TokenResponse refreshToken(String refreshToken) {
        return postSrtExecute(UrlConstants.POST_TOKEN_REFRESH_URL, new HashMap<String, Object>() {{
            put("refresh_token", refreshToken);
        }}, new TypeReference<TokenResponse>() {
        });
    }

    /**
     * App 端接口
     *
     * @param refreshToken refreshToken
     * @return 刷新后的token， refreshToken会改变
     * @author weiba
     * @date 2024/5/8 13:50
     */
    public TokenResponse refreshTokenByApp(String refreshToken) {
        return postSrtExecute(UrlConstants.POST_APP_TOKEN_REFRESH_URL, new HashMap<String, Object>() {{
            put("refresh_token", refreshToken);
            put("grant_type", "refresh_token");
        }}, new TypeReference<TokenResponse>() {
        });
    }

    public boolean createSession(String pubkey) {
        HttpResponse httpResponse = postStrExecuteResponse(UrlConstants.POST_CREATE_SESSION_URL, new CreateSessionRequest(pubkey));
        return httpResponse.getStatusCode() == 200;
    }

    public boolean refreshSession() {
        // 执行前需要刷新 X-Signature
        HttpResponse httpResponse = postStrExecuteResponse(UrlConstants.POST_NEW_SESSION_URL, new HashMap<>());
        return httpResponse.getStatusCode() == 200;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     * @author weiba
     * @date 2024/4/30 14:05
     */
    public UserInfo getUserInfo() {
        return postExecute(UrlConstants.POST_GET_USER_INFO_URL, new TypeReference<UserInfo>() {
        });
    }

    /**
     * 获取环境信息
     *
     * @return 环境信息
     * @author weiba
     * @date 2024/4/30 16:32
     */
    public SBoxInfo getSBoxInfo() {
        return postExecute(UrlConstants.POST_GET_SBOX_INFO_URL, new TypeReference<SBoxInfo>() {
        });
    }

    public void signOut(String authenticationId) {

    }
}
