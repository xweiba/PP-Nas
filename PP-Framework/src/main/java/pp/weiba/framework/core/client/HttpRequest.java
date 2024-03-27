package pp.weiba.framework.core.client;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.HttpCookieDeserializer;

import java.net.HttpCookie;
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
    private boolean disableUrlEncoding;

    private Method method = Method.GET;

    private Map<String, Object> requestParams;

    private String requestBody;

    private String contentType;

    @JSONField(name = "cookieMap", deserializeUsing = HttpCookieDeserializer.class)
    private Map<String, HttpCookie> cookieMap;

    private Map<String, String> headerMap = new HashMap<>();

    // 上传文件信息, 注意https上传文件不支持完整的零拷贝，它需要将数据读取到内存中进行加密。
    private UploadFile uploadFile;

    private int timeout = 60000;

    // 设置是否打开重定向，如果打开默认重定向次数为2
    private boolean followRedirect;

    // 重定向最大次数
    private int maxRedirectCount = 2;

    // 重定向是否携带cookie 默认false
    private boolean followRedirectsCookie;

    // 构建参数, 不参与请求
    private Map<String, Object> buildParams;

    // 默认开启缓存
    private boolean disableCache;

    public static HttpRequest urlFormatBuilder(CharSequence urlTemplate) {
        return urlFormatBuilder(Method.GET, urlTemplate, null, null);
    }

    public static HttpRequest urlFormatBuilder(Method method, CharSequence urlTemplate) {
        return urlFormatBuilder(method, urlTemplate, null, null);
    }

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

    public HttpRequest addCookie(String key, String value) {
        return addCookie(new HttpCookie(key, value));
    }

    public HttpRequest addCookie(HttpCookie cookie) {
        if (this.cookieMap == null) this.cookieMap = new HashMap<>();
        this.cookieMap.put(cookie.getName(), cookie);
        return this;
    }

    public HttpRequest setCookieMap(Map<String, HttpCookie> cookieMap) {
        if (this.cookieMap == null) this.cookieMap = new HashMap<>();
        this.cookieMap.putAll(cookieMap);
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
        this.headerMap = initMap(this.headerMap);
        if (headerMap != null) {
            headerMap = ObjectUtil.clone(headerMap);
            headerMap.putAll(this.headerMap);
            this.headerMap = headerMap;
        }
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
