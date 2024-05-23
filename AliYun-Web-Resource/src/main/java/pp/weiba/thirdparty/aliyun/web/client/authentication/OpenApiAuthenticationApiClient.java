package pp.weiba.thirdparty.aliyun.web.client.authentication;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.util.BeanUtils;
import com.google.common.reflect.TypeToInstanceMap;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.net.client.model.HttpResponse;
import pp.weiba.framework.net.client.model.Method;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.authentication.request.OpenAccessTokenGrantType;
import pp.weiba.thirdparty.aliyun.web.client.authentication.request.OpenAccessTokenRequest;
import pp.weiba.thirdparty.aliyun.web.client.authentication.request.OpenAuthorizationRequest;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.OpenAccessTokenResponse;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.TokenResponse;
import pp.weiba.utils.StringUtils;

import java.util.HashMap;

/**
* 阿里云盘开放平台鉴权接口
*
* @author weiba
* @date 2024/5/23 16:03
*/
@Log4j2
public class OpenApiAuthenticationApiClient extends AbstractApiHttpClient {

    public OpenApiAuthenticationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 获取授权码，十分钟有效
     *
     * @param params
     * @return 授权码 code
     * @author weiba
     * @date 2024/5/23 16:07
     */
    public String getAuthorizationCode(OpenAuthorizationRequest params) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, UrlConstants.GET_POST_OPEN_OAUTH_AUTHORIZE_URL, null, BeanUtil.beanToMap(params)).setRequestBody("{\"scope\":\"user:base,file:all:read,file:all:write\",\"authorize\":1,\"drives\":[\"backup\",\"resource\"]}");
        HttpResponse httpResponse = executeResponse(httpRequest);
        // {"redirectUri":"https://openapi.alipan.com/oauth/authorize/callback?code=f2bf6446d9b145168cedfd49c0ec6624"}
        String body = httpResponse.getBody();
        if (!body.contains("code=")) {
            throw new RuntimeException("返回结果异常！");
        }
        return StringUtils.substring(body,"code=", "\"");
    }

    /**
     * 获取 AccessToken
     *
     * @param params
     * @return AccessToken
     * @author weiba
     * @date 2024/5/23 17:14
     */
    public OpenAccessTokenResponse getOpenAccessToken(OpenAccessTokenRequest params) {
        return postSrtExecute(UrlConstants.POST_OPEN_GET_ACCESS_TOKEN_URL, params, new TypeReference<OpenAccessTokenResponse>() {
        });
    }
}
