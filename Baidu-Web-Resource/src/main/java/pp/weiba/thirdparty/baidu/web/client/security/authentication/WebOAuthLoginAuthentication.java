package pp.weiba.thirdparty.baidu.web.client.security.authentication;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.framework.core.convert.HttpCookieDeserializer;

import java.net.HttpCookie;
import java.util.Map;

/**
 * Web登录权限信息, 通过此信息，登录其他应用
 *
 * @author weiba
 * @date 2024/3/27 11:04
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class WebOAuthLoginAuthentication {

    public WebOAuthLoginAuthentication(Map<String, HttpCookie> cookieMap) {
        this.cookieMap = cookieMap;
    }

    private LoginResponse loginResponse;

    @JSONField(name = "cookieMap", deserializeUsing = HttpCookieDeserializer.class)
    private Map<String, HttpCookie> cookieMap;

}
