package pp.weiba.framework.net.client;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.net.client.model.HttpResponse;
import pp.weiba.framework.net.client.model.Method;
import pp.weiba.utils.JSONUtils;

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

    protected <T> T execute(TypeReference<T> typeReference, String url, Object... urlFormatVals) {
        return execute(Method.GET, StrUtil.format(url, urlFormatVals), null, typeReference);
    }

    protected <T> T execute(String url, Map<?, ?> urlParams, TypeReference<T> typeReference) {
        return execute(Method.GET, url, urlParams, typeReference);
    }

    protected <T> T postExecute(String url, Map<String, Object> requestParams, TypeReference<T> typeReference) {
        return execute(Method.POST, url, null, requestParams, typeReference);
    }

    protected <T> T postExecute(String url, TypeReference<T> typeReference) {
        return execute(Method.POST, url, null, null, typeReference);
    }

    protected <T> T postExecute(String url, String requestBody, TypeReference<T> typeReference) {
        return execute(Method.POST, url, null, null, requestBody, typeReference);
    }

    protected <T> T postSrtExecute(String url, Object requestBodyObj, TypeReference<T> typeReference) {
        return execute(Method.POST, url, null, null, JSONUtils.toJsonStr(requestBodyObj), typeReference);
    }

    protected <T> T postExecute(Map<String, Object> requestParams, TypeReference<T> typeReference, String url, Object... urlFormatVals) {
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
        return execute(method, url, buildParams, urlParams, requestParams, null, typeReference);
    }

    protected <T> T execute(Method method, String url, Map<String, Object> buildParams, Map<?, ?> urlParams, String requestBody, TypeReference<T> typeReference) {
        return execute(method, url, buildParams, urlParams, null, requestBody, typeReference);
    }

    protected <T> T execute(Method method, String url, Map<String, Object> buildParams, Map<?, ?> urlParams, Map<String, Object> requestParams, String requestBody, TypeReference<T> typeReference) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(method, url, buildParams, urlParams).requestParams(requestParams).setRequestBody(StrUtil.isBlank(requestBody) ? null : requestBody);
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

    protected HttpResponse postStrExecuteResponse(String url, String requestParams) {
        return executeSrtResponse(Method.POST, url, null, requestParams);
    }

    protected HttpResponse postStrExecuteResponse(String url, Object requestParams) {
        return executeSrtResponse(Method.POST, url, null, JSONUtils.toJsonStr(requestParams));
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
        return executeResponse(method, url, null, urlParams, requestParams, null, null);
    }

    protected HttpResponse executeSrtResponse(Method method, String url, Map<?, ?> urlParams, String requestBody) {
        return executeResponse(method, url, null, urlParams, null, requestBody, null);
    }

    protected HttpResponse executeResponse(Method method, String url, Map<String, Object> buildParams, Map<?, ?> urlParams, Map<String, Object> requestParams, String requestBody, Map<String, String> headerMap) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(method, url, buildParams, urlParams).requestParams(requestParams).handler(headerMap).setRequestBody(StrUtil.isBlank(requestBody) ? null : requestBody);
        return httpClient.execute(httpRequest);
    }

    protected HttpResponse executeResponse(HttpRequest httpRequest) {
        return httpClient.execute(httpRequest);
    }

}
