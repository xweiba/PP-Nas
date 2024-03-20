package pp.weiba.thirdparty.baidu.web.api.netdisk.request;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class BaiduNetDiskWebQueryShareFileParams {

    private String shareId;

    private String token;

    private String dir;

    private QueryShareOrderType order;

    private SortType desc;

    private Integer pageNo;

    private Integer pageSize;


}
