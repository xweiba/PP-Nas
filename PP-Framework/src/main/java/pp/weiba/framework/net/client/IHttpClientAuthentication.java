package pp.weiba.framework.net.client;

import pp.weiba.framework.net.client.model.HttpRequest;

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
