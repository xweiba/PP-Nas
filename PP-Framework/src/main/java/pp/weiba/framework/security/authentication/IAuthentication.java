package pp.weiba.framework.security.authentication;

/**
 * 认证接口
 *
 * @author weiba
 * @date 2024/3/7 16:26
 */
public interface IAuthentication<T> {

    /**
     * 构建认证信息
     *
     * @return 返回认证信息
     * @author weiba
     * @date 2024/3/8 11:20
     */
    T login();

    /**
     * 登出
     *
     * @author weiba
     * @date 2024/3/19 14:05
     */
    void logout();

}
