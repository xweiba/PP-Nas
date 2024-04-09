package pp.weiba.framework.resource;

import lombok.Data;
import pp.weiba.framework.KeyValue;

import java.util.List;
import java.util.Map;

/**
 * 分享查询
 *
 * @author weiba
 * @date 2024/4/9 15:32
 */
@Data
public class ShareQueryParams {

    private String shareId;

    private String shareUserId;

    private Map<String, List<KeyValue>> verify;

    private String path;

    private OrderType orderType = OrderType.CREATE_TIME;

    private SortType sortType = SortType.DESC;

    private Integer pageNo;

    private Integer pageSize;

}
