package pp.weiba.thirdparty.baidu.web.client.processors;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IProcessor;
import pp.weiba.framework.net.client.ClientConstants;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.thirdparty.baidu.web.client.netdisk.UrlConstants;

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

    public static final Map<String, String> HTTP_JSON_HEADER = BeanCopier.create(ClientConstants.HTTP_JSON_HEADER, new HashMap<String, String>() {
        {
            put("Referer", UrlConstants.HEADER_REFERER);
            put("X-Requested-With", "XMLHttpRequest");
        }
    }, CopyOptions.create().setIgnoreCase(true).setIgnoreNullValue(true)).copy();

    public static final Map<String, String> HTTP_HTML_HEADER = BeanCopier.create(ClientConstants.HTTP_HTML_HEADER, new HashMap<String, String>() {
        {
            put("Referer", UrlConstants.HEADER_REFERER);
        }
    }, CopyOptions.create().setIgnoreCase(true).setIgnoreNullValue(true)).copy();

//    public static final Map<String, String> HTTP_JSON_HEADER = new HashMap<String, String>() {
//        {
//            put("Accept", "application/json, text/plain, */*");
//            put("Accept-Encoding", "gzip, deflate, br");
//            put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
//            put("Cache-Control", "no-cache");
//            put("Connection", "keep-alive");
//            put("DNT", "1");
//            put("Referer", UrlConstants.HEADER_REFERER);
//            put("Pragma", "no-cache");
//            put("Sec-ch-ua", ClientConstants.SEC_CH_UA);
//            put("Sec-ch-ua-mobile", "?0");
//            put("Sec-ch-ua-platform", "\"Windows\"");
//            put("Sec-Fetch-Dest", "empty");
//            put("Sec-Fetch-Mode", "cors");
//            put("Sec-Fetch-Site", "same-origin");
//            put("User-Agent", ClientConstants.USER_AGENT);
//            put("X-Requested-With", "XMLHttpRequest");
//        }
//    };

//    public static final Map<String, String> HTTP_HTML_HEADER = new HashMap<String, String>() {
//        {
//            put("Accept", "text/html,application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            put("Accept-Encoding", "gzip, deflate, br");
//            put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
//            put("Connection", "keep-alive");
//            put("DNT", "1");
//            put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0");
//        }
//    };

    @Override
    public HttpRequest process(HttpRequest request) {
        if (request.getHtmlRequest()) {
            return request.handler(HTTP_HTML_HEADER);
        }
        return request.handler(HTTP_JSON_HEADER);
    }
}
