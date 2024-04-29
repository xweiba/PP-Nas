package pp.weiba.thirdparty.aliyun.web.resource.security.authentication;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.AbstractAuthentication;
import pp.weiba.framework.security.authentication.AuthenticationManager;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.NetDiskAuthentication;

/**
 * 百度网盘认证信息统一处理
 *
 * @author weiba
 * @date 2024/3/8 11:10
 */
@Log4j2
public class AliYunNetDiskWebAuthentication extends AbstractAuthentication<NetDiskAuthentication> {


    private final AuthenticationApiClient authenticationApiClient;

    public AliYunNetDiskWebAuthentication(String authenticationId, String authenticationType, AuthenticationApiClient authenticationApiClient, ICredential<NetDiskAuthentication> credential) {
        super(authenticationId, authenticationType, credential);
        this.authenticationApiClient = authenticationApiClient;
    }

    @Override
    public NetDiskAuthentication detectionAuthentication(NetDiskAuthentication netDiskAuthentication) {
        if (netDiskAuthentication == null || StrUtil.isBlank(netDiskAuthentication.getAuthorization())) {
            // 记录日志，抛出异常
            log.error("阿里云盘认证信息为空");
            throw new RuntimeException("阿里云盘认证信息为空");
        }
        return netDiskAuthentication;
    }

    @Override
    protected void doLogout() {
        authenticationApiClient.signOut(authenticationId);
        AuthenticationManager.removeAuthentication(authenticationId, authenticationType);
    }

}
