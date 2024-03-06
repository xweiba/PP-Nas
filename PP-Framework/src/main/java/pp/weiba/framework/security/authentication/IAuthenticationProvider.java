package pp.weiba.framework.security.authentication;

/**
 * 鉴权接口
 *
 * @author weiba
 * @date 2024/3/6 9:17
 */
public interface IAuthenticationProvider {

    /**
     * 鉴权接口
     *
     * @param credentials 鉴权参数
     * @return 登录成功或失败
     * @author weiba
     * @date 2024/3/6 10:17
     */
    Boolean authenticate(ICredentials credentials);

}
