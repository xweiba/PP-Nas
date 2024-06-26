package pp.weiba.thirdparty.baidu.web.client.security.authentication;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.net.client.AbstractHttpClientAuthentication;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.security.authentication.AuthenticationManager;

import java.net.HttpCookie;
import java.util.Map;

/**
 * web版百度Http客户端鉴权处理器
 *
 * @author weiba
 * @date 2024/3/11 10:50
 */
@Log4j2
public class BaiduYunWebHttpClientAuthentication extends AbstractHttpClientAuthentication {

    public BaiduYunWebHttpClientAuthentication() {
    }

    @Override
    public HttpRequest authentication(HttpRequest request) {
        Map<String, HttpCookie> cookies = getCookies();
        request.setCookieMap(cookies);
        return request;
    }

    public Map<String, HttpCookie> getCookies() {
        NetDiskAuthentication netDiskAuthentication = AuthenticationManager.getAuthentication(this.getAuthenticationId(), this.getAuthenticationType());
        if (netDiskAuthentication != null && CollUtil.isNotEmpty(netDiskAuthentication.getDomainCookieMap())) {
            return netDiskAuthentication.getDomainCookieMap();
        }
        return null;
    }
}
