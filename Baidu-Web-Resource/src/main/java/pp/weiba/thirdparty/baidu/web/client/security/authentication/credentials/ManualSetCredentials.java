package pp.weiba.thirdparty.baidu.web.client.security.authentication.credentials;

import lombok.extern.log4j.Log4j2;

/**
 * 手动设置认证信息
 *
 * @author weiba
 * @date 2024/3/8 11:05
 */
@Log4j2
public class ManualSetCredentials extends WebCookieCredentials {

    public ManualSetCredentials(String bduss, String stoken, String ptoken, String baiduid, String ubi) {
        super(bduss, stoken, ptoken, baiduid, ubi);
    }
}
