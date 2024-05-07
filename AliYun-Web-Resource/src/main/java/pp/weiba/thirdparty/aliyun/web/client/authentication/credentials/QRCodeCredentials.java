package pp.weiba.thirdparty.aliyun.web.client.authentication.credentials;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.IQRCodeCredentials;
import pp.weiba.thirdparty.aliyun.web.client.authentication.NetDiskAuthentication;

/**
 * 扫码登录
 *
 * @author weiba
 * @date 2024/5/6 13:40
 */
@Log4j2
public class QRCodeCredentials implements IQRCodeCredentials<NetDiskAuthentication> {

    @Override
    public String getQRUrl() {
        return null;
    }


    @Override
    public NetDiskAuthentication getCredential() {
        return null;
    }
}
