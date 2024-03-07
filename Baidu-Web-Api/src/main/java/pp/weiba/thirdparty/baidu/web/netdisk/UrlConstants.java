package pp.weiba.thirdparty.baidu.web.netdisk;

/**
 * 接口Url信息
 *
 * @author weiba
 * @date 2024/3/7 13:34
 */
public class UrlConstants {

    // API 域名
    public static final String API_DOMAIN = "https://pan.baidu.com";

    // API 前缀
    public static final String API_PREFIX = API_DOMAIN + "/api";

    // 查询网盘用量
    public static final String GET_QUOTA = API_PREFIX + "/quota?clienttype=0&app_id=250528&web=1&dp-logid={}";

    // 创建文件夹
    public static final String POST_CREATE_DIR = API_PREFIX + "/create?a=commit&clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}&bdstoken={bdstoken}";

    // Header REFERER
    public static final String HEADER_REFERER = API_DOMAIN + "/disk/main";

    // 获取页面模板变量
    public static final String GET_TEMPLATE_VARIABLE = API_PREFIX + "/gettemplatevariable?clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}&fields=[{fields}]";

    // 获取用户登录状态
    public static final String GET_LOGIN_STATUS = API_PREFIX + "/loginStatus?clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}";

}
