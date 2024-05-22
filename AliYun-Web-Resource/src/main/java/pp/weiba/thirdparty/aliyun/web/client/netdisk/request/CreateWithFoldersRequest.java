package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.AliYunClientConstants;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.AliYunUtils;

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
public class CreateWithFoldersRequest {

    public CreateWithFoldersRequest(boolean isBackupDrive, String parentFileId, String name, String filePath, long fileSize) {
        this.driveId = isBackupDrive ? AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
        this.parentFileId = parentFileId;
        this.name = name;
        this.filePath = filePath;
        this.size = fileSize;
    }

    @JSONField(serialize=false)
    private String filePath;

    @JSONField(serialize=false)
    private List<PartInfoListRequest> partInfoListAll;

    /**
     * driveId
     */
    @JSONField(name = "drive_id")
    private String driveId;
    /**
     * partInfoList 分片序列号，从 1 开始
     * 单个文件分片最大限制5GB，最小限制 100KB
     */
    @JSONField(name = "part_info_list")
    private List<PartInfoListRequest> partInfoList;
    /**
     * parentFileId
     */
    @JSONField(name = "parent_file_id")
    private String parentFileId;
    /**
     * name 文件名称，按照 utf8 编码最长 1024 字节，不能以 / 结尾
     */
    @JSONField(name = "name")
    private String name;
    /**
     * type
     * file | folder
     */
    @JSONField(name = "type")
    private String type = "file";
    /**
     * checkNameMode
     *      auto_rename 自动重命名，存在并发问题
     *      refuse 同名不创建
     *      ignore 同名文件可创建
     */
    @JSONField(name = "check_name_mode")
    private String checkNameMode = "auto_rename";
    /**
     * size
     */
    @JSONField(name = "size")
    private Long size;
    /**
     * createScene
     */
    @JSONField(name = "create_scene")
    private String createScene = "file_upload";
    /**
     * deviceName
     */
    @JSONField(name = "device_name")
    private String deviceName;
    /**
     * preHash
     */
    @JSONField(name = "pre_hash")
    private String preHash;
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
     * proofCode
     */
    @JSONField(name = "proof_code")
    private String proofCode;
    /**
     * proofVersion
     */
    @JSONField(name = "proof_version")
    private String proofVersion;

    /**
     * PartInfoListResponse 10MB 一个分片，必须按顺序上传
     */
    @NoArgsConstructor
    @Data
    public static class PartInfoListRequest {
        /**
         * partNumber
         */
        @JSONField(name = "part_number")
        private Integer partNumber;
    }

    public List<PartInfoListRequest> getPartInfoListAll() {
        if (partInfoListAll == null) {
            partInfoListAll = AliYunUtils.getPartInfoList(size);
        }
        return partInfoListAll;
    }

    public List<PartInfoListRequest> getPartInfoList() {
        if (partInfoList == null) {
            List<PartInfoListRequest> partInfoListAll = getPartInfoListAll();
            if (partInfoListAll.size() <= 20) {
                partInfoList = partInfoListAll;
            } else {
                partInfoList = partInfoListAll.subList(0, 20);
            }
        }
        return partInfoList;
    }
}
