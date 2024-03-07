package pp.weiba.thirdparty.baidu.web.netdisk.processors;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.HttpRequest;
import pp.weiba.thirdparty.baidu.web.client.IDataProcessor;
import pp.weiba.thirdparty.baidu.web.netdisk.UrlConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 为请求添加默认的请求头
 *
 * @author weiba
 * @date 2024/3/7 16:48
 */
@Log4j2
public class AddDefaultHeaderProcessor implements IDataProcessor<HttpRequest> {

    public static final Map<String, String> HTTP_HEADER = new HashMap<String, String>() {
        {
            put("Accept", "application/json, text/plain, */*");
            put("Accept-Encoding", "gzip, deflate, br");
            put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
            put("Cache-Control", "no-cache");
            put("Connection", "keep-alive");
            put("DNT", "1");
            put("Referer", UrlConstants.HEADER_REFERER);
            put("Pragma", "no-cache");
            put("Sec-ch-ua", "\"Microsoft Edge\";v=\"105\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"105\"");
            put("Sec-ch-ua-mobile", "?0");
            put("Sec-ch-ua-platform", "\"Windows\"");
            put("Sec-Fetch-Dest", "empty");
            put("Sec-Fetch-Mode", "cors");
            put("Sec-Fetch-Site", "same-origin");
            put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36 Edg/105.0.1343.42");
            put("X-Requested-With", "XMLHttpRequest");
        }
    };

    @Override
    public HttpRequest process(HttpRequest request) {
        return request.handler(HTTP_HEADER);
    }
}
