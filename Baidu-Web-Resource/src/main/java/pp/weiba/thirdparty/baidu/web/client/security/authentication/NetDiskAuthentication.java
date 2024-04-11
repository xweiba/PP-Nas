package pp.weiba.thirdparty.baidu.web.client.security.authentication;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.framework.core.convert.HttpCookieDeserializer;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.LoginStatusResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.TemplateVariableResponse;

import java.net.HttpCookie;
import java.util.Map;

/**
 * 百度认证信息
 *
 * @author weiba
 * @date 2024/3/7 16:22
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NetDiskAuthentication {

    // OpenAPI 认证信息， XPAN 接口需要
    public AccessToken accessToken;
    // 网盘公共信息
    public TemplateVariableResponse.Result templateVariable;
    // OAUTH 认证信息，通过此获取应用的 Cookies
    private WebOAuthLoginAuthentication webOAuthLoginAuthentication;
    // 应用的 cookie map
    @JSONField(name = "cookieMap", deserializeUsing = HttpCookieDeserializer.class)
    private Map<String, HttpCookie> domainCookieMap;

    public NetDiskAuthentication(WebOAuthLoginAuthentication webOAuthLoginAuthentication) {
        this.webOAuthLoginAuthentication = webOAuthLoginAuthentication;
    }
    public LoginStatusResponse.LoginInfo loginInfo;

    public String getCookieValue(String cookieName) {
        if (domainCookieMap.containsKey(cookieName)) {
            return domainCookieMap.get(cookieName).getValue();
        }
        return null;
    }

    public boolean containsCookie(String cookieName) {
        return domainCookieMap.containsKey(cookieName);
    }

}
