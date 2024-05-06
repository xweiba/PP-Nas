package pp.weiba.thirdparty.aliyun.web.client.authentication.credentials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.aliyun.web.client.authentication.NetDiskAuthentication;

/**
 * Web OAUTH 认证信息
 *
 * @author weiba
 * @date 2024/3/27 13:46
 */
@Log4j2
@Data
@AllArgsConstructor
@Accessors(chain = true)
public abstract class WebAuthCredentials implements ICredential<NetDiskAuthentication> {

    protected String authorization;

    @Override
    public NetDiskAuthentication getCredential() {
        return new NetDiskAuthentication().setAuthorization(this.authorization);
    }

}
