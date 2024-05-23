package pp.weiba.thirdparty.aliyun.web.client.authentication;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.net.client.AbstractHttpClientAuthentication;
import pp.weiba.framework.net.client.ClientConstants;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.security.authentication.AuthenticationManager;
import pp.weiba.thirdparty.aliyun.web.client.AliYunClientConstants;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.TokenResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.SignatureInfo;

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
        Map<String, Object> params = request.getBuildParams();

        boolean isNewSession = false;
        boolean isNotAddTokenType = false;
        if (params != null) {
            if (params.get(ClientConstants.REQUEST_PARAM_NEW_SESSION_TAG) != null) {
                isNewSession = (Boolean) params.get(ClientConstants.REQUEST_PARAM_NEW_SESSION_TAG);
            }
            if (params.get(AliYunClientConstants.NOT_ADD_TOKEN_TYPE) != null) {
                isNotAddTokenType = (Boolean) params.get(AliYunClientConstants.NOT_ADD_TOKEN_TYPE);
            }

        }
        if (!isNewSession) {
            NetDiskAuthentication authorization = getAuthorization();
            if (authorization != null) {
                TokenResponse tokenResponse = authorization.getToken();
                SignatureInfo signatureInfo = authorization.getSignatureInfo();
                if (tokenResponse != null) {
                    String authorizationStr = isNotAddTokenType ? tokenResponse.getAccessToken() : tokenResponse.getTokenType() + " " + tokenResponse.getAccessToken();
                    request.addheader("Authorization", authorizationStr);
                }
                if (signatureInfo != null) {
                    if (StrUtil.isNotBlank(signatureInfo.getXDeviceId())) {
                        request.addheader("X-Device-Id", signatureInfo.getXDeviceId());
                    }
                    if (StrUtil.isNotBlank(signatureInfo.getXSignature())) {
                        request.addheader("X-Signature", signatureInfo.getXSignature());
                    }
                }

            }
        }
        return request;
    }

    public NetDiskAuthentication getAuthorization() {
        NetDiskAuthentication netDiskAuthentication = AuthenticationManager.getAuthentication(this.getAuthenticationId(), this.getAuthenticationType());
        if (netDiskAuthentication != null && StrUtil.isNotBlank(netDiskAuthentication.getToken().getAccessToken())) {
            return netDiskAuthentication;
        }
        return null;
    }
}
