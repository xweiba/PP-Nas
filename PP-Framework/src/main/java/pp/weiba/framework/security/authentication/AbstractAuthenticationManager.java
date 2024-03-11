package pp.weiba.framework.security.authentication;

import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 认证信息管理器
 *
 * @author weiba
 * @date 2024/3/7 16:15
 */
@Log4j2
public abstract class AbstractAuthenticationManager {

    protected static final Map<String, Object> authenticationMap = new ConcurrentHashMap<>();

    public static <T> void setAuthentication(String businessId, String businessType, T authentication) {
        Objects.requireNonNull(businessId, "businessId不能为空");
        Objects.requireNonNull(businessType, "businessType不能为空");
        Objects.requireNonNull(authentication, "authentication不能为空");
        authenticationMap.put(buildKey(businessId, businessType), authentication);
    }

    public static <T> T getAuthentication(String businessId, String businessType) {
        Objects.requireNonNull(businessId, "businessId不能为空");
        Objects.requireNonNull(businessType, "businessType不能为空");
        Object authentication = authenticationMap.get(buildKey(businessId, businessType));
        if (authentication != null) {
            return (T) authentication;
        }
        return null;
    }

    public static void removeAuthentication(String businessId, String businessType) {
        Objects.requireNonNull(businessId, "businessId不能为空");
        Objects.requireNonNull(businessType, "businessType不能为空");
        authenticationMap.remove(buildKey(businessId, businessType));
    }

    public static String buildKey(String businessId, String businessType) {
        return businessId + "@" + businessType;
    }

}
