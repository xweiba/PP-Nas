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
* @date 2024/5/17 10:22
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetFileChildDirResponse {


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
     * punishedFileCount
     */
    @JSONField(name = "punished_file_count")
    private Integer punishedFileCount;

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
    }
}
