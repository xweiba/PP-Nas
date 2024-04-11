package pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.WebOAuthLoginAuthentication;

import java.nio.charset.StandardCharsets;

/**
 * 通过配置获取认证信息
 *
 * @author weiba
 * @date 2024/3/6 14:59
 */
@Log4j2
public class OAuthJSONCredentials extends WebCookieCredentials implements ICredential<NetDiskAuthentication> {

    private final String jsonFilePath;

    public OAuthJSONCredentials(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    @Override
    public void buildCredential() {
        WebOAuthLoginAuthentication webOAuthLoginAuthentication = JSON.parseObject(FileUtil.readString(jsonFilePath, StandardCharsets.UTF_8), WebOAuthLoginAuthentication.class);
        if (webOAuthLoginAuthentication == null) {
            throw new IllegalArgumentException("jsonFilePath is not found");
        }
        initCookies(webOAuthLoginAuthentication.getCookieMap());
    }

}
