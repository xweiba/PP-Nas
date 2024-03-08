package pp.weiba.thirdparty.baidu.web.api.security.authentication.credentials;

/**
 * 二维码扫描认证处理器
 *
 * @author weiba
 * @date 2024/3/7 16:32
 */
public interface IQRCodeCredentials extends ICredential {

    String getQRUrl();

}
