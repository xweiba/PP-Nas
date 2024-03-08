package pp.weiba.thirdparty.baidu.web.client;

import lombok.extern.log4j.Log4j2;

/**
 * HttpClient 包装器
 *
 * @author weiba
 * @date 2024/3/8 10:38
 */
@Log4j2
public abstract class AbstractHttpClientWrap implements IHttpClient {

    protected final IHttpClient httpClient;

    // 延时初始化， 避免在对象构造时就执行不必要的逻辑，以提高性能和资源利用率。
    private boolean isClientConfigInitialized = false;

    public AbstractHttpClientWrap(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private synchronized void ensureClientConfigInitialized() {
        if (!isClientConfigInitialized) {
            initClientConfig();
            isClientConfigInitialized = true;
        }
    }

    @Override
    public HttpResponse execute(HttpRequest request) {
        ensureClientConfigInitialized();
        return httpClient.execute(request);
    }

    @Override
    public <T> T execute(HttpRequest request, TypeReference<T> typeReference) {
        ensureClientConfigInitialized();
        return httpClient.execute(request, typeReference);
    }

    @Override
    public void addRequestDataProcessor(IDataProcessor<HttpRequest> httpRequestProcessor) {
        httpClient.addRequestDataProcessor(httpRequestProcessor);
    }

    @Override
    public void addResponseDataProcessor(IDataProcessor<HttpResponse> httpResponseProcessor) {
        httpClient.addResponseDataProcessor(httpResponseProcessor);
    }

    protected abstract void initDefaultProcessors();

    protected void initClientConfig() {
        initDefaultProcessors();
    }
}
