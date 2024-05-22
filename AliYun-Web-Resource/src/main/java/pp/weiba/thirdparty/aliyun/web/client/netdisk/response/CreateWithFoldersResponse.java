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
* @date 2024/5/21 11:17
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateWithFoldersResponse {


    /**
     * parentFileId
     */
    @JSONField(name = "parent_file_id")
    private String parentFileId;
    /**
     * partInfoList
     */
    @JSONField(name = "part_info_list")
    private List<PartInfoListResponse> partInfoList;
    /**
     * uploadId
     */
    @JSONField(name = "upload_id")
    private String uploadId;
    /**
     * rapidUpload
     */
    @JSONField(name = "rapid_upload")
    private Boolean rapidUpload;
    /**
     * type
     */
    @JSONField(name = "type")
    private String type;
    /**
     * fileId
     */
    @JSONField(name = "file_id")
    private String fileId;
    /**
     * revisionId
     */
    @JSONField(name = "revision_id")
    private String revisionId;
    /**
     * domainId
     */
    @JSONField(name = "domain_id")
    private String domainId;
    /**
     * driveId
     */
    @JSONField(name = "drive_id")
    private String driveId;
    /**
     * fileName
     */
    @JSONField(name = "file_name")
    private String fileName;
    /**
     * encryptMode
     */
    @JSONField(name = "encrypt_mode")
    private String encryptMode;
    /**
     * location
     */
    @JSONField(name = "location")
    private String location;
    /**
     * preHash
     */
    @JSONField(name = "pre_hash")
    private String preHash;
    /**
     * code PreHashMatched 即秒传成功
     */
    @JSONField(name = "code")
    private String code;
    /**
     * message
     */
    @JSONField(name = "message")
    private String message;

    /**
     * PartInfoListResponse
     */
    @NoArgsConstructor
    @Data
    public static class PartInfoListResponse {
        /**
         * partNumber
         */
        @JSONField(name = "part_number")
        private Integer partNumber;
        /**
         * uploadUrl
         */
        @JSONField(name = "upload_url")
        private String uploadUrl;
        /**
         * internalUploadUrl
         */
        @JSONField(name = "internal_upload_url")
        private String internalUploadUrl;
        /**
         * contentType
         */
        @JSONField(name = "content_type")
        private String contentType;
        /**
         * signatureInfo
         */
        @JSONField(name = "signature_info")
        private SignatureInfoResponse signatureInfo;

        /**
         * SignatureInfoResponse
         */
        @NoArgsConstructor
        @Data
        public static class SignatureInfoResponse {
            /**
             * authType
             */
            @JSONField(name = "auth_type")
            private String authType;
            /**
             * stsToken
             */
            @JSONField(name = "sts_token")
            private String stsToken;
        }
    }
}
