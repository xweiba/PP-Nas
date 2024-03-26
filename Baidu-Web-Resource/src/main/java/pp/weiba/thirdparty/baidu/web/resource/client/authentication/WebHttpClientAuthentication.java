package pp.weiba.thirdparty.baidu.web.resource.client.authentication;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.KeyValue;
import pp.weiba.framework.core.client.AbstractHttpClientAuthentication;
import pp.weiba.framework.core.client.HttpRequest;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.BaiduAuthenticationManager;

import java.util.List;

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
        List<KeyValue> cookies = getCookies();
        request.setCookies(cookies);
        return request;
    }

    public List<KeyValue> getCookies() {
        Authentication authentication = BaiduAuthenticationManager.getAuthentication(this.getAuthenticationId(), this.getAuthenticationType());
        if (authentication != null) {
            return authentication.getCookies();
        }
        return null;
    }
}
