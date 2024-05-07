package pp.weiba.thirdparty.aliyun.web.client.authentication.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.net.HttpCookie;
import java.util.Map;

/**
 * 登录初始化页面
 *
 * @author weiba
 * @date 2024/5/7 13:54
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginMainResponse {

    private LoginMainViewData viewData;

    private LonginMainConfig config;

    private Map<String, HttpCookie> cookieMap;
}
