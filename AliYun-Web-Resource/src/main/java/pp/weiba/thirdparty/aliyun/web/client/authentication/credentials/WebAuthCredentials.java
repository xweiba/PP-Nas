package pp.weiba.thirdparty.aliyun.web.client.authentication.credentials;

import cn.hutool.core.util.IdUtil;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.AbstractCredential;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.TokenResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.AliYunUtils;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.SignatureInfo;

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
        this.credentialData = new NetDiskAuthentication().setToken(this.tokenResponse);
    }

    @Override
    public NetDiskAuthentication refresh() {
        this.refreshToken();
        return credentialData;
    }

    public void refreshToken() {
        this.tokenResponse = authenticationApiClient.refreshToken(credentialData.getToken().getRefreshToken());
        credentialData.setToken(this.tokenResponse);
    }
}
