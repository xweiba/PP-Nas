package pp.weiba.thirdparty.aliyun.web.client.processors;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IProcessor;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 为请求添加默认的请求头
 *
 * @author weiba
 * @date 2024/3/7 16:48
 */
@Log4j2
public class AddDefaultHeaderProcessor implements IProcessor<HttpRequest> {

    public static final Map<String, String> HTTP_JSON_HEADER = new HashMap<String, String>() {
        {
            put("Accept", "application/json, text/plain, */*");
            put("Accept-Encoding", "gzip, deflate, br");
            put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
            put("Content-Type", "application/json");
            put("DNT", "1");
            put("Priority", "u=1, i");
            put("Referer", UrlConstants.HEADER_REFERER);
            put("Sec-ch-ua", UrlConstants.SEC_CH_UA);
            put("Sec-ch-ua-mobile", "?0");
            put("Sec-ch-ua-platform", "\"Windows\"");
            put("Sec-Fetch-Dest", "empty");
            put("Sec-Fetch-Mode", "cors");
            put("Sec-Fetch-Site", "cross-site");
            put("User-Agent", UrlConstants.NAV_USER_AGENT);
            put("X-Canary", "client=web,app=adrive,version=v4.9.0");
        }
    };


    @Override
    public HttpRequest process(HttpRequest request) {
        request.handler(HTTP_JSON_HEADER);
        if (request.getHtmlRequest()) {
            request.addheader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
            request.delHeader("Content-Type");
        }
        return request;
    }
}
