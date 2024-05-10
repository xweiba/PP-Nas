package pp.weiba.thirdparty.aliyun.web.client.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 设备列表
*
* @author weiba
* @date 2024/5/10 11:32
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceListResponse {

    /**
     * result
     */
    @JSONField(name = "result")
    private ResultResponse result;
    /**
     * success
     */
    @JSONField(name = "success")
    private Boolean success;
    /**
     * code
     */
    @JSONField(name = "code")
    private Object code;
    /**
     * message
     */
    @JSONField(name = "message")
    private Object message;

    /**
     * ResultResponse
     */
    @NoArgsConstructor
    @Data
    public static class ResultResponse {
        /**
         * maxDeviceCount
         */
        @JSONField(name = "maxDeviceCount")
        private Integer maxDeviceCount;
        /**
         * showUpgradeVip
         */
        @JSONField(name = "showUpgradeVip")
        private Boolean showUpgradeVip;
        /**
         * devices
         */
        @JSONField(name = "devices")
        private List<DevicesResponse> devices;

        /**
         * DevicesResponse
         */
        @NoArgsConstructor
        @Data
        public static class DevicesResponse {
            /**
             * deviceId
             */
            @JSONField(name = "deviceId")
            private String deviceId;
            /**
             * deviceName
             */
            @JSONField(name = "deviceName")
            private String deviceName;
            /**
             * modelName
             */
            @JSONField(name = "modelName")
            private String modelName;
            /**
             * city
             */
            @JSONField(name = "city")
            private String city;
            /**
             * loginTime
             */
            @JSONField(name = "loginTime")
            private String loginTime;
        }
    }
}
