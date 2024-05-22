package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.AliYunClientConstants;

/*
{"drive_id":"18654654","limit":20,"order_by":"name","order_direction":"DESC"}
* */

/**
* 获取回收站资源信息
*
* @author weiba
* @date 2024/5/10 14:23
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetRecycleRsequest {


    /**
     * driveId
     */
    @JSONField(name = "drive_id")
    private String driveId = AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG;
    /**
     * limit
     */
    @JSONField(name = "limit")
    private Integer limit = 20;
    /**
     * orderBy
     */
    @JSONField(name = "order_by")
    private String orderBy = "name";
    /**
     * orderDirection
     */
    @JSONField(name = "order_direction")
    private String orderDirection = "DESC";
}
