package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.NetDiskAuthentication;
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
