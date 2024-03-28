package pp.weiba.framework.core.client;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.HttpCookieDeserializer;

import java.net.HttpCookie;
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

    @JSONField(name = "cookieMap", deserializeUsing = HttpCookieDeserializer.class)
    private Map<String, HttpCookie> cookieMap;

    // 构建参数, 不参与请求
    private Map<String, Object> buildParams;

    public HttpResponse addCookie(String key, String value) {
        if (CollUtil.isEmpty(this.cookieMap)) this.cookieMap = new HashMap<>();
        this.cookieMap.put(key, new HttpCookie(key, value));
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

    public String getCookieValue(String cookieKey) {
        String cookieValue = "";
        if (this.cookieMap != null && this.cookieMap.containsKey(cookieKey)) {
            cookieValue = this.cookieMap.get(cookieKey).getValue();
        }

        return cookieValue;
    }

}
