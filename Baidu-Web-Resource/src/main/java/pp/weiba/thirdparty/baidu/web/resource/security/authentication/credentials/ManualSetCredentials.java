package pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.IManualSetCredentials;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;

/**
 * 手动设置认证信息
 *
 * @author weiba
 * @date 2024/3/8 11:05
 */
@Log4j2
public class ManualSetCredentials implements IManualSetCredentials<Authentication> {

    private final String bduss;

    private final String stoken;

    private final String baiduid;

    public ManualSetCredentials(String bduss, String stoken, String baiduid) {
        this.bduss = bduss;
        this.stoken = stoken;
        this.baiduid = baiduid;
    }

    @Override
    public Authentication getCredential() {
        return new Authentication(bduss, stoken, baiduid);
    }

}
