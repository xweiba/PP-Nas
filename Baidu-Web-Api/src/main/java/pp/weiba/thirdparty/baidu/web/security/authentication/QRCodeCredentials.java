package pp.weiba.thirdparty.baidu.web.security.authentication;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.security.Authentication;

/**
 * 扫码登录
 *
 * @author weiba
 * @date 2024/3/6 14:51
 */
@Log4j2
public class QRCodeCredentials extends AbstractBaiduAuthentication implements IQRCodeCredentials {

    @Override
    public Authentication getAuthentication() {
        return null;
    }

    @Override
    public String getQRUrl() {
        // 获取url后，使用长连接监听回调url
        // https://passport.baidu.com/channel/unicast?channel_id=d1a15e681b977b71951b7579a8463e06&gid=116BCD0-3FB6-4E39-A3FF-FFA78DEEBDE1&tpl=netdisk&_sdkFrom=1&callback=tangram_guid_1709800728969&apiver=v3&tt=1709800883122&_=1709800883124
        return null;
    }
}
