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
* @date 2024/5/17 10:31
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetFileSimpleResponse {


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
     * hidden
     */
    @JSONField(name = "hidden")
    private Boolean hidden;
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
     * userMeta
     */
    @JSONField(name = "user_meta")
    private String userMeta;
    /**
     * parentFileId
     */
    @JSONField(name = "parent_file_id")
    private String parentFileId;
    /**
     * encryptMode
     */
    @JSONField(name = "encrypt_mode")
    private String encryptMode;
    /**
     * metaNamePunishFlag
     */
    @JSONField(name = "meta_name_punish_flag")
    private Integer metaNamePunishFlag;
    /**
     * metaNameInvestigationStatus
     */
    @JSONField(name = "meta_name_investigation_status")
    private Integer metaNameInvestigationStatus;
    /**
     * creatorType
     */
    @JSONField(name = "creator_type")
    private String creatorType;
    /**
     * creatorId
     */
    @JSONField(name = "creator_id")
    private String creatorId;
    /**
     * lastModifierType
     */
    @JSONField(name = "last_modifier_type")
    private String lastModifierType;
    /**
     * lastModifierId
     */
    @JSONField(name = "last_modifier_id")
    private String lastModifierId;
    /**
     * exFieldsInfo
     */
    @JSONField(name = "ex_fields_info")
    private ExFieldsInfoResponse exFieldsInfo;
    /**
     * syncFlag
     */
    @JSONField(name = "sync_flag")
    private Boolean syncFlag;
    /**
     * syncDeviceFlag
     */
    @JSONField(name = "sync_device_flag")
    private Boolean syncDeviceFlag;
    /**
     * syncMeta
     */
    @JSONField(name = "sync_meta")
    private String syncMeta;
    /**
     * trashed
     */
    @JSONField(name = "trashed")
    private Boolean trashed;
    /**
     * downloadUrl
     */
    @JSONField(name = "download_url")
    private String downloadUrl;

    /**
     * ExFieldsInfoResponse
     */
    @NoArgsConstructor
    @Data
    public static class ExFieldsInfoResponse {
        /**
         * imageCount
         */
        @JSONField(name = "image_count")
        private Integer imageCount;
        /**
         * storyIds
         */
        @JSONField(name = "story_ids")
        private String storyIds;
        /**
         * storyImageScore
         */
        @JSONField(name = "story_image_score")
        private Integer storyImageScore;
    }
}
