package pp.weiba.framework.core.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;

import java.util.Map;

/**
 * Api 客户端抽象类
 *
 * @author weiba
 * @date 2024/3/7 9:51
 */
@Log4j2
public abstract class AbstractApiHttpClient {

    protected final IHttpClient httpClient;

    public AbstractApiHttpClient(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public <T> T execute(String url, TypeReference<T> typeReference) {
        return execute(Method.GET, url, null, typeReference);
    }

    public <T> T execute(String url, Map<?, ?> urlParams, TypeReference<T> typeReference) {
        return execute(Method.GET, url, urlParams, typeReference);
    }

    public <T> T postExecute(String url, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        return execute(Method.POST, url, null, requestParams, typeReference);
    }

    public <T> T postExecuteUrlParams(String url, Map<String, String> urlParams, TypeReference<T> typeReference) {
        return execute(Method.POST, url, urlParams, typeReference);
    }

    public <T> T postExecuteUrlParams(String url, Map<String, String> urlParams, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        return execute(Method.POST, url, urlParams, requestParams, typeReference);
    }

    public <T> T execute(Method method, String url, Map<?, ?> urlParams, TypeReference<T> typeReference) {
        return execute(method, url, urlParams, null, typeReference);
    }

    public <T> T execute(Method method, String url, Map<?, ?> urlParams, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        return execute(method, url, null, urlParams, requestParams, typeReference);
    }

    public <T> T execute(Method method, String url, Map<String, Object> buildParams, Map<?, ?> urlParams, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(method, url, buildParams, urlParams).requestParams(requestParams);
        return httpClient.execute(httpRequest, typeReference);
    }

}
