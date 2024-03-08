package pp.weiba.framework.security.authentication.credential;

/**
 * 账号密码登录，会有多步验证，最好使用短信验证码
 *
 * @author weiba
 * @date 2024/3/7 16:42
 */
public interface IUsernamePasswordCredentials extends ICredential {

    // 账号密码登录
    Boolean login(String username, String password);

}
