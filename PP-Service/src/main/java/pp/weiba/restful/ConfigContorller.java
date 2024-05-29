package pp.weiba.restful;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import lombok.extern.log4j.Log4j2;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.Context;
import pp.weiba.framework.KeyValue;
import pp.weiba.framework.security.authentication.qr.IScanQR;
import pp.weiba.framework.security.authentication.qr.model.ScanQRStatus;
import pp.weiba.framework.utils.UserInfoUtils;
import pp.weiba.thirdparty.aliyun.web.client.authentication.model.NetDiskAuthentication;

/**
* 配置类
*
* @author weiba
* @date 2024/5/27 10:54
*/
@Log4j2
@Controller
@Configuration
public class ConfigContorller {

    @Inject
    IScanQR<NetDiskAuthentication> aliyunScanQr;

    @Mapping("/login/qr")
    public String loginQr(Context ctx){
        UserInfoUtils.setCurrentThreadUserInfo(new KeyValue("user", "1"));
        String scanQrUrl = aliyunScanQr.getScanQrUrl("1");
        ctx.contentType("text/html;charset=UTF-8");
        QrConfig qrConfig = (new QrConfig(500, 500)).setMargin(5);
        String s = QrCodeUtil.generateAsBase64(scanQrUrl, qrConfig, "svg");
        return "<img src='" + s + "'>";
    }

    @Mapping("/login/qr/status")
    public ScanQRStatus qrStatus(String userId){
        ScanQRStatus diskAuthentication = aliyunScanQr.checkScanQrStatus(userId);
        return aliyunScanQr.checkScanQrStatus("1");
    }

    @Mapping("/login/qr/result")
    public NetDiskAuthentication qrResult(String userId){
        NetDiskAuthentication diskAuthentication = aliyunScanQr.scanQrComplate(userId);
        return diskAuthentication;
    }
    
}
