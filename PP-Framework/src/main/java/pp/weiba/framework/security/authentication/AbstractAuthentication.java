package pp.weiba.framework.security.authentication;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.ICredential;

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

    private final ICredential<T> credential;

    public AbstractAuthentication(String authenticationId, String authenticationType, ICredential<T> credential) {
        this.authenticationId = authenticationId;
        this.authenticationType = authenticationType;
        this.credential = credential;
    }

    /**
     * 初始化认证信息
     *
     * @return 返回认证信息
     * @author weiba
     * @date 2024/3/8 11:19
     */
    protected T initAuthentication() {
        return credential.getCredential();
    }

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
    protected T completeAuthenticationInformation(T authentication) {
        return authentication;
    }

    @Override
    public T login() {
        T authentication = initAuthentication();
        appLogin(authentication);
        authentication = detectionAuthentication(authentication);
        authentication = completeAuthenticationInformation(authentication);
        return authentication;
    }

    protected void appLogin(T netDiskAuthentication) {
        if (netDiskAuthentication == null) {
            throw new RuntimeException("认证信息为空");
        }
        // 存储到认证管理器， 后面请求时要使用
        AuthenticationManager.setAuthentication(authenticationId, authenticationType, netDiskAuthentication);
    }

    @Override
    public void logout() {
        doLogout();
    }

    protected abstract void doLogout();

}
