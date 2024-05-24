package pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi.request;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.AliYunClientConstants;

/**
* 
*
* @author weiba
* @date 2024/5/24 13:41
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetDownloadUrlRequest {

    public GetDownloadUrlRequest(boolean isBackupDrive, String fileId) {
        this.driveId = isBackupDrive ? AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
        this.fileId = fileId;
    }

    @JSONField(name = "drive_id")
    private String driveId;

    @JSONField(name = "file_id")
    private String fileId;

    @JSONField(name = "expire_sec")
    private Integer expireSec;
    
}
