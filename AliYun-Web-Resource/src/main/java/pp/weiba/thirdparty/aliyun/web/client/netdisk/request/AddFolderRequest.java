package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 新增文件夹
 *
 * @author weiba
 * @date 2024/4/30 9:58
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AddFolderRequest {

    @JSONField(name = "drive_id")
    private String driveId;
    @JSONField(name = "parent_file_id")
    private String parentFileId;

    /* refuse模式 当有同名文件时会返回之前的文件信息 */
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "check_name_mode")
    private String checkNameMode;
    @JSONField(name = "type")
    private String type;

}
