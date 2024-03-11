package pp.weiba.framework.core.client;

/**
 * httpClient 认证处理接口
 *
 * @author weiba
 * @date 2024/3/11 10:27
 */
public interface IHttpClientAuthentication {

    HttpRequest authentication(HttpRequest request);

    String getAuthenticationId();

    String getAuthenticationType();

}
