package pp.weiba.thirdparty.aliyun.web.resource.security.authentication;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.AbstractAuthentication;
import pp.weiba.framework.security.authentication.AuthenticationManager;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.AliYunUtils;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.SignatureInfo;

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
    protected NetDiskAuthentication initAuthentication() {
        NetDiskAuthentication netDiskAuthentication = super.initAuthentication();
        if (StrUtil.isBlank(netDiskAuthentication.getAuthorization())) {
            // 记录日志，抛出异常
            log.error("阿里云盘认证信息为空");
            throw new RuntimeException("阿里云盘认证信息为空");
        }
        // 设置设备X-ID
        return netDiskAuthentication.setXDeviceId(IdUtil.simpleUUID());
    }

    @Override
    public NetDiskAuthentication detectionAuthentication(NetDiskAuthentication netDiskAuthentication) {

        if (netDiskAuthentication.getUserInfo() == null) {
            netDiskAuthentication.setUserInfo(this.authenticationApiClient.getUserInfo());
        }

        if (netDiskAuthentication.getSBoxInfo() == null) {
            netDiskAuthentication.setSBoxInfo(this.authenticationApiClient.getSBoxInfo());
        }

        return netDiskAuthentication;
    }

    @Override
    protected NetDiskAuthentication completeAuthenticationInformation(NetDiskAuthentication authentication) {
        // 生成公钥与私钥
        SignatureInfo signatureInfo = AliYunUtils.createSignatureInfo(authentication.getUserInfo().getUserId(), authentication.getXDeviceId());
        authentication.setSignatureInfo(signatureInfo);

        // 算出签名，这个需要定时刷新，后面再添加
        String xSignature = AliYunUtils.buildXSignature(signatureInfo);
        authentication.setXSignature(xSignature);

        // 将公钥设置到服务器
        authenticationApiClient.createSession(signatureInfo.getPrivateKey());

        return super.completeAuthenticationInformation(authentication);
    }

    @Override
    protected void doLogout() {
        authenticationApiClient.signOut(authenticationId);
        AuthenticationManager.removeAuthentication(authenticationId, authenticationType);
    }

}
