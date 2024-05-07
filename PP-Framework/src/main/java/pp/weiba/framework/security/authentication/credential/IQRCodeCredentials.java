package pp.weiba.framework.security.authentication.credential;

/**
 * 二维码扫描认证处理器
 *
 * @author weiba
 * @date 2024/3/7 16:32
 */
public interface IQRCodeCredentials<T> extends ICredential<T> {

    String getQRUrl();

}
