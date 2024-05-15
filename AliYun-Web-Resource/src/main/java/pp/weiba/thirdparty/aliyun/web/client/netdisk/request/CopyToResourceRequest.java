package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.GetFileInfoResponse;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* 资源复制
*
* @author weiba
* @date 2024/5/10 17:49
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CopyToResourceRequest {

    public CopyToResourceRequest(String fromDriveId, String toDriveId, String toParentFileid, List<FilesResponse> files) {
        this.fromDriveId = fromDriveId;
        this.toDriveId = toDriveId;
        this.toParentFileid = toParentFileid;
        this.files = files;
    }

    /**
     * files
     */
    @JSONField(name = "files")
    private List<FilesResponse> files;
    /**
     * fromDriveId
     */
    @JSONField(name = "from_drive_id")
    private String fromDriveId;
    /**
     * toDriveId
     */
    @JSONField(name = "to_drive_id")
    private String toDriveId;
    /**
     * toParentFileid
     */
    @JSONField(name = "to_parent_fileId")
    private String toParentFileid;
    /**
     * fromFileIds
     */
    @JSONField(name = "from_file_ids")
    private List<String> fromFileIds;

    /**
     * FilesResponse
     */
    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class FilesResponse {

        /**
         * driveId
         */
        @JSONField(name = "driveId")
        private String driveId;
        /**
         * fileId
         */
        @JSONField(name = "fileId")
        private String fileId;
        /**
         * type
         */
        @JSONField(name = "type")
        private String type;
        /**
         * domainId
         */
        @JSONField(name = "domainId")
        private String domainId;
        /**
         * category
         */
        @JSONField(name = "category")
        private String category;
        /**
         * mimeType
         */
        @JSONField(name = "mimeType")
        private String mimeType;
        /**
         * userTags
         */
        @JSONField(name = "userTags")
        private UserTagsResponse userTags;
        /**
         * rawFile
         */
        @JSONField(name = "rawFile")
//        private RawFileResponse rawFile;
        private GetFileInfoResponse rawFile;
        /**
         * parentFileId
         */
        @JSONField(name = "parentFileId")
        private String parentFileId;
        /**
         * createdAt
         */
        @JSONField(name = "createdAt")
        private Date createdAt;
        /**
         * updatedAt
         */
        @JSONField(name = "updatedAt")
        private Date updatedAt;
        /**
         * tookAt
         */
        @JSONField(name = "tookAt")
        private Long tookAt;
        /**
         * starred
         */
        @JSONField(name = "starred")
        private Boolean starred;
        /**
         * name
         */
        @JSONField(name = "name")
        private String name;
        /**
         * extension
         */
        @JSONField(name = "extension")
        private String extension;
        /**
         * size
         */
        @JSONField(name = "size")
        private Integer size;
        /**
         * punishFlag
         */
        @JSONField(name = "punishFlag")
        private Integer punishFlag;
        /**
         * thumbnail
         */
        @JSONField(name = "thumbnail")
        private String thumbnail;
        /**
         * url
         */
        @JSONField(name = "url")
        private String url;
        /**
         * contentHash
         */
        @JSONField(name = "contentHash")
        private String contentHash;
        /**
         * isSyncFolder
         */
        @JSONField(name = "isSyncFolder")
        private boolean isSyncFolder;
        /**
         * restricted
         */
        @JSONField(name = "restricted")
        private boolean restricted;
        /**
         * isSyncDevice
         */
        @JSONField(name = "isSyncDevice")
        private boolean isSyncDevice;
        /**
         * userMeta
         */
        @JSONField(name = "userMeta")
        private String userMeta;
        /**
         * syncMeta
         */
        @JSONField(name = "syncMeta")
        private String syncMeta;
        /**
         * isDetailLoaded
         */
        @JSONField(name = "isDetailLoaded")
        private boolean isDetailLoaded;
        /**
         * folderSizeInfo
         */
        @JSONField(name = "folderSizeInfo")
        private String folderSizeInfo;

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
         * RawFileResponse
         */
        @NoArgsConstructor
        @Data
        public static class RawFileResponse {
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
             * createdAt
             */
            @JSONField(name = "created_at")
            private String createdAt;
            /**
             * driveId
             */
            @JSONField(name = "drive_id")
            private String driveId;
            /**
             * fileExtension
             */
            @JSONField(name = "file_extension")
            private String fileExtension;
            /**
             * fileId
             */
            @JSONField(name = "file_id")
            private String fileId;
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
             * syncDeviceFlag
             */
            @JSONField(name = "sync_device_flag")
            private Boolean syncDeviceFlag;
            /**
             * syncFlag
             */
            @JSONField(name = "sync_flag")
            private Boolean syncFlag;
            /**
             * syncMeta
             */
            @JSONField(name = "sync_meta")
            private String syncMeta;
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
             * url
             */
            @JSONField(name = "url")
            private String url;
            /**
             * userMeta
             */
            @JSONField(name = "user_meta")
            private String userMeta;
            /**
             * userTags
             */
            @JSONField(name = "user_tags")
            private UserTagsResponse userTags;

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

        /**
         * UserMetaResponse
         */
        @NoArgsConstructor
        @Data
        public static class UserMetaResponse {
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
        }
    }

    public List<String> getFromFileIds() {
        return files.stream().map(FilesResponse::getFileId).collect(Collectors.toList());
    }
}
