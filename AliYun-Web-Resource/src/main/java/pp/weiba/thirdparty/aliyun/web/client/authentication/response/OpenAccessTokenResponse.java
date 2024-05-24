package pp.weiba.thirdparty.aliyun.web.client.authentication.response;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* 开放平台返回的AccessToken
*
* @author weiba
* @date 2024/5/23 17:03
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OpenAccessTokenResponse {

    /* Bearer */
    @JSONField(name = "token_type")
    private String tokenType;

    /* 用来获取用户信息的 access_token。 */
    @JSONField(name = "access_token")
    private String accessToken;

    /* 用来获取用户信息的 refresh_token, PKCE模式下不返回, 单次有效，用来刷新 access_token，90 天有效期。刷新后，返回新的 refresh_token，请保存以便下一次刷新使用。*/
    @JSONField(name = "refresh_token")
    private String refreshToken;

    /* access_token的过期时间，单位秒。 PKCE模式30天有效期*/
    @JSONField(name = "expires_in")
    private Long expiresIn;

    /* 创建时间 */
    private Long createTime = new Date().getTime();
    
}
