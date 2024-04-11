package pp.weiba.framework.core.client;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

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
public class HttpRequest extends AuthInfo<HttpRequest> {

    private String url;

    // url 是否需要Encode
    private boolean disableUrlEncoding;

    private Method method = Method.GET;

    private Map<String, Object> requestParams;

    private String requestBody;

    private String contentType;

    // 上传文件信息, 注意https上传文件不支持完整的零拷贝，它需要将数据读取到内存中进行加密。
    private UploadFile uploadFile;

    private int timeout = 60000;

    // 设置是否打开重定向，如果打开默认重定向次数为2
    private Boolean followRedirect = false;

    // 重定向最大次数
    private Integer maxRedirectCount = 2;

    // 重定向是否携带cookie 默认false
    private Boolean followRedirectsCookie = false;

    // 构建参数, 不参与请求
    private Map<String, Object> buildParams;

    // 默认开启缓存
    private Boolean disableCache = false;

    private Boolean htmlRequest = false;

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


    public HttpRequest requestParams(Map<String, Object> hashMap) {
        this.requestParams = hashMap;
        return this;
    }

    public HttpRequest addBuildParams(String key, Object value) {
        this.buildParams = addMapValue(this.buildParams, key, value);
        return this;
    }

    public Object getBuildParam(String name) {
        if (this.buildParams != null) {
            return this.buildParams.get(name);
        }
        return null;
    }
}
