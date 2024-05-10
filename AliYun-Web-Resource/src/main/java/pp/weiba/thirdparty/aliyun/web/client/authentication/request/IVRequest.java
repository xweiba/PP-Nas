package pp.weiba.thirdparty.aliyun.web.client.authentication.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
* 
*
* @author weiba
* @date 2024/5/10 11:42
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IVRequest {


    /**
     * actionType
     */
    @JSONField(name = "action_type")
    private String actionType;
    /**
     * callbackType
     */
    @JSONField(name = "callback_type")
    private String callbackType;
}
