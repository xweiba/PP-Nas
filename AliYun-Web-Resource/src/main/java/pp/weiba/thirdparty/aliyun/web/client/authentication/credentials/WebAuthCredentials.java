package pp.weiba.thirdparty.aliyun.web.client.authentication.credentials;

import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.AbstractCredential;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.model.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.TokenResponse;

/**
 * Web OAUTH 认证信息
 *
 * @author weiba
 * @date 2024/3/27 13:46
 */
@Log4j2
@Accessors(chain = true)
public abstract class WebAuthCredentials extends AbstractCredential<NetDiskAuthentication> {

    protected final AuthenticationApiClient authenticationApiClient;

    public WebAuthCredentials(AuthenticationApiClient authenticationApiClient) {
        this.authenticationApiClient = authenticationApiClient;
    }

    @Override
    public NetDiskAuthentication refresh() {
        this.refreshToken();
        return credentialData;
    }

    public void refreshToken() {
        if (credentialData.getToken() == null) return;
        TokenResponse tokenResponse = authenticationApiClient.refreshToken(credentialData.getToken().getRefreshToken());
        credentialData.setToken(tokenResponse);
    }
}
