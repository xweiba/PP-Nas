package pp.weiba.thirdparty.aliyun.web.client;

/**
 * AliYun 盘接口Url 常量
 *
 * @author weiba
 * @date 2024/4/29 16:51
 */
public class UrlConstants {

    public static final String DEVICE_NAME = "Edge浏览器_Web";

    public static final String API_DOMAIN = "https://api.aliyundrive.com";

    public static final String USER_API_DOMAIN = "https://user.aliyundrive.com";

    public static final String HEADER_REFERER = "https://www.alipan.com/";

    public static final String PASSPORT_DOMAIN = "https://passport.alipan.com";

    public static final String AUTH_ALIPAN_DOMAIN = "https://auth.alipan.com/";

    public static final String SIGNIN_ALIPAN_DOMAIN = "https://member.aliyundrive.com";

    public static final String NAV_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 Edg/124.0.0.0";
    public static final String SEC_CH_UA = "\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Microsoft Edge\";v=\"122\"";

    public static final String HTML_INIT_LOGIN_MAIN_URL = PASSPORT_DOMAIN + "/mini_login.htm?lang=zh_cn&appName=aliyun_drive&appEntrance=web_default&styleType=auto&bizParams=&notLoadSsoView=false&notKeepLogin=false&isMobile=false&ad__pass__q__rememberLogin=true&ad__pass__q__rememberLoginDefaultValue=true&ad__pass__q__forgotPassword=true&ad__pass__q__licenseMargin=true&ad__pass__q__loginType=normal&hidePhoneCode=true&rnd=0.";

    public static final String HTML_INIT_AUTH_ALIPAN_URL = AUTH_ALIPAN_DOMAIN + "v2/oauth/authorize?client_id=25dzX3vbYqktVxyX&redirect_uri=https%3A%2F%2Fwww.alipan.com%2Fsign%2Fcallback&response_type=code&login_type=custom&state=%7B%22origin%22%3A%22https%3A%2F%2Fwww.alipan.com%22%7D";

    public static final String POST_TOKEN_LONGIN_URL = AUTH_ALIPAN_DOMAIN + "v2/oauth/token_login";

    /* 使用code 获取 token */
    public static final String POST_TOKEN_GET_BY_CODE_URL = API_DOMAIN + "/token/get";

    /* token 刷新*/
    public static final String POST_TOKEN_REFRESH_URL = API_DOMAIN + "/token/refresh";

    /* App 的刷新接口 */
    public static final String POST_APP_TOKEN_REFRESH_URL = AUTH_ALIPAN_DOMAIN + "v2/account/token";

    /* 创建会话信息，将公钥设置至阿里云服务器 */
    public static final String POST_CREATE_SESSION_URL = API_DOMAIN + "/users/v1/users/device/create_session";

    /* 刷新 X-Signature */
    public static final String POST_NEW_SESSION_URL = API_DOMAIN + "/users/v1/users/device/renew_session";

    /* 获取设备列表，最多十台 */
    public static final String POST_DEVICE_LIST_URL = API_DOMAIN + "/users/v2/users/device_list";

    /* 获取设备登出时的token */
    public static final String POST_GET_IV_URL = API_DOMAIN + "/v2/user/get_iv_url";

    /* 指定设备退出登录 */
    public static final String POST_DEVICE_OFFLINE_URL = API_DOMAIN + "/users/v1/users/device_offline";

    /* 获取个人信息 */
    public static final String POST_PERSONAL_INFO_URL = API_DOMAIN + "/v2/databox/get_personal_info";

    /* 获取用户信息 */
    public static final String POST_GET_USER_INFO_URL = USER_API_DOMAIN + "/v2/user/get";

    /* 获取登录的环境信息 */
    public static final String POST_GET_SBOX_INFO_URL = API_DOMAIN + "/v2/sbox/get";

    /* 今日签到 */
    public static final String POST_SIGN_IN_INFO_URL = SIGNIN_ALIPAN_DOMAIN + "/v1/activity/sign_in_goods";

    /* 获取今日签到及奖励领取状态 */
    public static final String POST_SIGN_IN_STATUS_INFO_URL = SIGNIN_ALIPAN_DOMAIN + "/v2/activity/sign_in_info";

    /* 获取所有签到及其奖励领取状态 */
    public static final String POST_SIGN_IN_LIST_STATUS_INFO_URL = SIGNIN_ALIPAN_DOMAIN + "/v2/activity/sign_in_list";

    /* 签到 */
    public static final String POST_SIGN_IN_REWARD_URL = SIGNIN_ALIPAN_DOMAIN + "/v1/activity/sign_in_reward";

    /* 新增文件夹 */
    public static final String POST_ADD_FOLDER_URL = API_DOMAIN + "/adrive/v2/file/createWithFolders";

    /* 获取文件信息 */
    public static final String POST_GET_FILE_INFO_URL = API_DOMAIN + "/v2/file/get";

    /* 资源批量处理 */
    public static final String POST_RESOURCE_BATCH_URL = API_DOMAIN + "/adrive/v4/batch";

    /* 资源批处理 文件删除&web回收站删除文件时调用此接口 */
    public static final String RESOURCE_BATCH_FILE_DELETE_URL = "/file/delete";

    /* 资源批处理 文件移动 */
    public static final String RESOURCE_BATCH_FILE_MOVE_URL = "/file/move";

    /* 资源批处理 回收站*/
    public static final String RESOURCE_BATCH_FILE_TO_RECYCLE_URL = "/recyclebin/trash";

    /* 资源批处理 回收站还原*/
    public static final String RESOURCE_BATCH_RECYCLE_RESTORE_URL = "/recyclebin/restore";

    /* 批处理 异步任务查询 */
    public static final String RESOURCE_BATCH_GET_ASYNC_TASK_URL = "/async_task/get";

    /* 取消分享 */
    public static final String RESOURCE_BATCH_CANCEL_SHARE_URL = "/share_link/cancel";

    /* 回收站列表查询 */
    public static final String POST_GET_RECYCLE_LIST_URL =  API_DOMAIN + "/adrive/v2/recyclebin/list";

    /* 重命名 */
    public static final String POST_RENAME_URL = API_DOMAIN + "/v3/file/update";

    /* 获取资源下载地址 */
    public static final String POST_GET_FILE_DOWNLOAD_URL = API_DOMAIN + "/v2/file/get_download_url";

    /* 资源搜索 */
    public static final String POST_RESOURCE_SEARCH_URL = API_DOMAIN + "/adrive/v3/file/search";

    /* 将文件在两个盘中同步 */
    public static final String POST_RESOURCE_COPY_TO_RESOURCE_URL = API_DOMAIN + "/adrive/v2/file/crossDriveCopy";

    /* 创建分享 */
    public static final String POST_RESOURCE_CREATE_SHARE_URL = API_DOMAIN + "/adrive/v2/share_link/create";

    /* 查询我的分享 */
    public static final String POST_RESOURCE_GET_SHARE_URL = API_DOMAIN + "/adrive/v3/share_link/list";

    /* 获取分享的认证token*/
    public static final String POST_GET_SHARE_TOKEN_URL = API_DOMAIN + "/v2/share_link/get_share_token";

    /* 获取分享资源的文件列表 */
    public static final String POST_GET_SHARE_FILE_LIST_URL = API_DOMAIN + "/adrive/v2/file/list_by_share";

    /* 获取分享资源的文件详情 */
    public static final String POST_GET_SHARE_FILE_DETAIL_URL = API_DOMAIN + "/adrive/v2/file/get_by_share";

    /* 获取子目录信息 */
    public static final String POST_GET_FILE_CHILD_DIR_URL = API_DOMAIN + "/adrive/v2/file/listFolder";

    /* 获取资源简要信息 */
    public static final String POST_GET_FILE_SIMPLE_URL = API_DOMAIN + "/adrive/v2/file/simpleGet";

    /* 保存分享文件 */
    public static final String RESOURCE_BATCH_SAVE_SHARE_FILE_URL = "/file/copy";
}
