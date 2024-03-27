package pp.weiba.thirdparty.baidu.web.api.netdisk;

import lombok.extern.log4j.Log4j2;

/**
 * 百度网盘开放平台接口，该接口需要带上access_token
 *
 * @author weiba
 * @date 2024/3/25 13:08
 */
@Log4j2
public class XpanUrlConstants {


    // XPAN API 前缀
    public static final String XPAN_API_PREFIX = UrlConstants.API_DOMAIN + "/rest/2.0/xpan";

    //
    public static final String POST_AUTH_API = "https://openapi.baidu.com/oauth/2.0/getbdstoken";


    // 百度手机助手id
    public static final String CLIENT_ID = "IlLqBbU3GjQ0t46TRwFateTprHWl39zF";

    // 获取 access_token, client_id 为百度开放平台中申请的应用的AppKey， 个人账号已经无法申请了,
    // https://openapi.baidu.com/oauth/2.0/authorize?client_id=IlLqBbU3GjQ0t46TRwFateTprHWl39zF&response_type=token&redirect_uri=oob&scope=basic,netdisk
    public static final String GET_ACCESS_TOKEN = "https://openapi.baidu.com/oauth/2.0/authorize?client_id=" + CLIENT_ID + "&response_type=token&redirect_uri=oob&scope=basic,netdisk";

    // 获取文件信息
    public static final String POST_FILE_MULTIMEDIA = XPAN_API_PREFIX + "/multimedia?access_token={xpan_access_token}";

}
