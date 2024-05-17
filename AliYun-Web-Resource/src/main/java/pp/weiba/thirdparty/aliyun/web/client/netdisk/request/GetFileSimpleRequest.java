package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.ClientContants;

/**
* 简单请求信息
*
* @author weiba
* @date 2024/5/17 10:33
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetFileSimpleRequest {

    public GetFileSimpleRequest(boolean isBackupDrive, String fileId) {
        this.driveId = isBackupDrive ? ClientContants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : ClientContants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
        this.fileId = fileId;
    }

    /**
     * driveId
     */
    @JSONField(name = "drive_id")
    private String driveId;
    /**
     * fileId
     */
    @JSONField(name = "file_id")
    private String fileId;
}
