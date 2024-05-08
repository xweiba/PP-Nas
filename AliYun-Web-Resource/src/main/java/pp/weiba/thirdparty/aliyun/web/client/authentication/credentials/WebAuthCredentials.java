package pp.weiba.thirdparty.aliyun.web.client.authentication.credentials;

import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.AbstractCredential;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.NetDiskAuthentication;
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

    protected TokenResponse tokenResponse;

    protected final AuthenticationApiClient authenticationApiClient;

    public WebAuthCredentials(AuthenticationApiClient authenticationApiClient) {
        this.authenticationApiClient = authenticationApiClient;
    }

    abstract TokenResponse buildToken();

    public void buildCredential() {
        this.tokenResponse = this.buildToken();
        this.refreshToken();
        this.credentialData = new NetDiskAuthentication().setToken(this.tokenResponse);
    }

    public void refreshToken() {
        this.tokenResponse = authenticationApiClient.refreshToken(this.tokenResponse.getRefreshToken());
    }
}
