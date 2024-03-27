package pp.weiba.thirdparty.baidu.web.resource.client.authentication;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.AbstractHttpClientAuthentication;
import pp.weiba.framework.core.client.HttpRequest;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.BaiduAuthenticationManager;

import java.net.HttpCookie;
import java.util.Map;

/**
 * web版百度Http客户端鉴权处理器
 *
 * @author weiba
 * @date 2024/3/11 10:50
 */
@Log4j2
public class WebHttpClientAuthentication extends AbstractHttpClientAuthentication {

    public WebHttpClientAuthentication(String authenticationId, String authenticationType) {
        super(authenticationId, authenticationType);
    }

    @Override
    public HttpRequest authentication(HttpRequest request) {
        Map<String, HttpCookie> cookieMap = getCookies();
        request.setCookieMap(cookieMap);
        return request;
    }

    public Map<String, HttpCookie> getCookies() {
        Authentication authentication = BaiduAuthenticationManager.getAuthentication(this.getAuthenticationId(), this.getAuthenticationType());
        if (authentication != null && CollUtil.isNotEmpty(authentication.getCookieMap())) {
            return authentication.getCookieMap();
        }
        return null;
    }
}
