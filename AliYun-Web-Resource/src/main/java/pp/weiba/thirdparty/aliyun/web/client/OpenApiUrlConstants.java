package pp.weiba.thirdparty.aliyun.web.client;
import lombok.extern.log4j.Log4j2;

/**
* 
*
* @author weiba
* @date 2024/5/24 13:36
*/
@Log4j2
public class OpenApiUrlConstants {

    /* OpenAPI Domain*/
    public static final String OPEN_API_DOMAIN = "https://open.aliyundrive.com";

    /* OpenAPI 获取授权code */
    public static final String GET_POST_OPEN_OAUTH_AUTHORIZE_BASE_URL = OPEN_API_DOMAIN + "/oauth/users/authorize";

    /* OpenAPI 获取授权code */
    public static final String GET_POST_OPEN_OAUTH_AUTHORIZE_URL = GET_POST_OPEN_OAUTH_AUTHORIZE_BASE_URL + "?client_id={clientId}&redirect_uri={redirectUri}&scope={scope}&code_challenge={codeChallenge}&code_challenge_method={codeChallengeMethod}";

    /* OpenAPI 获取 AccessToken  */
    public static final String POST_OPEN_GET_ACCESS_TOKEN_URL = OPEN_API_DOMAIN + "/oauth/access_token";

    /* 获取文件下载地址 */
    public static final String POST_GET_DOWNLOAD_URL = OPEN_API_DOMAIN + "/adrive/v1.0/openFile/getDownloadUrl";


    
}
