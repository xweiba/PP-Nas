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
* @date 2024/5/17 10:02
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetShareFileDetailResponse {

    /**
     * driveId
     */
    @JSONField(name = "drive_id")
    private String driveId;
    /**
     * domainId
     */
    @JSONField(name = "domain_id")
    private String domainId;
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
     * name
     */
    @JSONField(name = "name")
    private String name;
    /**
     * type
     */
    @JSONField(name = "type")
    private String type;
    /**
     * createdAt
     */
    @JSONField(name = "created_at")
    private String createdAt;
    /**
     * updatedAt
     */
    @JSONField(name = "updated_at")
    private String updatedAt;
    /**
     * fileExtension
     */
    @JSONField(name = "file_extension")
    private String fileExtension;
    /**
     * mimeType
     */
    @JSONField(name = "mime_type")
    private String mimeType;
    /**
     * mimeExtension
     */
    @JSONField(name = "mime_extension")
    private String mimeExtension;
    /**
     * size
     */
    @JSONField(name = "size")
    private Long size;
    /**
     * parentFileId
     */
    @JSONField(name = "parent_file_id")
    private String parentFileId;
    /**
     * category
     */
    @JSONField(name = "category")
    private String category;
    /**
     * punishFlag
     */
    @JSONField(name = "punish_flag")
    private Integer punishFlag;
    /**
     * revisionId
     */
    @JSONField(name = "revision_id")
    private String revisionId;
}
