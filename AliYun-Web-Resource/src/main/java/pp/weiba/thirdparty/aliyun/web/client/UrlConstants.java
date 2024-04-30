package pp.weiba.thirdparty.aliyun.web.client;

/**
 * AliYun 盘接口Url 常量
 *
 * @author weiba
 * @date 2024/4/29 16:51
 */
public class UrlConstants {

    public static final String API_DOMAIN = "https://api.aliyundrive.com";

    public static final String HEADER_REFERER = "https://www.alipan.com/";

    /* 获取个人信息 */
    public static final String POST_PERSONAL_INFO_URL = API_DOMAIN + "/v2/databox/get_personal_info";

    /* 新增文件夹 */
    public static final String POST_ADD_FOLDER_URL = API_DOMAIN + "/adrive/v2/file/createWithFolders";
}
