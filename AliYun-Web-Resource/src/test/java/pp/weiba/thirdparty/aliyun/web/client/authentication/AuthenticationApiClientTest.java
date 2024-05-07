package pp.weiba.thirdparty.aliyun.web.client.authentication;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.*;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.InitAuthenticationTest;
import pp.weiba.utils.QRUtils;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
class AuthenticationApiClientTest extends InitAuthenticationTest {

    static {
        isHutoolHttpClient = true;
        initAuthentication();
    }

    private static LoginMainResponse loginMainViewData;

    private static GenerateQrCodeResponse generateQrCodeResponse;

    private static QrLonginCheckResponse qrLonginCheckResponse;

    private static CodeByInitTokenResponse codeByToken;

    private static TokenResponse token;

    private final AuthenticationApiClient authenticationApiClient = new AuthenticationApiClient(httpClient);

    @Test
    void initLoginMain() {
        loginMainViewData = authenticationApiClient.initLoginMain();
        assertNotNull(loginMainViewData);
    }

    @Test
    void generateQRcode() {
        initLoginMain();
        generateQrCodeResponse = authenticationApiClient.generateQRcode(loginMainViewData);
    }

    HttpRequest qrLoginCheckInit() {
        generateQRcode();
        return authenticationApiClient.buildQrLoginCheckHttpRequest(generateQrCodeResponse, loginMainViewData);
    }

    @Test
    void qrLoginCheck() {
        HttpRequest httpRequest = qrLoginCheckInit();
        while (true) {
            QRUtils.printQr(generateQrCodeResponse.getContent().getData().getCodeContent());
            ThreadUtil.sleep(5000);

            qrLonginCheckResponse = authenticationApiClient.qrLoginCheck(httpRequest);
            String qrCodeStatus = qrLonginCheckResponse.getContent().getData().getQrCodeStatus();
            if (Objects.equals(qrCodeStatus, "NEW")) {
                log.info("二维码未扫描");
            } else if (Objects.equals(qrCodeStatus, "EXPIRED")) {
                log.info("二维码已失效，请扫描新二维码");
                httpRequest = qrLoginCheckInit();
            } else if (Objects.equals(qrCodeStatus, "SCANED")) {
                log.info("二维码已扫描，请确认登录");
            } else if (Objects.equals(qrCodeStatus, "CANCELED")) {
                log.info("二维码已扫描，但取消登录，请扫描新二维码");
                httpRequest = qrLoginCheckInit();
            } else if (Objects.equals(qrCodeStatus, "CONFIRMED")) {
                log.info("二维码已扫描，且确认登录");
                break;
            }
        }
        // Cookie 回写
        loginMainViewData.setCookieMap(httpRequest.getCookieMap());
    }

    @Test
    void getCodeByInitToken() {
        qrLoginCheck();
        String bizExt = qrLonginCheckResponse.getContent().getData().getBizExt();
        codeByToken = authenticationApiClient.getCodeByInitToken(bizExt, loginMainViewData.getCookieMap());
    }


    @Test
    void getTokenByCode() {
        getCodeByInitToken();
        TokenResponse token = authenticationApiClient.getTokenByCode(codeByToken.getCode(), IdUtil.simpleUUID());
        assertNotNull(token);
    }
}
