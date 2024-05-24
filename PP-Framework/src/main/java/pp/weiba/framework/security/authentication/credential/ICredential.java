package pp.weiba.framework.security.authentication.credential;

/**
 * 认证接口
 *
 * @author weiba
 * @date 2024/3/8 10:55
 */
public interface ICredential<T> {

    default void buildCredential() {

    }

    default T refresh() {
        return null;
    }

    T getCredential();

}
