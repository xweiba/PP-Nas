package pp.weiba.thirdparty.baidu.web.resource.processors;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.HttpRequest;
import pp.weiba.framework.core.convert.IDataProcessor;
import pp.weiba.thirdparty.baidu.web.api.netdisk.UrlConstants;

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
            put("Sec-ch-ua", "\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Microsoft Edge\";v=\"122\"");
            put("Sec-ch-ua-mobile", "?0");
            put("Sec-ch-ua-platform", "\"Windows\"");
            put("Sec-Fetch-Dest", "empty");
            put("Sec-Fetch-Mode", "cors");
            put("Sec-Fetch-Site", "same-origin");
            put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0");
            put("X-Requested-With", "XMLHttpRequest");
        }
    };

    @Override
    public HttpRequest process(HttpRequest request) {
        return request.handler(HTTP_HEADER);
    }
}
