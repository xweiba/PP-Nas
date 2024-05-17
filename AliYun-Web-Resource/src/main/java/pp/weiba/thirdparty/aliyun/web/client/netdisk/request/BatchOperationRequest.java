package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;
import lombok.*;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.ClientContants;

/**
* 批处理请求业务参数
*
* @author weiba
* @date 2024/5/10 15:02
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BatchOperationRequest {

    public BatchOperationRequest(String url) {
        this.url = url;
    }

    public BatchOperationRequest(String url, String fileId) {
        this(url, fileId, true);
    }

    public BatchOperationRequest(String url, boolean isBackupDrive) {
        this(url, null, isBackupDrive);
    }


    public BatchOperationRequest(String url, String fileId, boolean isBackupDrive) {
        this.url = url;
        this.id = fileId;
        this.fileId = fileId;
        this.driveId = isBackupDrive ? ClientContants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : ClientContants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
    }

    public BatchOperationRequest(String url, String fileId, String toParentFileId) {
        this(url, fileId, toParentFileId, true);
    }

    public BatchOperationRequest(String url, String fileId, String toParentFileId, boolean isBackupDrive) {
        this(url, fileId, isBackupDrive);
        this.toParentFileId = toParentFileId;
        this.toDriveId = isBackupDrive ? ClientContants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : ClientContants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
    }

    private String url;

    private String method = "POST";

    private String contentType = "application/json";

    private String id;

    private String driveId;

    private String fileId;

    private String toDriveId;

    private String toParentFileId;

    private Object bodyRequest;
    
}
