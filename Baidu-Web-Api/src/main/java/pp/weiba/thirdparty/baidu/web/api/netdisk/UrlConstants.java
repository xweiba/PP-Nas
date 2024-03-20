package pp.weiba.thirdparty.baidu.web.api.netdisk;

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

    // Header REFERER
    public static final String HEADER_REFERER = API_DOMAIN + "/disk/main";

    // 账号登出
    public static final String GET_LOGOUT = "https://passport.baidu.com/?logout&u=" + HEADER_REFERER + "?from=homeFlow";

    // 查询网盘用量
    public static final String GET_QUOTA = API_PREFIX + "/quota?clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}";

    // 创建文件夹
    public static final String POST_CREATE_DIR = API_PREFIX + "/create?a=commit&clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}&bdstoken={bdstoken}";

    // 获取页面模板变量
    public static final String GET_TEMPLATE_VARIABLE = API_PREFIX + "/gettemplatevariable?clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}&fields=[{fields}]";

    // 获取用户登录状态
    public static final String GET_LOGIN_STATUS = API_PREFIX + "/loginStatus?clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}";

    // 重复上传检测
    public static final String POST_UPLOAD_FILE_DUPLICATE_CHECK = API_PREFIX + "/rapidupload?bdstoken={bdstoken}";

    // 文件预创建
    public static final String POST_PRE_UPLOAD_FILE = API_PREFIX + "/precreate?clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}";

    // 文件上传
    public static final String POST_UPLOAD_FILE = "{}/rest/2.0/pcs/superfile2?method=upload&app_id=250528&channel=chunlei&clienttype=0&web=1&uploadsign=0&logid={logid}&path={}&uploadid={}&partseq={}";

    // PCS API
    public static final String PCS_API_PREFIX = "https://pcs.baidu.com/rest/2.0/pcs";

    // 查询上传文件服务地址
    public static final String GET_QUERY_UPLOAD_FILE_SERVICES = PCS_API_PREFIX + "/file?app_id=250528&method=locateupload";

    // 分片上传成功后完成文件创建
    public static final String POST_COMPLETE_CREATE_FILE = API_PREFIX + "/create?isdir=0&rtype=1&clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}&logid={logid}&bdstoken={bdstoken}";


}
