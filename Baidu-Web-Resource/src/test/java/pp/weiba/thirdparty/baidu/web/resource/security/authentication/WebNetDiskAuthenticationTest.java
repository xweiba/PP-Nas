package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.NetDiskAuthentication;

@Slf4j
public class WebNetDiskAuthenticationTest extends InitAuthenticationTest {

    @BeforeAll
    static void init() {
        InitAuthenticationTest.initAhcClientBaiduWebAuthentication();
        NetDiskAuthentication netDiskAuthentication = baiduWebAuthentication.login();
        log.info("登录认证信息：{}", netDiskAuthentication.toString());
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
