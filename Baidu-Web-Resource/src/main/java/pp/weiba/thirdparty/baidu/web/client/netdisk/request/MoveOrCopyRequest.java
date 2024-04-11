package pp.weiba.thirdparty.baidu.web.client.netdisk.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 移动或拷贝参数
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2024/4/3 17:03
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoveOrCopyRequest {
    private String path;
    private String dest;
    private String newname;
}
