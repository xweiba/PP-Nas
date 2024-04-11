package pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.ISMSCodeCredentials;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.NetDiskAuthentication;

/**
 * 短信验证
 *
 * @author weiba
 * @date 2024/3/6 14:56
 */
@Log4j2
public class SMSCodeCredentials implements ISMSCodeCredentials {

    @Override
    public NetDiskAuthentication getCredential() {
        return null;
    }

    @Override
    public Boolean sendSMS(String phone) {
        return null;
    }

    @Override
    public Boolean verifySMS(String phone, String code) {
        return null;
    }
}
