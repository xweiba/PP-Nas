package pp.weiba.framework.resource.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 资源容量信息
 * @date 2023/6/7 0:45
 */
@Accessors(chain = true)
@Data
public class ResourceCapacity {

    /* 已使用 */
    private long use;

    /* 总量 */
    private long total;

}
