package pp.weiba.thirdparty.aliyun.web.client.authentication.credentials;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.aliyun.web.client.authentication.ALiYunAuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.model.NetDiskAuthentication;
import pp.weiba.utils.JSONUtils;

/**
 * 手动设置
 *
 * @author weiba
 * @date 2024/4/29 17:40
 */
@Log4j2
public class JsonStrSetCredentials extends WebAuthCredentials {

    private final String jsonStr;

    public JsonStrSetCredentials(ALiYunAuthenticationApiClient authenticationApiClient, String jsonStr) {
        super(authenticationApiClient);
        if (!JSONUtils.isStrJSONValid(jsonStr)) {
            throw new RuntimeException("请传入json字符串");
        }
        this.jsonStr = jsonStr;
    }

    public void buildCredential() {
        this.credentialData = JSONUtils.toBean(jsonStr, NetDiskAuthentication.class);
    }
}
