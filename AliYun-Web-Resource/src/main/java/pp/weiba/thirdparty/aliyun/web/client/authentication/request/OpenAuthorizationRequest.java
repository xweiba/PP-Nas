package pp.weiba.thirdparty.aliyun.web.client.authentication.request;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import lombok.experimental.Accessors;
import sun.net.www.content.text.plain;

/**
 * @author weiba
 * @date 2024/5/23 16:15
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OpenAuthorizationRequest {

    public OpenAuthorizationRequest(String clientId, OpenAuthorizationPkceType pkceType) {
        this.clientId = clientId;
        this.codeChallengeMethod = pkceType;
        this.codeChallengeStr = RandomUtil.randomString(RandomUtil.randomInt(43, 128));
        if (OpenAuthorizationPkceType.PLAIN == pkceType) {
            this.codeChallenge = this.codeChallengeStr;
        } else if (OpenAuthorizationPkceType.S256 == pkceType) {
            this.codeChallenge = Base64.encode(DigestUtil.sha256(this.codeChallengeStr));
        } else {
            throw new RuntimeException("pkceType 类型不支持");
        }
    }

    /* 创建应用时分配的 appId */
    @JSONField(name = "client_id")
    private String clientId;

    /*
    *   授权后要回调的 URI，即接收 Authorization Code的 URI。请使用 urlEncode 对链接进行处理。
        云盘会把授权后的 code 放到 redirect_uri URL参数上。示例
        redirect_uri&code={code}
     * */
    @JSONField(name = "redirect_uri")
    private String redirectUri = "oob";

    /* 申请的授权范围，多个用逗号分隔。示例 user:base,file:all:read*/
    @JSONField(name = "scope")
    private String scope = "user:base,file:all:read,file:all:write";

    /* 仅支持 code*/
    @JSONField(name = "response_type")
    private String responseType;

    /**
     * 用于保持请求和回调的状态，授权服务器在回调时（重定向用户浏览器到“redirect_uri”时），会在Query Parameter中原样回传该参数。OAuth2.0标准协议推荐，利用state参数来防止CSRF攻击。
     */
    @JSONField(name = "response_type")
    private String state;

    /**
     * h5 下 true 强制用户登录，默认 false
     */
    @JSONField(name = "state")
    private Boolean relogin;

    /*
     * 一个长度 43 - 128 的随机字符串。, PKCE 无服务器模式使用，30天有效期的 AccessToken，不支持刷新，需要开启 公开客户端接入
     * */
    @JSONField(name = "code_challenge")
    private String codeChallenge;

    /* codeChallengeStr 原始值 */
    @JSONField(serialize=false)
    private String codeChallengeStr;

    /**
     * 如果 code_challenge_method 是 plain，可以不用计算，直接明文传递一个长度大于等于 43 的字符串。
     * 推荐：如果 code_challenge_method 是 S256，首先生成一个字符串 S，计算 SHA256(S) 得到一个二进制 Buffer，然后将其转为 base64 编码。伪代码：
     * BASE64URL-ENCODE(SHA256(ASCII(S)))
     */
    @JSONField(name = "code_challenge_method")
    private OpenAuthorizationPkceType codeChallengeMethod;

    /**
     * 默认 web
     * */
    @JSONField(name = "source")
    private String source;

}
