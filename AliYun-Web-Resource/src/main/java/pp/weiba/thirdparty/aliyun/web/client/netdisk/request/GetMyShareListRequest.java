package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.core.AliYunClientConstants;

/**
* 获取我的分享列表
*
* @author weiba
* @date 2024/5/15 17:33
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetMyShareListRequest {

    /**
     * creator
     */
    @JSONField(name = "creator")
    private String creator = AliYunClientConstants.REQUEST_PARAM_RESOURCE_USER_ID_TAG;
    /**
     * includeCanceled
     */
    @JSONField(name = "include_canceled")
    private boolean includeCanceled;
    /**
     * category
     */
    @JSONField(name = "category")
    private String category = "file,album";
    /**
     * limit
     */
    @JSONField(name = "limit")
    private Integer limit = 20;
    /**
     * orderBy
     */
    @JSONField(name = "order_by")
    private String orderBy = "browse_count";
    /**
     * orderDirection
     */
    @JSONField(name = "order_direction")
    private String orderDirection = "DESC";
}
