package pp.weiba.thirdparty.baidu.web.api.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * Http响应信息
 *
 * @author weiba
 * @date 2024/3/7 10:21
 */
@Log4j2
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HttpResponse {

    private int statusCode;

    private String statusText;

    private String body;

    private String contentType;

    private Map<String, String> headers;

    private Map<String, String> cookies;

    // 构建参数, 不参与请求
    private Map<String, Object> buildParams;

    public HttpResponse addCookies(String key, String value) {
        this.cookies = addMapValue(this.cookies, key, value);
        return this;
    }

    public HttpResponse addheader(String key, String value) {
        this.headers = addMapValue(this.headers, key, value);
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
