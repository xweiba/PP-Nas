package pp.weiba.thirdparty.baidu.web.security.authentication;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证信息管理器
 *
 * @author weiba
 * @date 2024/3/7 16:15
 */
@Log4j2
public class AuthenticationManager {

    public static final Map<String, Authentication> authenticationMap = new HashMap<>();

    public static void setAuthentication(String key, Authentication value) {
        authenticationMap.put(key, value);
    }

    public static Authentication getAuthentication(String key) {
        return authenticationMap.get(key);
    }

    public static void removeAuthentication(String key) {
        authenticationMap.remove(key);
    }

    public static Authentication getAuthentication(String identityInformationId, String identityInformationType) {
        return getAuthentication(identityInformationId + "_" + identityInformationType);
    }
}
