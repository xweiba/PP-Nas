package pp.weiba.framework.net.client;

import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.net.client.model.HttpResponse;

/**
 * Http Client 请求信息与响应信息转换适配接口
 *
 * @author weiba
 * @date 2024/3/7 10:45
 */
public interface IHttpTypeAdapter<T, F> {

    T adapter(HttpRequest request);

    HttpResponse adapter(F response);

}
