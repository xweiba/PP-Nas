package pp.weiba.framework.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
 * 资源容量信息
 *
 * @author weiba
 * @date 2024/3/5 15:48
 */
@Log4j2
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceCapacity {

    /* 已使用 */
    private long use;

    /* 总量 */
    private long total;

}
