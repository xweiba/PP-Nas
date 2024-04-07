package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 回收站文件还原
 *
 * @author weiba
 * @date 2024/4/7 9:43
 */

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecycleRestoreResponse extends ApiResponse {

    private String taskid;

    private List<String> faillist;

}
