package pp.weiba.thirdparty.baidu.web.security.authentication;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.security.Authentication;

/**
 * 账号密码验证
 *
 * @author weiba
 * @date 2024/3/6 14:56
 */
@Log4j2
public class UsernamePasswordCredentials extends AbstractBaiduAuthentication implements IUsernamePasswordCredentials {

    @Override
    public Authentication getAuthentication() {
        return null;
    }

    @Override
    public Boolean login(String username, String password) {
        return null;
    }
}
