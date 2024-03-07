package pp.weiba.thirdparty.baidu.web.client;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HttpRequest {

    private String url;

    // url 是否需要Encode
    private Boolean disableUrlEncoding = Boolean.FALSE;

    private Method method = Method.GET;

    private Map<String, Object> params;

    private String requestBody;

    private String contentType;

    private Map<String, String> headerMap = new HashMap<>();

    private Integer timeout;

    // 设置是否打开重定向，如果打开默认重定向次数为2
    private Boolean followRedirects;

    // 重定向最大次数
    private Integer maxRedirectCount;

    // 重定向是否携带cookie 默认false
    private Boolean followRedirectsCookie;

    // 构建参数, 不参与请求
    private Map<String, Object> buildParams;

    public static HttpRequest urlFormatBuilder(CharSequence urlTemplate, Object... urlParams) {
        return urlFormatBuilder(Method.GET, urlTemplate, urlParams);
    }

    public static HttpRequest urlFormatBuilder(Method method, CharSequence urlTemplate, Object... urlParams) {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(StrUtil.format(urlTemplate, urlParams));
        return httpRequest;
    }

    public HttpRequest addParam(String key, Object value) {
        if (this.params == null) this.params = new HashMap<>();
        this.params.put(key, value);
        return this;
    }

    public HttpRequest addheader(String key, String value) {
        this.headerMap.put(key, value);
        return this;
    }

}
