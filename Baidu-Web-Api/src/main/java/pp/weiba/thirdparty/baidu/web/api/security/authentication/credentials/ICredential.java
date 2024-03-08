package pp.weiba.thirdparty.baidu.web.api.security.authentication.credentials;

/**
 * 认证接口
 *
 * @author weiba
 * @date 2024/3/8 10:55
 */
public interface ICredential<T> {

    T getCredential();

}
