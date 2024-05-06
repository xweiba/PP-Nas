package pp.weiba.thirdparty.aliyun.web.client;

/**
 * AliYun 盘接口Url 常量
 *
 * @author weiba
 * @date 2024/4/29 16:51
 */
public class UrlConstants {

    public static final String API_DOMAIN = "https://api.aliyundrive.com";

    public static final String USER_API_DOMAIN = "https://user.aliyundrive.com";

    public static final String HEADER_REFERER = "https://www.alipan.com/";

    public static final String HTML_LOGIN_URL = "https://passport.alipan.com/mini_login.htm?lang=zh_cn&appName=aliyun_drive&appEntrance=web_default&styleType=auto&bizParams=&notLoadSsoView=false&notKeepLogin=false&isMobile=false&ad__pass__q__rememberLogin=true&ad__pass__q__rememberLoginDefaultValue=true&ad__pass__q__forgotPassword=true&ad__pass__q__licenseMargin=true&ad__pass__q__loginType=normal&hidePhoneCode=true&rnd=0.";

    /* 创建会话信息，将公钥设置至阿里云服务器 */
    public static final String POST_CREATE_SESSION_URL = API_DOMAIN + "/users/v1/users/device/create_session";

    /* token 刷新*/
    public static final String POST_TOKEN_REFRESH_URL = API_DOMAIN + "/token/refresh";

    /* 获取个人信息 */
    public static final String POST_PERSONAL_INFO_URL = API_DOMAIN + "/v2/databox/get_personal_info";

    /* 获取用户信息 */
    public static final String POST_GET_USER_INFO_URL = USER_API_DOMAIN + "/v2/user/get";

    /* 获取登录的环境信息 */
    public static final String POST_GET_SBOX_INFO_URL = API_DOMAIN + "/v2/sbox/get";

    /* 新增文件夹 */
    public static final String POST_ADD_FOLDER_URL = API_DOMAIN + "/adrive/v2/file/createWithFolders";

    /* 获取文件信息 */
    public static final String POST_GET_FILE_INFO_URL = API_DOMAIN + "/v2/file/get";
}
