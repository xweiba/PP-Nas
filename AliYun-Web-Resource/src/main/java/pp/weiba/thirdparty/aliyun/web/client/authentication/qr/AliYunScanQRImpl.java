package pp.weiba.thirdparty.aliyun.web.client.authentication.qr;
import cn.hutool.core.util.IdUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.security.authentication.qr.IScanQR;
import pp.weiba.framework.security.authentication.qr.ScanQRStatusType;
import pp.weiba.framework.security.authentication.qr.model.ScanQRStatus;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.model.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
* 
*
* @author weiba
* @date 2024/5/27 11:34
*/
@Log4j2
public class AliYunScanQRImpl implements IScanQR<NetDiskAuthentication> {

    public static final String QR_LOGIN_AUTHENTICATION = "QR_LOGIN_BIZEXT";

    private AuthenticationApiClient authenticationApiClient;

    private volatile Map<String, HttpRequest> getScanQrResultParams = new ConcurrentHashMap<>();

    public AliYunScanQRImpl(AuthenticationApiClient authenticationApiClient) {
        this.authenticationApiClient = authenticationApiClient;
    }

    @Override
    public String getScanQrUrl(String key) {
        LoginMainResponse loginMainViewData = authenticationApiClient.initLoginMain();
        GenerateQrCodeResponse generateQrCodeResponse = authenticationApiClient.generateQRcode(loginMainViewData);

        // 查询扫描结果的request
        HttpRequest httpRequest = authenticationApiClient.buildQrLoginCheckHttpRequest(generateQrCodeResponse, loginMainViewData);
        getScanQrResultParams.put(key, httpRequest);
        return generateQrCodeResponse.getContent().getData().getCodeContent();
    }

    @Override
    public ScanQRStatus checkScanQrStatus(String key) {
        HttpRequest httpRequest = getScanQrResultParams.get(key);
        // cookie 会回写到 httpRequest
        QrLonginCheckResponse qrLonginCheckResponse = authenticationApiClient.qrLoginCheck(httpRequest);
        ScanQRStatus isScanQrComplate = isScanQrComplate(qrLonginCheckResponse);
        if (isScanQrComplate.getType() == ScanQRStatusType.CONFIRMED) {
            httpRequest.addRequestParams(QR_LOGIN_AUTHENTICATION, qrLonginCheckResponse.getContent().getData().getBizExt());
        }
        return isScanQrComplate;
    }

    @Override
    public NetDiskAuthentication scanQrComplate(String key) {
        HttpRequest httpRequest = getScanQrResultParams.get(key);
        String bizExt = (String)httpRequest.getRequestParams().get(QR_LOGIN_AUTHENTICATION);
        CodeByInitTokenResponse codeByToken = authenticationApiClient.getCodeByInitToken(bizExt, httpRequest.getCookieMap());

        String deviceId = IdUtil.simpleUUID();
        TokenResponse token = authenticationApiClient.getTokenByCode(codeByToken.getCode(), deviceId);
        token.setDeviceId(deviceId);
        return new NetDiskAuthentication().setToken(token);
    }

    public ScanQRStatus isScanQrComplate(QrLonginCheckResponse qrLonginCheckResponse) {
        String qrCodeStatus = qrLonginCheckResponse.getContent().getData().getQrCodeStatus();
        ScanQRStatusType type = null;
        String msg = null;
        if (Objects.equals(qrCodeStatus, "NEW")) {
            type = ScanQRStatusType.NEW;
            msg = "二维码未扫描";
        }else if (Objects.equals(qrCodeStatus, "SCANED")) {
            type = ScanQRStatusType.SCANED;
            msg = "二维码已扫描，请确认登录";
        }  else if (Objects.equals(qrCodeStatus, "EXPIRED")) {
            type = ScanQRStatusType.EXPIRED;
            msg = "二维码已失效，请扫描新二维码";
        } else if (Objects.equals(qrCodeStatus, "CANCELED")) {
            type = ScanQRStatusType.CANCELED;
            msg = "二维码已扫描，但取消登录，请扫描新二维码";
        } else if (Objects.equals(qrCodeStatus, "CONFIRMED")) {
            type = ScanQRStatusType.CONFIRMED;
            msg = "二维码已扫描，且已确认登录成功";
        }
        log.info(msg);
        return new ScanQRStatus(type, msg);
    }
}
