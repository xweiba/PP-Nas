package pp.weiba.thirdparty.baidu.web.security.authentication;

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

    @Override
    public T buildAuthentication() {
        T authentication = initAuthentication();
        authentication = detectionAuthentication(authentication);
        authentication = completeAuthenticationInformation(authentication);
        return authentication;
    }

}
