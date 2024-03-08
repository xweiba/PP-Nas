package pp.weiba.thirdparty.baidu.web.api.security.authentication.credentials;

import java.util.Properties;

/**
 * 通过配置文件获取认证信息
 *
 * @author weiba
 * @date 2024/3/8 11:00
 */
public interface IConfigCredentials extends ICredential {

    Properties getProperties();

}
