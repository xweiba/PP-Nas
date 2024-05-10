package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
* 获取文件下载地址，有100MB限制
*
* @author weiba
* @date 2024/5/10 16:52
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetFileDownloadUrlResponse {

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
     * method
     */
    @JSONField(name = "method")
    private String method;
    /**
     * url
     */
    @JSONField(name = "url")
    private String url;
    /**
     * expiration
     */
    @JSONField(name = "expiration")
    private String expiration;
    /**
     * size
     */
    @JSONField(name = "size")
    private Integer size;
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
}
