package pp.weiba.utils.http;

import com.alibaba.fastjson.TypeReference;

/**
 * Http接口
 *
 * @author weiba
 * @date 2024/2/28 10:09
 */
public interface IHttpClient {

    String execute(HttpClientRequest request);

    <T> T execute(HttpClientRequest request, TypeReference<T> typeReference);

}
