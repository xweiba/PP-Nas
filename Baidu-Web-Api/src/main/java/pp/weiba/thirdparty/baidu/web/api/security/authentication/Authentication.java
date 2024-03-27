package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;
import pp.weiba.framework.core.convert.HttpCookieDeserializer;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.LoginStatusResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.TemplateVariableResponse;

import java.net.HttpCookie;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 百度认证信息
 *
 * @author weiba
 * @date 2024/3/7 16:22
 */
@Accessors(chain = true)
@Data
public class Authentication {

    public TemplateVariableResponse.Result templateVariable;
    public LoginStatusResponse.LoginInfo loginInfo;

    @JSONField(name = "cookieMap", deserializeUsing = HttpCookieDeserializer.class)
    private Map<String, HttpCookie> cookieMap;

    public Authentication(HttpCookie... cookies) {
        if (cookies == null || cookies.length == 0) {
            throw new IllegalArgumentException("cookies 不能为空");
        }
        this.cookieMap = Arrays.stream(cookies).collect(Collectors.toMap(HttpCookie::getName, cookie -> cookie));
    }

    public Authentication(List<HttpCookie> cookies) {
        if (cookies == null || cookies.isEmpty()) {
            throw new IllegalArgumentException("cookies 不能为空");
        }
        this.cookieMap = cookies.stream().collect(Collectors.toMap(HttpCookie::getName, cookie -> cookie));
    }

    public Authentication(Map<String, HttpCookie> cookieMap) {
        if (CollUtil.isNotEmpty(cookieMap)) {
            throw new IllegalArgumentException("cookieMap 不能为空");
        }
        this.cookieMap = cookieMap;
    }

    public String getCookieValue(String cookieName) {
        if (cookieMap.containsKey(cookieName)) {
            return cookieMap.get(cookieName).getValue();
        }
        return null;
    }

    public boolean containsCookie(String cookieName) {
        return cookieMap.containsKey(cookieName);
    }

}
