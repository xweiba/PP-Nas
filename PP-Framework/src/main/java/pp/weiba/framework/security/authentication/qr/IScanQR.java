package pp.weiba.framework.security.authentication.qr;

import pp.weiba.framework.security.authentication.qr.model.ScanQRStatus;

/**
* 获取QrUrl
*
* @author weiba
* @date 2024/5/27 11:27
*/
public interface IScanQR<T> {

    /**
     * 获取扫码的url
     *
     * @param key 该用户标识
     * @return
     * @author weiba
     * @date 2024/5/28 15:07
     */
    String getScanQrUrl(String key);

    /**
     * 判断扫码状态
     *
     * @param key 该用户标识
     * @return
     * @author weiba
     * @date 2024/5/28 15:07
     */
    ScanQRStatus checkScanQrStatus(String key);

    /**
     * 完成扫码后构建需要的信息
     *
     * @param key 该用户标识
     * @return
     * @author weiba
     * @date 2024/5/28 15:07
     */
    T scanQrComplate(String key);

}
