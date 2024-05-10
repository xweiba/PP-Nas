package pp.weiba.thirdparty.aliyun.web.client.authentication.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/*
{"deviceId":"81a4d8593ee84efc8eff85c16968d740","token":"CN-SPLIT-AQigvcWXrUAQ2AQiDWhhdmFuYV9hcHBfaXYyAQE409HHhPYxQAFKECHZgp0WOcTC2CFK_UHwL_gqXNfC_o7kPKLYXcJ85iNv_oNQWQ"}
* */
/**
* 
*
* @author weiba
* @date 2024/5/10 12:55
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceOfflineRequest {


    /**
     * deviceId
     */
    @JSONField(name = "deviceId")
    private String deviceId;
    /**
     * token
     */
    @JSONField(name = "token")
    private String token;
}
