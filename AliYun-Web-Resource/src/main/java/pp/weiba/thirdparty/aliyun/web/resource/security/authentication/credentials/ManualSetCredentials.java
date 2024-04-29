package pp.weiba.thirdparty.aliyun.web.resource.security.authentication.credentials;

import lombok.extern.log4j.Log4j2;

/**
 * 手动设置
 *
 * @author weiba
 * @date 2024/4/29 17:40
 */
@Log4j2
public class ManualSetCredentials extends WebAuthCredentials {

    public ManualSetCredentials(String authorization) {
        super(authorization);
    }
}
