package pp.weiba.framework.net.client.model;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;
import pp.weiba.framework.core.convert.HttpCookieDeserializer;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证信息
 *
 * @author weiba
 * @date 2024/4/11 11:01
 */
@Accessors
@Data
public abstract class AuthInfo<T> {

    protected Map<String, String> headerMap;

    @JSONField(name = "cookieMap", deserializeUsing = HttpCookieDeserializer.class)
    protected Map<String, HttpCookie> cookieMap;

    public T addCookie(String key, String value) {
        return addCookie(new HttpCookie(key, value));
    }

    public T addCookie(HttpCookie cookie) {
        if (cookie != null && StrUtil.isNotBlank(cookie.getName()) && StrUtil.isNotBlank(cookie.getValue())) {
            if (this.cookieMap == null) this.cookieMap = new HashMap<>();
            this.cookieMap.put(cookie.getName(), cookie);
        }
        return (T) this;
    }

    public T handler(Map<String, String> headerMap) {
        this.headerMap = initMap(this.headerMap);
        if (headerMap != null) {
            headerMap = ObjectUtil.clone(headerMap);
            headerMap.putAll(this.headerMap);
            this.headerMap = headerMap;
        }
        return (T) this;
    }

    public T addheader(String key, String value) {
        this.headerMap = addMapValue(this.headerMap, key, value);
        return (T) this;
    }

    public void delHeader(String key) {
        this.headerMap.remove(key);
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

    public T setCookieMap(Map<String, HttpCookie> cookieMap) {
        if (CollUtil.isNotEmpty(cookieMap)) {
            this.cookieMap = initMap(this.cookieMap);
            this.cookieMap.putAll(cookieMap);
        }
        return (T) this;
    }

    public T setCookieMapNotOverlay(Map<String, HttpCookie> cookieMap) {
        if (CollUtil.isNotEmpty(cookieMap)) {
            this.cookieMap = initMap(this.cookieMap);
            for (Map.Entry<String, HttpCookie> item : cookieMap.entrySet()) {
                // 当前有 不覆盖
                if (!this.cookieMap.containsKey(item.getKey())) {
                    this.cookieMap.put(item.getKey(), item.getValue());
                }
            }
        }
        return (T) this;
    }

}
