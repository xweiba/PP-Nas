package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/*
{
    "drive_id": "18654654",
    "domain_id": "bj29",
    "file_id": "638829ed5df082af754043cba40637f674d213b7",
    "name": "Test1111",
    "type": "folder",
    "created_at": "2022-12-01T04:13:33.714Z",
    "updated_at": "2024-05-10T08:28:39.697Z",
    "hidden": false,
    "starred": false,
    "status": "available",
    "user_meta": "{\"channel\":\"file_upload\",\"client\":\"windows\"}",
    "parent_file_id": "root",
    "encrypt_mode": "none",
    "meta_name_punish_flag": 0,
    "meta_name_investigation_status": 0,
    "creator_type": "User",
    "creator_id": "0075833233234dd187c395ee3c7747b0",
    "creator_name": "weiba",
    "last_modifier_type": "User",
    "last_modifier_id": "0075833233234dd187c395ee3c7747b0",
    "last_modifier_name": "weiba",
    "user_tags": {
        "channel": "file_upload",
        "client": "windows",
        "device_id": "323213213231",
        "device_name": "Unknown"
    },
    "ex_fields_info": {
        "image_count": 0
    },
    "sync_flag": false,
    "sync_device_flag": false,
    "sync_meta": "",
    "download_url": ""
}
* */
/**
* 重命名
*
* @author weiba
* @date 2024/5/10 16:31
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RenameResponse {

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
     * labels
     */
    @JSONField(name = "labels")
    private List<String> labels;
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
     * downloadUrl
     */
    @JSONField(name = "download_url")
    private String downloadUrl;
    /**
     * url
     */
    @JSONField(name = "url")
    private String url;
    /**
     * thumbnail
     */
    @JSONField(name = "thumbnail")
    private String thumbnail;
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
     * imageMediaMetadata
     */
    @JSONField(name = "image_media_metadata")
    private ImageMediaMetadataResponse imageMediaMetadata;
    /**
     * punishFlag
     */
    @JSONField(name = "punish_flag")
    private Integer punishFlag;
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
     * creatorName
     */
    @JSONField(name = "creator_name")
    private String creatorName;
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
     * lastModifierName
     */
    @JSONField(name = "last_modifier_name")
    private String lastModifierName;
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
     * ImageMediaMetadataResponse
     */
    @NoArgsConstructor
    @Data
    public static class ImageMediaMetadataResponse {
        /**
         * width
         */
        @JSONField(name = "width")
        private Integer width;
        /**
         * height
         */
        @JSONField(name = "height")
        private Integer height;
        /**
         * imageTags
         */
        @JSONField(name = "image_tags")
        private List<ImageTagsResponse> imageTags;
        /**
         * imageQuality
         */
        @JSONField(name = "image_quality")
        private ImageQualityResponse imageQuality;

        /**
         * ImageQualityResponse
         */
        @NoArgsConstructor
        @Data
        public static class ImageQualityResponse {
            /**
             * overallScore
             */
            @JSONField(name = "overall_score")
            private Double overallScore;
        }

        /**
         * ImageTagsResponse
         */
        @NoArgsConstructor
        @Data
        public static class ImageTagsResponse {
            /**
             * name
             */
            @JSONField(name = "name")
            private String name;
            /**
             * tagLevel
             */
            @JSONField(name = "tag_level")
            private Integer tagLevel;
            /**
             * confidence
             */
            @JSONField(name = "confidence")
            private Integer confidence;
            /**
             * parentName
             */
            @JSONField(name = "parent_name")
            private String parentName;
            /**
             * centricScore
             */
            @JSONField(name = "centric_score")
            private Double centricScore;
        }
    }

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
