package pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.IConfigCredentials;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;

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
public class ConfigCredentials implements IConfigCredentials {

    private final String configPath;

    public ConfigCredentials(String configPath) {
        if (StrUtil.isBlank(configPath)) {
            configPath = "config.properties";
        }
        this.configPath = configPath;
    }

    // 获取jar目录下的config.properties中的baidu.authentication.bduss和baidu.authentication.stoken
    public Authentication getCredential() {

        Properties props = getProperties();

        // 从配置文件中获取值
        String bduss = props.getProperty("baidu.authentication.bduss");
        String stoken = props.getProperty("baidu.authentication.stoken");

        // 构建并返回 Authentication 对象
        return new Authentication(bduss, stoken);
    }

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
}
