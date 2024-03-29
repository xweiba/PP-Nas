package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.NetDiskAuthentication;
import pp.weiba.utils.Log;

@Log4j2
public class WebNetDiskAuthenticationTest extends InitAuthenticationTest {

    @BeforeAll
    static void init() {
        InitAuthenticationTest.initAhcClientBaiduWebAuthentication();
        NetDiskAuthentication netDiskAuthentication = baiduWebAuthentication.login();
        log.info(Log.formatJson("netDiskAuthentication:{}", netDiskAuthentication));
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
