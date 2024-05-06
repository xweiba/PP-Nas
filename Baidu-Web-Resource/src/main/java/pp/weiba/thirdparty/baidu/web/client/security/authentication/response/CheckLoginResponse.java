package pp.weiba.thirdparty.baidu.web.client.security.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 扫码检测
 * {
 * "errno": 0,
 * "channel_id": "08dcd61b568fbd38641a8e31fb4b8eb6",
 * "channel_v": "{\"status\":0,\"v\":\"d9cecd871b3afcdf327bc08b50edd0cf\",\"u\":\"\"}"
 * }
 *
 * @author weiba
 * @date 2024/3/26 13:17
 */
@Data
@Accessors(chain = true)
public class CheckLoginResponse {

    @JSONField(name = "errno")
    private Integer errno;
    @JSONField(name = "channel_id")
    private String channelId;
    @JSONField(name = "channel_v")
    private Channel channelV;

    @Data
    @Accessors(chain = true)
    public static class Channel {
        @JSONField(name = "status")
        private Integer status;
        /* bduss */
        @JSONField(name = "v")
        private String v;
        @JSONField(name = "u")
        private String u;
    }
}
