package pp.weiba.thirdparty.aliyun.web.client.authentication;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.net.client.AbstractHttpClientAuthentication;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.security.authentication.AuthenticationManager;

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
        NetDiskAuthentication authorization = getAuthorization();
        if (authorization != null) {
            request.addheader("Authorization", authorization.getAuthorization());
            request.addheader("X-Device-Id", authorization.getXDeviceId());
            if (StrUtil.isNotBlank(authorization.getXSignature())) {
                request.addheader("X-Signature", authorization.getXSignature());
            }
        }
        return request;
    }

    public NetDiskAuthentication getAuthorization() {
        NetDiskAuthentication netDiskAuthentication = AuthenticationManager.getAuthentication(this.getAuthenticationId(), this.getAuthenticationType());
        if (netDiskAuthentication != null && StrUtil.isNotBlank(netDiskAuthentication.getAuthorization())) {
            return netDiskAuthentication;
        }
        return null;
    }
}
