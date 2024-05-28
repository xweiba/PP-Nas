package pp.weiba.framework.net.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.utils.UserInfoUtils;

/**
 * HttpClient 认证处理
 *
 * @author weiba
 * @date 2024/3/11 14:21
 */
@Log4j2
public abstract class AbstractHttpClientAuthentication implements IHttpClientAuthentication {

    // authenticationId 和 authenticationType 可以使用ThreadLocal获取，实现鉴权信息动态获取，一个client实例，发起不同用户的请求。
    public AbstractHttpClientAuthentication() {
    }

    public String getAuthenticationId() {
        return UserInfoUtils.getCurrentThreadUserId();
    }

    public String getAuthenticationType() {
        return UserInfoUtils.getCurrentThreadUserType();
    }

}
