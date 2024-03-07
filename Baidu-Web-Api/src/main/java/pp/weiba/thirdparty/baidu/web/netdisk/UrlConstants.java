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
}
