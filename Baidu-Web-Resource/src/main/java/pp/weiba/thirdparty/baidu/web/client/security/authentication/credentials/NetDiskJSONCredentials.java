package pp.weiba.thirdparty.baidu.web.client.security.authentication.credentials;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.NetDiskAuthentication;

import java.nio.charset.StandardCharsets;

/**
 * 通过配置获取认证信息
 *
 * @author weiba
 * @date 2024/3/6 14:59
 */
@Log4j2
public class NetDiskJSONCredentials extends WebCookieCredentials {

    private final String jsonFilePath;

    public NetDiskJSONCredentials(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    @Override
    protected NetDiskAuthentication buildNetDiskAuthentication() {
        NetDiskAuthentication diskAuthentication = JSON.parseObject(FileUtil.readString(jsonFilePath, StandardCharsets.UTF_8), NetDiskAuthentication.class);
        if (diskAuthentication == null) {
            throw new IllegalArgumentException("jsonFilePath is not found");
        }
        return diskAuthentication;
    }
}
