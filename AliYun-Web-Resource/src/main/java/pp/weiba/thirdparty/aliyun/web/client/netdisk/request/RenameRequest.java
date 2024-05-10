package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.ClientContants;
/*
{"drive_id":"18654654","file_id":"638829ed5df082af754043cba40637f674d213b7","name":"Test1111","check_name_mode":"refuse"}
* */
/**
* 重命名
*
* @author weiba
* @date 2024/5/10 16:30
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RenameRequest {

    public RenameRequest(String fileId, String name) {
        this(fileId, name, true);
    }

    public RenameRequest(String fileId, String name, boolean isBackupDrive) {
        this.driveId = isBackupDrive ? ClientContants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : ClientContants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
        this.fileId = fileId;
        this.name = name;
        this.checkNameMode = "refuse";
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
    /**
     * name
     */
    @JSONField(name = "name")
    private String name;
    /**
     * checkNameMode
     */
    @JSONField(name = "check_name_mode")
    private String checkNameMode;

}
