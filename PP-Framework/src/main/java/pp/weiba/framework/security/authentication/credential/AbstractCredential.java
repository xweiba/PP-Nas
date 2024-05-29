package pp.weiba.framework.security.authentication.credential;

import lombok.extern.log4j.Log4j2;

/**
 * 认证接口抽象
 *
 * @author weiba
 * @date 2024/5/8 14:22
 */
@Log4j2
public abstract class AbstractCredential<T> implements ICredential<T> {

    protected T credentialData;

    @Override
    public T getCredential() {
        buildCredential();
        return credentialData;
    }

    @Override
    public void updateCredential(String key, T result) {
        credentialData = result;
    }
}
