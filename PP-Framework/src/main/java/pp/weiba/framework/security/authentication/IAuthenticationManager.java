package pp.weiba.framework.security.authentication;

/**
 * 认证信息管理
 *
 * @author weiba
 * @date 2024/3/11 11:09
 */
public interface IAuthenticationManager<T> {

    void setAuthentication(String businessId, String businessType, T authentication);

    T getAuthentication(String businessId, String businessType);

    void removeAuthentication(String businessId, String businessType);

}
