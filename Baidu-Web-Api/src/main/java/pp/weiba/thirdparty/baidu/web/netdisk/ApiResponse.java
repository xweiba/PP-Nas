package pp.weiba.thirdparty.baidu.web.netdisk;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 百度网盘 Api 返回信息
 *
 * @author weiba
 * @date 2024/3/7 14:53
 */
@Accessors(chain = true)
@Getter
@Setter
public class ApiResponse {

    // 状态id
    private Integer errno;

    // 请求id
    private Long requestId;

}
