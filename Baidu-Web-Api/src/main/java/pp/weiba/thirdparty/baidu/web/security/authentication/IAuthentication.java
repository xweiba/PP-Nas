package pp.weiba.thirdparty.baidu.web.security.authentication;

/**
 * 认证接口
 *
 * @author weiba
 * @date 2024/3/7 16:26
 */
public interface IAuthentication<T> {

    T getAuthentication();

}
