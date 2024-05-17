package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* 获取分享认证后的Token
*
* @author weiba
* @date 2024/5/17 9:38
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetShareTokenResponse {


    /**
     * expireTime
     */
    @JSONField(name = "expire_time")
    private String expireTime;
    /**
     * expiresIn
     */
    @JSONField(name = "expires_in")
    private Integer expiresIn;
    /**
     * shareToken
     */
    @JSONField(name = "share_token")
    private String shareToken;
}
