package pp.weiba.framework.resource;

import lombok.Data;
import lombok.experimental.Accessors;
import pp.weiba.framework.KeyValue;

import java.util.List;
import java.util.Map;

/**
 * 分享保存参数
 *
 * @author weiba
 * @date 2024/4/9 16:37
 */
@Data
@Accessors(chain = true)
public class SaveShareParams {

    private String shareId;

    private String shareUserId;

    private String userId;

    // 文件夹必须存在
    private String savePath;

    private Map<String, List<KeyValue>> verify;

    private List<String> resourceIds;

}
