package pp.weiba.framework.core.client;

import cn.hutool.core.util.StrUtil;
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

    private final IHttpClient httpClient;

    protected AbstractApiHttpClient(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected <T> T execute(String url, TypeReference<T> typeReference) {
        return execute(Method.GET, url, null, typeReference);
    }

    protected <T> T execute(String url, TypeReference<T> typeReference, Object... urlFormatVals) {
        return execute(Method.GET, StrUtil.format(url, urlFormatVals), null, typeReference);
    }

    protected <T> T execute(String url, Map<?, ?> urlParams, TypeReference<T> typeReference) {
        return execute(Method.GET, url, urlParams, typeReference);
    }

    protected <T> T postExecute(String url, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        return execute(Method.POST, url, null, requestParams, typeReference);
    }

    protected <T> T postExecute(String url, Map<String, Object> requestParams, TypeReference<T> typeReference, Object... urlFormatVals) {
        return execute(Method.POST, StrUtil.format(url, urlFormatVals), null, requestParams, typeReference);
    }

    protected <T> T postExecuteUrlParams(String url, Map<?, ?> urlParams, TypeReference<T> typeReference) {
        return execute(Method.POST, url, urlParams, typeReference);
    }

    protected <T> T postExecuteUrlParams(String url, Map<?, ?> urlParams, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        return execute(Method.POST, url, urlParams, requestParams, typeReference);
    }

    protected <T> T execute(Method method, String url, Map<?, ?> urlParams, TypeReference<T> typeReference) {
        return execute(method, url, urlParams, null, typeReference);
    }

    protected <T> T execute(Method method, String url, Map<?, ?> urlParams, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        return execute(method, url, null, urlParams, requestParams, typeReference);
    }

    protected <T> T execute(Method method, String url, Map<String, Object> buildParams, Map<?, ?> urlParams, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(method, url, buildParams, urlParams).requestParams(requestParams);
        return httpClient.execute(httpRequest, typeReference);
    }

    protected <T> T execute(HttpRequest request, TypeReference<T> typeReference) {
        return httpClient.execute(request, typeReference);
    }


    protected HttpResponse executeResponse(String url) {
        return executeResponse(Method.GET, url, null);
    }

    protected HttpResponse executeResponse(String url, Map<?, ?> urlParams) {
        return executeResponse(Method.GET, url, urlParams);
    }

    protected HttpResponse postExecuteResponse(String url, Map<String, Object> requestParams) {
        return executeResponse(Method.POST, url, null, requestParams);
    }

    protected HttpResponse postExecuteUrlParamsResponse(String url, Map<?, ?> urlParams) {
        return executeResponse(Method.POST, url, urlParams);
    }

    protected HttpResponse postExecuteUrlParamsResponse(String url, Map<?, ?> urlParams, Map<String, Object> requestParams) {
        return executeResponse(Method.POST, url, urlParams, requestParams);
    }

    protected HttpResponse executeResponse(Method method, String url, Map<?, ?> urlParams) {
        return executeResponse(method, url, urlParams, null);
    }

    protected HttpResponse executeResponse(Method method, String url, Map<?, ?> urlParams, Map<String, Object> requestParams) {
        return executeResponse(method, url, null, urlParams, requestParams, null);
    }

    protected HttpResponse executeResponse(Method method, String url, Map<String, Object> buildParams, Map<?, ?> urlParams, Map<String, Object> requestParams, Map<String, String> headerMap) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(method, url, buildParams, urlParams).requestParams(requestParams).handler(headerMap);
        return httpClient.execute(httpRequest);
    }

    protected HttpResponse executeResponse(HttpRequest httpRequest) {
        return httpClient.execute(httpRequest);
    }

}
