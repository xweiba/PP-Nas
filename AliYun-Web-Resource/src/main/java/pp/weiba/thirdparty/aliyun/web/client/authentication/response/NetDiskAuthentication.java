package pp.weiba.thirdparty.aliyun.web.client.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;
import pp.weiba.framework.core.convert.HttpCookieDeserializer;

import java.net.HttpCookie;
import java.util.Map;

/**
 * 认证信息
 *
 * @author weiba
 * @date 2024/4/29 17:08
 */
@Data
@Accessors(chain = true)
public class NetDiskAuthentication {

    private String authorization;

    // 应用的 cookie map
    @JSONField(name = "cookieMap", deserializeUsing = HttpCookieDeserializer.class)
    private Map<String, HttpCookie> domainCookieMap;


}
