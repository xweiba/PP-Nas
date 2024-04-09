package pp.weiba.framework.resource;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 资源查询参数
 *
 * @author weiba
 * @date 2024/3/5 16:00
 */
@Accessors(chain = true)
@Data
public class ResourceQueryParams {

    /* 查找路径 */
    private String path;

    /* 关键字 */
    private String keyWord;

    private OrderType orderType = OrderType.CREATE_TIME;

    private SortType sortType = SortType.DESC;

    private Integer pageNo;

    private Integer pageSize;

}
