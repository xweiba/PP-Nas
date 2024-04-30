package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 新增文件夹返回信息
 *
 * @author weiba
 * @date 2024/4/30 10:05
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddFolderResponse {

    @JSONField(name = "parent_file_id")
    private String parentFileId;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "file_id")
    private String fileId;
    @JSONField(name = "domain_id")
    private String domainId;
    @JSONField(name = "drive_id")
    private String driveId;
    @JSONField(name = "file_name")
    private String fileName;
    @JSONField(name = "encrypt_mode")
    private String encryptMode;

}
