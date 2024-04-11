package pp.weiba.thirdparty.baidu.web.client.netdisk.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
 * 重命名信息
 *
 * @author weiba
 * @date 2024/4/2 9:43
 */
@Log4j2
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RenameRequest {
    /* fsid*/
    private String id;

    /* 原路径 */
    private String path;

    /* 新名称 */
    private String newname;

}
