package pp.weiba.thirdparty.aliyun.web.client.authentication.model;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.OpenAccessTokenResponse;

/**
* 
*
* @author weiba
* @date 2024/5/24 10:38
*/
@Log4j2
public class OpenApiAuthenticationInfo {
    
    private String appId;

    private String appSecret;

    private OpenAccessTokenResponse accessToken;
}
