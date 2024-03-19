package pp.weiba.framework.security.authentication;

import lombok.extern.log4j.Log4j2;

/**
 * 认证信息抽象类
 *
 * @author weiba
 * @date 2024/3/8 11:10
 */
@Log4j2
public abstract class AbstractAuthentication<T> implements IAuthentication<T> {

    protected final String authenticationId;

    protected final String authenticationType;

    public AbstractAuthentication(String authenticationId, String authenticationType) {
        this.authenticationId = authenticationId;
        this.authenticationType = authenticationType;
    }

    /**
     * 初始化认证信息
     *
     * @return 返回认证信息
     * @author weiba
     * @date 2024/3/8 11:19
     */
    protected abstract T initAuthentication();

    /**
     * 认证信息检测，判断是否有效
     *
     * @param authentication 认证信息
     * @author weiba
     * @date 2024/3/8 11:25
     */
    protected abstract T detectionAuthentication(T authentication);


    /**
     * 补全认证信息
     *
     * @param authentication 认证信息
     * @return 返回完整认证信息
     * @author weiba
     * @date 2024/3/8 11:20
     */
    protected abstract T completeAuthenticationInformation(T authentication);

    @Override
    public T login() {
        T authentication = initAuthentication();
        authentication = detectionAuthentication(authentication);
        authentication = completeAuthenticationInformation(authentication);
        return authentication;
    }

    @Override
    public void logout() {
        doLogout();
    }

    protected abstract void doLogout();

}
