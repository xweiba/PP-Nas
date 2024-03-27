package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;
import pp.weiba.framework.core.convert.HttpCookieDeserializer;

import java.net.HttpCookie;
import java.util.Map;

/**
 * 登录权限信息
 *
 * @author weiba
 * @date 2024/3/27 11:04
 */
@Data
@Accessors(chain = true)
public class LoginAuthentication {

    private LoginResponse loginResponse;

    @JSONField(name = "cookieMap", deserializeUsing = HttpCookieDeserializer.class)
    private Map<String, HttpCookie> cookieMap;

}
