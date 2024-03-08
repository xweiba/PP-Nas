package pp.weiba.thirdparty.baidu.web.api.client;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * Http请求信息
 *
 * @author weiba
 * @date 2024/3/7 9:57
 */
@Log4j2
@Accessors(chain = true)
@Getter
@Setter
public class HttpRequest {

    private String url;

    // url 是否需要Encode
    private boolean disableUrlEncoding = Boolean.FALSE;

    private Method method = Method.GET;

    private Map<String, Object> requestParams;

    private String requestBody;

    private String contentType;

    private Map<String, String> headerMap = new HashMap<>();

    private int timeout = 60000;

    // 设置是否打开重定向，如果打开默认重定向次数为2
    private boolean followRedirect;

    // 重定向最大次数
    private int maxRedirectCount = 2;

    // 重定向是否携带cookie 默认false
    private boolean followRedirectsCookie;

    // 构建参数, 不参与请求
    private Map<String, Object> buildParams;

    public static HttpRequest urlFormatBuilder(CharSequence urlTemplate, Map<String, Object> buildParams) {
        return urlFormatBuilder(Method.GET, urlTemplate, buildParams, null);
    }

    public static HttpRequest urlFormatBuilder(CharSequence urlTemplate, Map<String, Object> buildParams, Map<?, ?> urlParams) {
        return urlFormatBuilder(Method.GET, urlTemplate, buildParams, urlParams);
    }

    public static HttpRequest urlFormatBuilder(Method method, CharSequence urlTemplate, Map<String, Object> buildParams) {
        return urlFormatBuilder(method, urlTemplate, buildParams, null);
    }

    public static HttpRequest urlFormatBuilder(Method method, CharSequence urlTemplate, Map<String, Object> buildParams, Map<?, ?> urlParams) {
        String url = StrUtil.format(urlTemplate, urlParams);
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(url);
        httpRequest.setMethod(method);
        httpRequest.setBuildParams(buildParams);
        return httpRequest;
    }

    public HttpRequest addRequestParams(String key, Object value) {
        this.requestParams = addMapValue(this.requestParams, key, value);
        return this;
    }

    public HttpRequest addheader(String key, String value) {
        this.headerMap = addMapValue(this.headerMap, key, value);
        return this;
    }

    public HttpRequest addBuildParams(String key, String value) {
        this.buildParams = addMapValue(this.buildParams, key, value);
        return this;
    }

    public HttpRequest requestParams(Map<String, Object> hashMap) {
        this.requestParams = hashMap;
        return this;
    }

    public HttpRequest handler(Map<String, String> headerMap) {
        this.headerMap = headerMap;
        return this;
    }

    public <T> Map<String, T> addMapValue(Map<String, T> map, String key, T value) {
        map = initMap(map);
        map.put(key, value);
        return map;
    }

    public <T> Map<String, T> initMap(Map<String, T> map) {
        if (map == null) map = new HashMap<>();
        return map;
    }
}
