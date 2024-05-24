package pp.weiba.thirdparty.aliyun.web.client.authentication.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.OpenAccessTokenResponse;

/**
* 
*
* @author weiba
* @date 2024/5/24 10:38
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenApiAuthenticationInfo {

    public OpenApiAuthenticationInfo(String appId, OpenAccessTokenResponse accessToken) {
        this.appId = appId;
        this.accessToken = accessToken;
    }

    private String appId;

    private String appSecret;

    private OpenAccessTokenResponse accessToken;
}
