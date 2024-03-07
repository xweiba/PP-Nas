package pp.weiba.thirdparty.baidu.web.netdisk.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.netdisk.ApiResponse;

/**
 * 容量信息
 *
 * @author weiba
 * @date 2024/3/7 14:54
 */
@Log4j2
@Accessors(chain = true)
@Getter
@Setter
public class Capacity extends ApiResponse {

    // 已使用
    private Long used;

    // 总容量
    private Long total;

}
