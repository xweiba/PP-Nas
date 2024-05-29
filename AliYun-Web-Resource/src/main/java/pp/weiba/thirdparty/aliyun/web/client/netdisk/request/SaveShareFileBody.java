package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.aliyun.web.client.core.AliYunClientConstants;

/**
* 
*
* @author weiba
* @date 2024/5/17 10:47
*/
@NoArgsConstructor
@Data
@Log4j2
public class SaveShareFileBody {

    public SaveShareFileBody(boolean isBackupDrive, String toParentFileId, String shareId, String shareFileId) {
        this.toDriveId = isBackupDrive ? AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
        this.toParentFileId = toParentFileId;
        this.shareId = shareId;
        this.fileId = shareFileId;
    }

    /**
     * fileId
     */
    @JSONField(name = "file_id")
    private String fileId;
    /**
     * shareId
     */
    @JSONField(name = "share_id")
    private String shareId;
    /**
     * autoRename
     */
    @JSONField(name = "auto_rename")
    private Boolean autoRename = true;
    /**
     * toParentFileId
     */
    @JSONField(name = "to_parent_file_id")
    private String toParentFileId;
    /**
     * toDriveId
     */
    @JSONField(name = "to_drive_id")
    private String toDriveId;

}
