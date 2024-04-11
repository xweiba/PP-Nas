package pp.weiba.framework.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 结果状态信息
 *
 * @author weiba
 * @date 2024/4/11 13:39
 */
@Data
@Accessors(chain = true)
public class StatusResultInfo {

    private final String id;
    private final boolean success;
    private final String message; // 可选字段，用于提供更多关于操作结果的信息

    public StatusResultInfo(String id, boolean success, String message) {
        this.id = id;
        this.success = success;
        this.message = message;
    }

}
