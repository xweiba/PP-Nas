package pp.weiba.framework.core.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;

import java.util.HashMap;
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

    public <T> T execute(String url, Map<String, Object> buildParams, TypeReference<T> typeReference) {
        return execute(Method.GET, url, buildParams, null, typeReference);
    }

    public <T> T execute(String url, Map<String, Object> buildParams, Map<?, ?> urlParams, TypeReference<T> typeReference) {
        return execute(Method.GET, url, buildParams, urlParams, typeReference);
    }

    public <T> T postExecute(String url, Map<String, Object> buildParams, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        return execute(Method.POST, url, buildParams, null, requestParams, typeReference);
    }

    public <T> T postExecuteUrlParams(String url, Map<String, Object> buildParams, Map<String, String> urlParams, TypeReference<T> typeReference) {
        return execute(Method.POST, url, buildParams, urlParams, typeReference);
    }

    public <T> T postExecuteUrlParams(String url, Map<String, Object> buildParams, Map<String, String> urlParams, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        return execute(Method.POST, url, buildParams, urlParams, requestParams, typeReference);
    }

    public <T> T execute(Method method, String url, Map<String, Object> buildParams, Map<?, ?> urlParams, TypeReference<T> typeReference) {
        return execute(method, url, buildParams, urlParams, null, typeReference);
    }

    public <T> T execute(Method method, String url, Map<String, Object> buildParams, Map<?, ?> urlParams, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        return httpClient.execute(HttpRequest.urlFormatBuilder(method, url, buildParams, urlParams).requestParams(requestParams), typeReference);
    }

    public Map<String, Object> initBuildParams(String authenticationId, String authenticationType) {
        return new HashMap<String, Object>() {{
            put("authenticationId", authenticationId);
            put("authenticationType", authenticationType);
        }};
    }

}
