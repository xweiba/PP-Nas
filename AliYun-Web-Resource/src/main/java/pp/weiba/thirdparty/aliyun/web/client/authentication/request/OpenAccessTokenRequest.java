package pp.weiba.thirdparty.aliyun.web.client.authentication.request;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import lombok.experimental.Accessors;

/**
* 开放平台获取AccessToken
*
* @author weiba
* @date 2024/5/23 17:03
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OpenAccessTokenRequest {

    public OpenAccessTokenRequest(String clientId, String code, String codeVerifier) {
        this.clientId = clientId;
        this.code = code;
        this.codeVerifier = codeVerifier;
    }

    /* 创建应用时分配的 appId */
    @JSONField(name = "client_id")
    private String clientId;

    /* 创建应用时分配的 appId, PKCE模式，不需要传 */
    @JSONField(name = "client_secret")
    private String clientSecret;

    /* 身份类型 authorization_code 或 refresh_token*/
    @JSONField(name = "grant_type")
    private OpenAccessTokenGrantType grantType = OpenAccessTokenGrantType.AUTHORIZATION_CODE;

    /* 授权码 */
    @JSONField(name = "code")
    private String code;

    /* 刷新 token，单次请求有效。 */
    @JSONField(name = "refresh_token")
    private String refreshToken;

    /* 拼接 PKCE 授权登录连接时生成的随机字符串值也就是 code_challenge 原始值，不是其摘要值。 */
    @JSONField(name = "code_verifier")
    private String codeVerifier;
}
