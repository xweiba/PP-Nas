package pp.weiba.service.qr;

import pp.weiba.framework.KeyValue;

/**
* 获取QrUrl
*
* @author weiba
* @date 2024/5/27 11:27
*/
public interface IScanQR {
    
    KeyValue getScanQrUrl(String type);

    <T> T getResult(String key);
    
}
