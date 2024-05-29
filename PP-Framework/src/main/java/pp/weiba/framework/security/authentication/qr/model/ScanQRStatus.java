package pp.weiba.framework.security.authentication.qr.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.qr.ScanQRStatusType;

/**
* 扫码状态
*
* @author weiba
* @date 2024/5/29 12:57
*/
@Log4j2
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScanQRStatus {

    /* 扫描状态 */
    private ScanQRStatusType type;

    /* 信息*/
    private String msg;
    
}
