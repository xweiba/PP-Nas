package pp.weiba.framework.security.authentication.qr;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.IBusinessType;
import pp.weiba.framework.security.authentication.qr.model.ScanQRStatus;

/**
* 业务类型对应的扫码
*
* @author weiba
* @date 2024/5/28 16:34
*/
@Log4j2
public class BusinessTypeScanQR<T> implements IScanQR<T> {
    
    private IBusinessType businessType;

    private IScanQR<T> scanQRImpl;

    public BusinessTypeScanQR(IBusinessType businessType, IScanQR<T> scanQRImpl) {
        this.businessType = businessType;
        this.scanQRImpl = scanQRImpl;
    }

    @Override
    public String getScanQrUrl(String key) {
        return scanQRImpl.getScanQrUrl(key);
    }

    @Override
    public ScanQRStatus checkScanQrStatus(String key) {
        return scanQRImpl.checkScanQrStatus(key);
    }

    @Override
    public T scanQrComplate(String key) {
        return scanQRImpl.scanQrComplate(key);
    }
}
