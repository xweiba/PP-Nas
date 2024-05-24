package pp.weiba.thirdparty.aliyun.web.client.security.authentication;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.authentication.model.NetDiskAuthentication;
import pp.weiba.utils.LogUtils;

@Log4j2
public class WebNetDiskAuthenticationTest extends InitAuthenticationTest {

    static {
        InitAuthenticationTest.initAhcClientBaiduWebAuthentication();
        NetDiskAuthentication netDiskAuthentication = baiduWebAuthentication.login();
        log.debug(LogUtils.formatJson("netDiskAuthentication:{}", netDiskAuthentication));
    }

    /*
     * 登出接口，默认忽略，避免登录信息失效
     * */
    @Test
    @Disabled
    void logout() {
        baiduWebAuthentication.logout();
    }


}
