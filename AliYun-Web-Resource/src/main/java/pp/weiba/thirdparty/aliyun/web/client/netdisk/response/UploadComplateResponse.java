package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
* 文件上传完成响应
*
* @author weiba
* @date 2024/5/22 14:37
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadComplateResponse {


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
     * contentType
     */
    @JSONField(name = "content_type")
    private String contentType;
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
     * hidden
     */
    @JSONField(name = "hidden")
    private Boolean hidden;
    /**
     * size
     */
    @JSONField(name = "size")
    private Integer size;
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
     * uploadId
     */
    @JSONField(name = "upload_id")
    private String uploadId;
    /**
     * parentFileId
     */
    @JSONField(name = "parent_file_id")
    private String parentFileId;
    /**
     * crc64Hash
     */
    @JSONField(name = "crc64_hash")
    private String crc64Hash;
    /**
     * contentHash
     */
    @JSONField(name = "content_hash")
    private String contentHash;
    /**
     * contentHashName
     */
    @JSONField(name = "content_hash_name")
    private String contentHashName;
    /**
     * category
     */
    @JSONField(name = "category")
    private String category;
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
     * userTags
     */
    @JSONField(name = "user_tags")
    private UserTagsResponse userTags;
    /**
     * revisionId
     */
    @JSONField(name = "revision_id")
    private String revisionId;
    /**
     * revisionVersion
     */
    @JSONField(name = "revision_version")
    private Integer revisionVersion;
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
     * location
     */
    @JSONField(name = "location")
    private String location;
    /**
     * contentUri
     */
    @JSONField(name = "content_uri")
    private String contentUri;

    /**
     * UserTagsResponse
     */
    @NoArgsConstructor
    @Data
    public static class UserTagsResponse {
        /**
         * channel
         */
        @JSONField(name = "channel")
        private String channel;
        /**
         * client
         */
        @JSONField(name = "client")
        private String client;
        /**
         * deviceId
         */
        @JSONField(name = "device_id")
        private String deviceId;
        /**
         * deviceName
         */
        @JSONField(name = "device_name")
        private String deviceName;
        /**
         * version
         */
        @JSONField(name = "version")
        private String version;
    }
}
