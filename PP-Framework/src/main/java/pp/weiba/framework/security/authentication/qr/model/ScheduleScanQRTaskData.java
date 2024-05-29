package pp.weiba.framework.security.authentication.qr.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
* 
*
* @author weiba
* @date 2024/5/29 13:09
*/
@Log4j2
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleScanQRTaskData<T> {

    public ScheduleScanQRTaskData(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    private String scheduleId;

    private ScanQRStatus scanQRStatus;

    private T scanQrComplate;


}
