package pp.weiba.thirdparty.baidu.web.client.netdisk.request;

import lombok.Data;
import lombok.experimental.Accessors;
import pp.weiba.framework.KeyValue;
import pp.weiba.framework.resource.model.SortType;

@Data
@Accessors(chain = true)
public class BaiduNetDiskWebQueryShareFileParams {

    private String shareId;

    private String shareUk;

    private KeyValue verifyCookie;

    private String dir;

    private QueryShareOrderType orderType;

    private SortType sortType = SortType.DESC;

    private Integer pageNo;

    private Integer pageSize;


}
