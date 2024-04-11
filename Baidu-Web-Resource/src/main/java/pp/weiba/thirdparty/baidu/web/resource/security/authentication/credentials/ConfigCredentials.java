package pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.IConfigCredentials;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.NetDiskAuthentication;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 通过配置获取认证信息
 *
 * @author weiba
 * @date 2024/3/6 14:59
 */
@Log4j2
public class ConfigCredentials extends WebCookieCredentials implements IConfigCredentials<NetDiskAuthentication> {

    private final String DEFAULT_CONFIG_PATH = "config.properties";

    private String configPath = DEFAULT_CONFIG_PATH;

    public ConfigCredentials(String configPath) {
        if (StrUtil.isNotBlank(configPath)) {
            this.configPath = configPath;
        }
    }

    public ConfigCredentials() {
    }

    // 获取jar目录下的config.properties中的baidu.authentication.bduss和baidu.authentication.stoken

    @Override
    public Properties getProperties() {
        // 创建 Properties 对象
        Properties props = new Properties();
        try {
            // 通过类加载器加载配置文件
            InputStream in = getClass().getClassLoader().getResourceAsStream(configPath);
            if (in == null) {
                throw new FileNotFoundException("Resource not found: " + configPath);
            }
            // 加载配置文件内容
            props.load(in);
        } catch (Exception e) {
            return null;
        }
        return props;
    }

    @Override
    public void buildCredential() {
        Properties props = getProperties();

        // 从配置文件中获取值
        this.bduss = props.getProperty("baidu.authentication.bduss");
        this.stoken = props.getProperty("baidu.authentication.stoken");
        this.ptoken = props.getProperty("baidu.authentication.ptoken");
        this.baiduid = props.getProperty("baidu.authentication.baiduid");
        this.ubi = props.getProperty("baidu.authentication.ubi");

    }
}
