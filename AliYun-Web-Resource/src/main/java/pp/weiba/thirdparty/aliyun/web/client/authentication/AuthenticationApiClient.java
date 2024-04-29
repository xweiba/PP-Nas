package pp.weiba.thirdparty.aliyun.web.client.authentication;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;

/**
 * 认证信息接口
 *
 * @author weiba
 * @date 2024/4/29 17:29
 */
@Log4j2
public class AuthenticationApiClient extends AbstractApiHttpClient {

    public AuthenticationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    public void signOut(String authenticationId) {

    }
}
