package pp.weiba.framework.security.authentication;

/**
 * 认证接口
 *
 * @author weiba
 * @date 2024/3/7 16:26
 */
public interface IAuthentication<T> {

    /**
     * 初始化认证信息
     *
     * @return 返回认证信息
     * @author weiba
     * @date 2024/3/8 11:19
     */
    T initAuthentication();

    /**
     * 认证信息检测，判断是否有效
     *
     * @param authentication 认证信息
     * @author weiba
     * @date 2024/3/8 11:25
     */
    T detectionAuthentication(T authentication);


    /**
     * 补全认证信息
     *
     * @param authentication 认证信息
     * @return 返回完整认证信息
     * @author weiba
     * @date 2024/3/8 11:20
     */
    T completeAuthenticationInformation(T authentication);

    /**
     * 构建认证信息
     *
     * @return 返回认证信息
     * @author weiba
     * @date 2024/3/8 11:20
     */
    T buildAuthentication();

}
