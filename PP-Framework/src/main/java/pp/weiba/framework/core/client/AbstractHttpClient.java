package pp.weiba.framework.core.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.ITypeReferenceProcessor;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.core.handler.BaseHandler;
import pp.weiba.framework.core.handler.ExecutorParams;
import pp.weiba.framework.core.handler.IHandler;
import pp.weiba.framework.utils.HandlerUtils;

/**
 * HttpClient 客户端
 *
 * @author weiba
 * @date 2024/3/7 10:29
 */
@Log4j2
public abstract class AbstractHttpClient<T, F> extends BaseHandler<ExecutorParams<HttpRequest, HttpResponse>> implements IHttpClient {

    // Http 请求与响应信息转换器
    private final IHttpTypeAdapter<T, F> httpTypeAdapter;

    // Http 响应body转对象-转换器
    private final ITypeReferenceProcessor<String> typeReferenceProcessor;

    // Http 请求参数处理链
    private IHandler<HttpRequest> requestChain;

    // Http 响应处理链
    private IHandler<HttpResponse> responseChain;

    // Http 响应处理链
    private IHandler<ExecutorParams<HttpRequest, HttpResponse>> executorChain;

    public AbstractHttpClient(IHttpTypeAdapter<T, F> httpTypeAdapter, ITypeReferenceProcessor<String> typeReferenceProcessor) {
        this.httpTypeAdapter = httpTypeAdapter;
        this.typeReferenceProcessor = typeReferenceProcessor;
        this.executorChain = this;
    }

    @Override
    public HttpResponse execute(HttpRequest request) {

        // 请求参数处理器
        if (requestChain != null) {
            request = requestChain.handle(request);
        }

        // 执行请求
        HttpResponse adapterResponse = httpExecute(request);

        // 响应信息过滤
        if (responseChain != null) {
            adapterResponse = responseChain.handle(adapterResponse);
        }
        return adapterResponse;
    }

    private HttpResponse httpExecute(HttpRequest request) {
        return this.handle(new ExecutorParams<HttpRequest, HttpResponse>().setInput(request)).getOutput();
    }

    @Override
    public <R> R execute(HttpRequest request, TypeReference<R> typeReference) {
        HttpResponse response = execute(request);
        return typeReferenceProcessor.process(response.getBody(), typeReference);
    }

    protected abstract F doExecute(T request);

    public void addRequestHandler(IHandler<HttpRequest> requestHandler) {
        if (requestChain == null) {
            requestChain = requestHandler;
        } else {
            HandlerUtils.addHandlerToEnd(requestChain, requestHandler, Boolean.TRUE);
        }
    }

    public void addResponseHandler(IHandler<HttpResponse> responseHandler) {
        if (responseChain == null) {
            responseChain = responseHandler;
        } else {
            HandlerUtils.addHandlerToEnd(responseChain, responseHandler, Boolean.TRUE);
        }
    }

    @Override
    public void addExecuteHandler(IHandler<ExecutorParams<HttpRequest, HttpResponse>> executeHandler) {

        if (!HandlerUtils.handlerContains(this.executorChain, executeHandler)) {
            // 先加入 先执行
            if (this.executorChain == this) {
                this.executorChain = executeHandler;
            } else {
                this.executorChain.setNext(executeHandler);
            }
            // 本类必须最后执行，因为加入的执行链必须在本类执行链之前执行
            executeHandler.setNext(this);
        }
    }

    @Override
    protected ExecutorParams<HttpRequest, HttpResponse> process(ExecutorParams<HttpRequest, HttpResponse> input) {
        // 适配请求数据
        T adapterRequest = httpTypeAdapter.adapter(input.getInput());

        // 执行请求
        F executeRequest = doExecute(adapterRequest);

        // 适配响应数据
        HttpResponse adapter = httpTypeAdapter.adapter(executeRequest);
        input.setOutput(adapter);

        return input;
    }
}
