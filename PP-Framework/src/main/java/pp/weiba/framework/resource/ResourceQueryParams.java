package pp.weiba.framework.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
 * 资源查询参数
 *
 * @author weiba
 * @date 2024/3/5 16:00
 */
@Log4j2
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceQueryParams {

    /* 查找路径 */
    private String path;

    /* 关键字 */
    private String keyWord;

    private OrderType orderType = OrderType.TIME;

    private SortType sortType = SortType.DESC;

    private Integer pageNo;

    private Integer pageSize;

}
