package pp.weiba.framework.security.authentication.credential;

import java.util.Properties;

/**
 * 通过配置文件获取认证信息
 *
 * @author weiba
 * @date 2024/3/8 11:00
 */
public interface IConfigCredentials<T> extends ICredential<T> {

    Properties getProperties();

    void buildCredential();

}
