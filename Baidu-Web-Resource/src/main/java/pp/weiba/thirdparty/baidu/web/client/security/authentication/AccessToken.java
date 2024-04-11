package pp.weiba.thirdparty.baidu.web.client.security.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 认证信息
 * https://openapi.baidu.com/oauth/2.0/login_success#expires_in=2592000&access_token=333.e1789e28d532096a6fdf42ef250466c6.Yaew4auT58m3DK0AsvC8Vmxxsds7pBaVCUs8.5gfYbg&session_secret=&session_key=&scope=basic+netdisk
 *
 * @author weiba
 * @date 2024/3/25 13:19
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {

    private Date expiresIn;

    private String accessToken;

    private String sessionSecret;

    private String scope;

}
