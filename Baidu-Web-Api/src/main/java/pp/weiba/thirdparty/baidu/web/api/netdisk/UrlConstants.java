package pp.weiba.thirdparty.baidu.web.api.netdisk;

import java.util.regex.Pattern;

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
    public static final String POST_UPLOAD_FILE = "{}/rest/2.0/pcs/superfile2?method=upload&app_id=250528&channel=chunlei&clienttype=0&web=1&uploadsign=0&logid={logid}&dp-logid={dp-logid}&path={}&uploadid={}&partseq={}";

    // PCS API
    public static final String PCS_API_PREFIX = "https://pcs.baidu.com/rest/2.0/pcs";

    // 查询上传文件服务地址
    public static final String GET_QUERY_UPLOAD_FILE_SERVICES = PCS_API_PREFIX + "/file?app_id=250528&method=locateupload";

    // 分片上传成功后完成文件创建
    public static final String POST_COMPLETE_CREATE_FILE = API_PREFIX + "/create?isdir=0&rtype=1&clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}&logid={logid}&bdstoken={bdstoken}";

    // 查询目录下的文件信息
    public static final String GET_QUERY_DIR_CHILD = API_PREFIX + "/list?clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}&dir={}&order={}&desc={}&num={}&page={}";

    // 文件搜索接口
    public static final String GET_FILE_SEARCH = API_PREFIX +
            "/search?dp-logid={dp-logid}&dir={}&key={}&order={}&desc={}&num={}&page={}&recursion=1&clienttype=0&app_id=250528&web=1";

    // 文件分享接口
    public static final String POST_SHARE_FILES = API_DOMAIN + "/share/set?channel=chunlei&clienttype=0&web=1&channel=chunlei&web=1&app_id=250528&clienttype=0&dp-logid={dp-logid}&bdstoken={bdstoken}&logid={logid}";

    // 取消文件分享
    public static final String POST_CANCEL_SHARE_FILES = API_DOMAIN + "/share/cancel?bdstoken={bdstoken}&dp-logid={dp-logid}&channel=chunlei&clienttype=0&app_id=250528&web=1";

    // 获取为分享的详细信息
    public static final String GET_MY_SHARE_DETAIL_BY_ID = API_DOMAIN + "/share/surlinfoinrecord?shareid={}&sign={}&bdstoken={bdstoken}&channel=chunlei&clienttype=0&app_id=250528&web=1&dp-logid={dp-logid}";

    // 获取我分享的文件列表
    public static final String GET_QUERY_MY_SHARE_FILES_URL = API_DOMAIN + "/share/record?num={}&page={}&order={}&desc={}&channel=chunlei&clienttype=0&app_id=250528&dp-logid={dp-logid}&web=1";

    // 获取分享中的文件详细
    public static final String GET_QUERY_SHARE_FILES_URL = API_DOMAIN + "/share/list?channel=chunlei&web=1&app_id=250528&showempty=0&web=1&clienttype=0" +
            "&t={}&dp-logid={dp-logid}&logid={logid}&uk={uk}" +
            "&shareid={}&dir={}&order={}&desc={}&page={}&num={}";

    // 分享验证接口
    public static final String POST_SHARE_FILE_VERIFY_URL = API_DOMAIN + "/share/verify?channel=chunlei&web=1&app_id=250528&clienttype=0&dp-logid={dp-logid}&bdstoken={bdstoken}&logid={logid}&surl={}&t={}";

    // 分享url模板
    public static final String GET_SHARE_FILE_PAGE_TEMPLATE_URL = API_DOMAIN + "/s/{}?from=init&pwd={}";

    // 分享页面 验证bdclndCookie， 正常的话该正则能取到数据
    public static final Pattern SHARE_INIT_PAGE_PATTERN_REGEX = Pattern.compile("(?<=locals\\.mset\\()(.+?)(?=\\);)");

    // 执行文件转存
    public static final String POST_SHARE_FILE_TRANSFER_URL = API_DOMAIN + "/share/transfer?ondup=newcopy&async=1&channel=chunlei&web=1&app_id=250528&clienttype=0&dp-logid={dp-logid}&logid={logid}&bdstoken={bdstoken}&shareid={}&from={}&sekey={}";


    // 获取QR图片url
    public static final String GET_QR_IMAGE = "https://passport.baidu.com/v2/api/getqrcode?lp=pc&qrloginfrom=pc&apiver=v3&tpl=netdisk&gid={}&callback={}&tt={}&logPage={}&_={}";

    // 检测扫码结果
    public static final String GET_CHECK_SCAN_QR_CALLBACK = "https://passport.baidu.com/channel/unicast?tpl=netdisk&_sdkFrom=1&apiver=v3&channel_id={}&gid={}&callback={}&tt={}&_={}";

    // 扫码成功后跳转登录
    public static final String GET_QR_LOGIN = "https://passport.baidu.com/v3/login/main/qrbdusslogin?u=https%253A%252F%252Fpan.baidu.com%252Fdisk%252Fmain%253Ffrom%253DhomeFlow%2523%252Findex&loginVersion=v4&qrcode=1&tpl=netdisk&maskId=&fileId=&apiver=v3&traceid=&alg=v3&rinfo=%7B%22fuid%22%3A%22c8c22e5754889c982a946ac77a7f8d5b%22%7D&callback={}&bduss={}&v={}&tt={}&time={}&sig={}&shaOne={}&elapsed={}";
}
