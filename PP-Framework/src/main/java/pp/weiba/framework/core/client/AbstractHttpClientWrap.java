package pp.weiba.framework.core.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IDataProcessor;
import pp.weiba.framework.core.convert.TypeReference;

/**
 * HttpClient 包装器
 *
 * @author weiba
 * @date 2024/3/8 10:38
 */
@Log4j2
public abstract class AbstractHttpClientWrap implements IHttpClient {

    protected final IHttpClient httpClient;

    protected final IHttpClientAuthentication authentication;

    // 延时初始化， 避免在对象构造时就执行不必要的逻辑，以提高性能和资源利用率。
    private boolean isClientConfigInitialized = false;

    public AbstractHttpClientWrap(IHttpClient httpClient, IHttpClientAuthentication authentication) {
        this.httpClient = httpClient;
        this.authentication = authentication;
    }

    private synchronized void ensureClientConfigInitialized() {
        if (!isClientConfigInitialized) {
            initClientConfig();
            isClientConfigInitialized = true;
        }
    }

    @Override
    public HttpResponse execute(HttpRequest request) {
        request = initExecute(request);
        return httpClient.execute(request);
    }

    private HttpRequest initExecute(HttpRequest request) {
        ensureClientConfigInitialized();
        request = authenticationHttpRequest(request);
        return request;
    }

    // 处理请求认证信息
    private HttpRequest authenticationHttpRequest(HttpRequest request) {
        return authentication.authentication(request);
    }

    @Override
    public <T> T execute(HttpRequest request, TypeReference<T> typeReference) {
        request = initExecute(request);
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

    // 初始化所有的请求数据处理器
    protected abstract void initDefaultProcessors();

    protected void initClientConfig() {
        initDefaultProcessors();
    }
}