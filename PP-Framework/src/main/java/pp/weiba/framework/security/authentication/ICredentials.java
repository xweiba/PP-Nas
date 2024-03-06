package pp.weiba.framework.security.authentication;

import java.util.Properties;

/**
 * 认证接口，可以是账号密码，扫码，短信验证
 *
 * @author weiba
 * @date 2024/3/6 14:54
 */
public interface ICredentials {

    Boolean authenticate(Properties properties);

}
