package pp.weiba.framework.net.client;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.hash.Hash;
import lombok.extern.log4j.Log4j2;
import sun.management.Agent;

import java.util.HashMap;
import java.util.Map;

/**
* 客户端常量
*
* @author weiba
* @date 2024/5/21 16:42
*/
@Log4j2
public class ClientConstants {

    public static final String REQUEST_PARAM_NEW_SESSION_TAG = "new_session";

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0";

    public static final String SEC_CH_UA = "\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Microsoft Edge\";v=\"122\"";

    public static final String ACCEPT_ENCODING = "gzip, deflate, br";

    public static final String ACCEPT_LANGUAGE = "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6";

    public static final String JSON_ACCEPT = "application/json, text/plain, */*";

    public static final String HTML_ACCEPT = "text/html,application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";


    public static final Map<String, String> HTTP_DEFAULT_HEADER = new HashMap<String, String>() {
        {
            put("Accept-Encoding", ACCEPT_ENCODING);
            put("Accept-Language", ACCEPT_LANGUAGE);
            put("DNT", "1");
            put("Sec-ch-ua", ClientConstants.SEC_CH_UA);
            put("Sec-ch-ua-mobile", "?0");
            put("Sec-ch-ua-platform", "\"Windows\"");
            put("Sec-Fetch-Dest", "empty");
            put("Sec-Fetch-Mode", "cors");
            put("Sec-Fetch-Site", "same-origin");
            put("User-Agent", ClientConstants.USER_AGENT);
        }
    };

    public static final Map<String, String> HTTP_JSON_HEADER = BeanCopier.create(HTTP_DEFAULT_HEADER, new HashMap<String, String>() {
        {
            put("Accept", JSON_ACCEPT);
            put("Cache-Control", "no-cache");
            put("Connection", "keep-alive");
            put("Pragma", "no-cache");
            put("X-Requested-With", "XMLHttpRequest");
        }
    }, CopyOptions.create().setIgnoreCase(true).setIgnoreNullValue(true)).copy();


    public static final Map<String, String> HTTP_HTML_HEADER = BeanCopier.create(HTTP_DEFAULT_HEADER, new HashMap<String, String>() {
        {
            put("Accept", HTML_ACCEPT);
            put("Connection", "keep-alive");
        }
    }, CopyOptions.create().setIgnoreCase(true).setIgnoreNullValue(true)).copy();

}
