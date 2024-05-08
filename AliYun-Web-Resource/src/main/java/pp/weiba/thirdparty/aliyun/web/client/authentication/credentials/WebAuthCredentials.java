package pp.weiba.thirdparty.aliyun.web.client.authentication.credentials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.AbstractCredential;
import pp.weiba.thirdparty.aliyun.web.client.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.TokenResponse;

/**
 * Web OAUTH 认证信息
 *
 * @author weiba
 * @date 2024/3/27 13:46
 */
@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public abstract class WebAuthCredentials extends AbstractCredential<NetDiskAuthentication> {

    protected TokenResponse tokenResponse;

}
