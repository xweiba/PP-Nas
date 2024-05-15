package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
* 
*
* @author weiba
* @date 2024/5/15 16:02
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FirstFileResponse {

    /**
     * trashed
     */
    @JSONField(name = "trashed")
    private Boolean trashed;
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
     * createdAt
     */
    @JSONField(name = "created_at")
    private String createdAt;
    /**
     * encryptMode
     */
    @JSONField(name = "encrypt_mode")
    private String encryptMode;
    /**
     * hidden
     */
    @JSONField(name = "hidden")
    private Boolean hidden;
    /**
     * name
     */
    @JSONField(name = "name")
    private String name;
    /**
     * parentFileId
     */
    @JSONField(name = "parent_file_id")
    private String parentFileId;
    /**
     * starred
     */
    @JSONField(name = "starred")
    private Boolean starred;
    /**
     * status
     */
    @JSONField(name = "status")
    private String status;
    /**
     * type
     */
    @JSONField(name = "type")
    private String type;
    /**
     * updatedAt
     */
    @JSONField(name = "updated_at")
    private String updatedAt;
}
