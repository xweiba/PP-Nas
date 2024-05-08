package pp.weiba.thirdparty.aliyun.web.client.authentication;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.*;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.InitAuthenticationTest;
import pp.weiba.utils.FileUtils;
import pp.weiba.utils.JSONUtils;
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

    public static final String TOKEN_SAVE_DIR_PATH = "/src/test/resources/token/";

    @Test
    @Disabled
    void initLoginMain() {
        loginMainViewData = authenticationApiClient.initLoginMain();
        assertNotNull(loginMainViewData);
    }

    @Test
    @Disabled
    void generateQRcode() {
        initLoginMain();
        generateQrCodeResponse = authenticationApiClient.generateQRcode(loginMainViewData);
    }

    HttpRequest qrLoginCheckInit() {
        generateQRcode();
        return authenticationApiClient.buildQrLoginCheckHttpRequest(generateQrCodeResponse, loginMainViewData);
    }

    @Test
    @Disabled
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
    @Disabled
    void getCodeByInitToken() {
        qrLoginCheck();
        String bizExt = qrLonginCheckResponse.getContent().getData().getBizExt();
        codeByToken = authenticationApiClient.getCodeByInitToken(bizExt, loginMainViewData.getCookieMap());
    }


    @Test
    @Disabled
    void getTokenByCode() {
        getCodeByInitToken();
        String deviceId = IdUtil.simpleUUID();
        TokenResponse token = authenticationApiClient.getTokenByCode(codeByToken.getCode(), deviceId);
        assertNotNull(token);
        token.setDeviceId(deviceId);
        FileUtils.saveJsonToWorkDir(token, TOKEN_SAVE_DIR_PATH, token.getUserId());
    }

    @Test
    @Disabled
    void readToken() {
        String fileName = "007589d773394dd187c395ee3c7747b0";
        String tokenJsonStr = FileUtils.readJsonToWorkDir(TOKEN_SAVE_DIR_PATH, fileName);
        assertNotNull(tokenJsonStr);
        token = JSONUtils.toBean(tokenJsonStr, TokenResponse.class);
        assertNotNull(token);
    }


    @Test
    @Disabled
    void refreshTokenByJson() {
        readToken();
        token = authenticationApiClient.refreshToken(token.getRefreshToken());
        assertNotNull(token);
    }

    @Test
    @Disabled
    void refreshToken() {
        token = authenticationApiClient.refreshToken("37e38246c11c44b28e9d2115955b9237");
        assertNotNull(token);
    }


    @Test
    @Disabled
    void refreshTokenByApp() {
        TokenResponse tokenResponse = authenticationApiClient.refreshTokenByApp("37e38246c11c44b28e9d2115955b9237");
        assertNotNull(tokenResponse);
    }
}
