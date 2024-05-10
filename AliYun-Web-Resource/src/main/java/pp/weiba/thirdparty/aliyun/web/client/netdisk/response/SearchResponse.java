package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 搜索
*
* @author weiba
* @date 2024/5/10 17:23
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchResponse {

    /**
     * items
     */
    @JSONField(name = "items")
    private List<ItemsResponse> items;
    /**
     * nextMarker
     */
    @JSONField(name = "next_marker")
    private String nextMarker;

    /**
     * ItemsResponse
     */
    @NoArgsConstructor
    @Data
    public static class ItemsResponse {
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
         * channel
         */
        @JSONField(name = "channel")
        private String channel;
        /**
         * videoMediaMetadata
         */
        @JSONField(name = "video_media_metadata")
        private VideoMediaMetadataResponse videoMediaMetadata;

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
             * exif
             */
            @JSONField(name = "exif")
            private String exif;
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

        /**
         * VideoMediaMetadataResponse
         */
        @NoArgsConstructor
        @Data
        public static class VideoMediaMetadataResponse {
            /**
             * time
             */
            @JSONField(name = "time")
            private String time;
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
             * location
             */
            @JSONField(name = "location")
            private String location;
            /**
             * country
             */
            @JSONField(name = "country")
            private String country;
            /**
             * province
             */
            @JSONField(name = "province")
            private String province;
            /**
             * city
             */
            @JSONField(name = "city")
            private String city;
            /**
             * district
             */
            @JSONField(name = "district")
            private String district;
            /**
             * township
             */
            @JSONField(name = "township")
            private String township;
            /**
             * addressLine
             */
            @JSONField(name = "address_line")
            private String addressLine;
            /**
             * videoMediaVideoStream
             */
            @JSONField(name = "video_media_video_stream")
            private List<?> videoMediaVideoStream;
            /**
             * videoMediaAudioStream
             */
            @JSONField(name = "video_media_audio_stream")
            private List<?> videoMediaAudioStream;
            /**
             * duration
             */
            @JSONField(name = "duration")
            private String duration;
        }
    }
}
