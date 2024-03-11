package pp.weiba.framework.core.client;

import lombok.extern.log4j.Log4j2;

/**
 * HttpClient 认证处理
 *
 * @author weiba
 * @date 2024/3/11 14:21
 */
@Log4j2
public abstract class AbstractHttpClientAuthentication implements IHttpClientAuthentication {

    protected String authenticationId;

    protected String authenticationType;

    // authenticationId 和 authenticationType 可以使用ThreadLocal获取，实现鉴权信息动态获取，一个client实例，发起不同用户的请求。
    public AbstractHttpClientAuthentication() {
    }

    public AbstractHttpClientAuthentication(String authenticationId, String authenticationType) {
        this.authenticationId = authenticationId;
        this.authenticationType = authenticationType;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

}
