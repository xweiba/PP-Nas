package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 
*
* @author weiba
* @date 2024/5/15 17:34
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetMyShareListResponse {

    /**
     * items
     */
    @JSONField(name = "items")
    private List<ItemsResponse> items;

    /**
     * ItemsResponse
     */
    @NoArgsConstructor
    @Data
    public static class ItemsResponse {
        /**
         * category
         */
        @JSONField(name = "category")
        private String category;
        /**
         * popularity
         */
        @JSONField(name = "popularity")
        private Integer popularity;
        /**
         * browseCount
         */
        @JSONField(name = "browse_count")
        private Integer browseCount;
        /**
         * shareId
         */
        @JSONField(name = "share_id")
        private String shareId;
        /**
         * shareMsg
         */
        @JSONField(name = "share_msg")
        private String shareMsg;
        /**
         * shareName
         */
        @JSONField(name = "share_name")
        private String shareName;
        /**
         * description
         */
        @JSONField(name = "description")
        private String description;
        /**
         * expiration
         */
        @JSONField(name = "expiration")
        private String expiration;
        /**
         * expired
         */
        @JSONField(name = "expired")
        private Boolean expired;
        /**
         * sharePwd
         */
        @JSONField(name = "share_pwd")
        private String sharePwd;
        /**
         * shareUrl
         */
        @JSONField(name = "share_url")
        private String shareUrl;
        /**
         * creator
         */
        @JSONField(name = "creator")
        private String creator;
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
         * fileIdList
         */
        @JSONField(name = "file_id_list")
        private List<String> fileIdList;
        /**
         * previewCount
         */
        @JSONField(name = "preview_count")
        private Integer previewCount;
        /**
         * saveCount
         */
        @JSONField(name = "save_count")
        private Integer saveCount;
        /**
         * downloadCount
         */
        @JSONField(name = "download_count")
        private Integer downloadCount;
        /**
         * status
         */
        @JSONField(name = "status")
        private String status;
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
         * firstFile
         */
        @JSONField(name = "first_file")
        private FirstFileResponse firstFile;
        /**
         * enableFileChangedNotify
         */
        @JSONField(name = "enable_file_changed_notify")
        private Boolean enableFileChangedNotify;
        /**
         * currentSyncStatus
         */
        @JSONField(name = "current_sync_status")
        private Integer currentSyncStatus;
        /**
         * nextSyncStatus
         */
        @JSONField(name = "next_sync_status")
        private Integer nextSyncStatus;
        /**
         * isPhotoCollection
         */
        @JSONField(name = "is_photo_collection")
        private Boolean isPhotoCollection;
        /**
         * shareSubtitle
         */
        @JSONField(name = "share_subtitle")
        private String shareSubtitle;
        /**
         * popularityStr
         */
        @JSONField(name = "popularity_str")
        private String popularityStr;
        /**
         * fullShareMsg
         */
        @JSONField(name = "full_share_msg")
        private String fullShareMsg;
        /**
         * shareTitle
         */
        @JSONField(name = "share_title")
        private String shareTitle;
        /**
         * displayName
         */
        @JSONField(name = "display_name")
        private String displayName;

        /**
         * FirstFileResponse
         */
        @NoArgsConstructor
        @Data
        public static class FirstFileResponse {
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
             * category
             */
            @JSONField(name = "category")
            private String category;
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
             * contentType
             */
            @JSONField(name = "content_type")
            private String contentType;
            /**
             * crc64Hash
             */
            @JSONField(name = "crc64_hash")
            private String crc64Hash;
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
             * mimeType
             */
            @JSONField(name = "mime_type")
            private String mimeType;
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
             * punishFlag
             */
            @JSONField(name = "punish_flag")
            private Integer punishFlag;
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
             * thumbnail
             */
            @JSONField(name = "thumbnail")
            private String thumbnail;
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
            /**
             * videoMediaMetadata
             */
            @JSONField(name = "video_media_metadata")
            private VideoMediaMetadataResponse videoMediaMetadata;

            /**
             * VideoMediaMetadataResponse
             */
            @NoArgsConstructor
            @Data
            public static class VideoMediaMetadataResponse {
                /**
                 * addressLine
                 */
                @JSONField(name = "address_line")
                private String addressLine;
                /**
                 * city
                 */
                @JSONField(name = "city")
                private String city;
                /**
                 * country
                 */
                @JSONField(name = "country")
                private String country;
                /**
                 * district
                 */
                @JSONField(name = "district")
                private String district;
                /**
                 * duration
                 */
                @JSONField(name = "duration")
                private String duration;
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
                 * province
                 */
                @JSONField(name = "province")
                private String province;
                /**
                 * time
                 */
                @JSONField(name = "time")
                private String time;
                /**
                 * township
                 */
                @JSONField(name = "township")
                private String township;
                /**
                 * videoMediaAudioStream
                 */
                @JSONField(name = "video_media_audio_stream")
                private List<VideoMediaAudioStreamResponse> videoMediaAudioStream;
                /**
                 * videoMediaVideoStream
                 */
                @JSONField(name = "video_media_video_stream")
                private List<VideoMediaVideoStreamResponse> videoMediaVideoStream;
                /**
                 * width
                 */
                @JSONField(name = "width")
                private Integer width;

                /**
                 * VideoMediaAudioStreamResponse
                 */
                @NoArgsConstructor
                @Data
                public static class VideoMediaAudioStreamResponse {
                    /**
                     * bitRate
                     */
                    @JSONField(name = "bit_rate")
                    private String bitRate;
                    /**
                     * channelLayout
                     */
                    @JSONField(name = "channel_layout")
                    private String channelLayout;
                    /**
                     * channels
                     */
                    @JSONField(name = "channels")
                    private Integer channels;
                    /**
                     * codeName
                     */
                    @JSONField(name = "code_name")
                    private String codeName;
                    /**
                     * duration
                     */
                    @JSONField(name = "duration")
                    private String duration;
                    /**
                     * sampleRate
                     */
                    @JSONField(name = "sample_rate")
                    private String sampleRate;
                }

                /**
                 * VideoMediaVideoStreamResponse
                 */
                @NoArgsConstructor
                @Data
                public static class VideoMediaVideoStreamResponse {
                    /**
                     * bitrate
                     */
                    @JSONField(name = "bitrate")
                    private String bitrate;
                    /**
                     * clarity
                     */
                    @JSONField(name = "clarity")
                    private String clarity;
                    /**
                     * codeName
                     */
                    @JSONField(name = "code_name")
                    private String codeName;
                    /**
                     * duration
                     */
                    @JSONField(name = "duration")
                    private String duration;
                    /**
                     * fps
                     */
                    @JSONField(name = "fps")
                    private String fps;
                    /**
                     * rotate
                     */
                    @JSONField(name = "rotate")
                    private String rotate;
                }
            }
        }
    }
}
