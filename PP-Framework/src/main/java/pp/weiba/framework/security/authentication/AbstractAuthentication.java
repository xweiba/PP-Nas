package pp.weiba.framework.security.authentication;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.KeyValue;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.framework.utils.UserInfoUtils;

/**
 * 认证信息抽象类
 *
 * @author weiba
 * @date 2024/3/8 11:10
 */
@Log4j2
public abstract class AbstractAuthentication<T> implements IAuthentication<T> {

    protected final ICredential<T> credential;

    protected T authentication;

    public AbstractAuthentication(ICredential<T> credential) {
        this.credential = credential;
    }

    /**
     * 初始化认证信息
     *
     * @return 返回认证信息
     * @author weiba
     * @date 2024/3/8 11:19
     */
    protected void initAuthentication() {
        authentication = credential.getCredential();
        credential.refresh();
    }

    /**
     * 认证信息检测，判断是否有效
     *
     * @param authentication 认证信息
     * @author weiba
     * @date 2024/3/8 11:25
     */
    protected abstract void detectionAuthentication();


    /**
     * 补全认证信息
     *
     * @return 返回完整认证信息
     * @author weiba
     * @date 2024/3/8 11:20
     */
    protected void completeAuthenticationInformation() {
    }

    @Override
    public T login() {
        initAuthentication();
        appLogin();
        refreshAuthenticationToManager();
        detectionAuthentication();
        completeAuthenticationInformation();
        return authentication;
    }

    protected void appLogin() {
        if (authentication == null) {
            throw new RuntimeException("认证信息为空");
        }
    }

    protected void refreshAuthenticationToManager() {
        AuthenticationManager.setAuthentication(UserInfoUtils.getCurrentThreadUserId(), UserInfoUtils.getCurrentThreadUserType(), authentication);
    }

    @Override
    public void logout() {
        doLogout();
    }

    protected abstract void doLogout();

}
