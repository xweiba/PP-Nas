package pp.weiba.thirdparty.baidu.web.resource.client.authentication;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.AbstractHttpClientAuthentication;
import pp.weiba.framework.core.client.HttpRequest;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.BaiduAuthenticationManager;

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
        String cookie = getCookie();
        request.addheader("Cookie", cookie);
        return request;
    }

    public String getCookie() {
        Authentication authentication = BaiduAuthenticationManager.getAuthentication(this.getAuthenticationId(), this.getAuthenticationType());
        if (authentication != null) {
            return authentication.getCookie();
        }
        return null;
    }
}
