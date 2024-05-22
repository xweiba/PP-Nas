package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.AliYunClientConstants;

/**
* 
*
* @author weiba
* @date 2024/5/17 10:18
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetFileChildDirRequest {

    public GetFileChildDirRequest(boolean isBackupDrive) {
        this.driveId = isBackupDrive ? AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
    }

    public GetFileChildDirRequest(boolean isBackupDrive, String parentFileId) {
        this.driveId = isBackupDrive ? AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
        this.parentFileId = parentFileId;
    }

    /**
     * driveId
     */
    @JSONField(name = "drive_id")
    private String driveId;
    /**
     * parentFileId
     */
    @JSONField(name = "parent_file_id")
    private String parentFileId = "root";
    /**
     * limit
     */
    @JSONField(name = "limit")
    private Integer limit = 100;
    /**
     * marker
     */
    @JSONField(name = "marker")
    private String marker;
    /**
     * orderBy
     */
    @JSONField(name = "order_by")
    private String orderBy = "updated_at";
    /**
     * orderDirection
     */
    @JSONField(name = "order_direction")
    private String orderDirection = "DESC";
}
