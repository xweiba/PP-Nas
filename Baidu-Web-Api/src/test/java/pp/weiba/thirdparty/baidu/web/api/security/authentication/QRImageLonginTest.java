package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.baidu.web.api.netdisk.utils.BaiduNetDiskWebScript;
import pp.weiba.utils.QRUtils;

import java.util.Date;

@Slf4j
class QRImageLonginTest {


    QrInfo qrResponse;
    LoginQRParams loginQRParams;
    CheckLoginResponse checkLoginResponse;
    private String decode = "https://wappass.baidu.com/wp/?qrlogin&t=1711424040&error=0&sign=e565566abb3969216b11e204e1453072&cmd=login&lp=pc&tpl=netdisk&adapter=3&logPage=pc_loginv5_1711424038%2ClogPage%3Aloginv5&qrloginfrom=pc&local=%E6%AD%A6%E6%B1%89";

    @Test
    void getQRImage() {
        String gid = BaiduNetDiskWebScript.loginGid();
        long tt = new Date().getTime();
        String callback = "tangram_guid_" + (tt + 1);
        String logPage = BaiduNetDiskWebScript.loginTraceId() + ",logPage:loginv5";
        long _ = tt + 2;
        loginQRParams = new LoginQRParams()
                .setGid(gid)
                .setTt(tt)
                .setCallback(callback)
                .setLogPage(logPage)
                .set_(_);
        String getQRImageHttpUrlResponseBody = HttpUtil.get(QRImageLongin.getQRImageHttpUrl(loginQRParams));
        qrResponse = QRImageLongin.getQRImageUrl(getQRImageHttpUrlResponseBody);
        // 解析二维码
        decode = QRUtils.decode("https://" + qrResponse.getImgurl());
        // 打印到控制台
        log.info(decode);
        QRUtils.printQr(decode);
    }

    @Test
    void loginCheck() {
        getQRImage();
        log.info("等待扫码....");
        while (true) {
            String channelId = qrResponse.getSign();
            String gid = loginQRParams.getGid();
            String callback = loginQRParams.getCallback();
            HttpResponse loginCheck = HttpRequest.get(QRImageLongin.checkScanQRCallbackUrl(channelId, gid, callback)).timeout(600000).execute();
            String responseBodyStr = QRImageLongin.getResponseBodyFormat(loginCheck.body());
            if (responseBodyStr.contains("\"errno\":1")) {
                log.info("等待扫码....");
            }
            if (responseBodyStr.contains("\"errno\":0")) {
                if (responseBodyStr.contains("\\\"status\\\":0")) {
                    log.info("登录成功");
                    checkLoginResponse = QRImageLongin.buildCheckLoginResponse(responseBodyStr);
                    break;
                } else {
                    log.info("已扫码，等待确认....");
                }
            }
        }
    }


    @Test
    void qrLogIn() {
        loginCheck();
        String loginUrl = QRImageLongin.qrLogInUrl(checkLoginResponse.getChannelV().getV());
        HttpResponse qrLogIn = HttpRequest.get(loginUrl).timeout(600000).execute();
        log.info(qrLogIn.body());
    }
}
