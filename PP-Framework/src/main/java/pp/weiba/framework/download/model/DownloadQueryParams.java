package pp.weiba.framework.download.model;

import lombok.Data;
import lombok.experimental.Accessors;
import pp.weiba.framework.resource.model.OrderType;
import pp.weiba.framework.resource.model.SortType;

/**
 * 下载查询参数
 *
 * @author weiba
 * @date 2024/4/11 13:30
 */
@Data
@Accessors(chain = true)
public class DownloadQueryParams {

    private DownloadStatus status = DownloadStatus.ACTIVE;

    private SortType sortType;

    private OrderType orderType = OrderType.CREATE_TIME;

    private Integer pageNo;

    private Integer pageSize;

}
