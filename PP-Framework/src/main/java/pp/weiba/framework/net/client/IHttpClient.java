package pp.weiba.framework.net.client;

import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.core.handler.ExecutorParams;
import pp.weiba.framework.core.handler.IHandler;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.net.client.model.HttpResponse;

/**
 * Http客户端
 *
 * @author weiba
 * @date 2024/3/6 16:23
 */
public interface IHttpClient {

    /**
     * 发送请求
     *
     * @param request 请求参数
     * @return 请求结果
     * @author weiba
     * @date 2024/3/6 17:06
     */
    HttpResponse execute(HttpRequest request);


    /**
     * 发送请求, 并通过TypeReference将Response转为对应类型
     *
     * @param request       请求参数
     * @param typeReference 转换类型
     * @return 转换后的请求结果
     * @author weiba
     * @date 2024/3/7 14:32
     */
    <T> T execute(HttpRequest request, TypeReference<T> typeReference);


    /**
     * 添加请求处理器，在请求前调用，默认实现同类型过滤器只会被添加一次, 按加入顺序执行
     *
     * @param requestHandler 过滤器
     * @author weiba
     * @date 2024/3/6 17:07
     */
    void addRequestHandler(IHandler<HttpRequest> requestHandler);

    /**
     * 添加响应处理器，在响应后调用，默认实现同类型过滤器只会被添加一次, 按加入顺序执行
     *
     * @param responseHandler 过滤器
     * @author weiba
     * @date 2024/3/6 17:07
     */
    void addResponseHandler(IHandler<HttpResponse> responseHandler);

    /**
     * 添加执行器处理器，可实现请求拦截，缓存，限流等处理，默认实现同类型过滤器只会被添加一次, 按加入顺序执行
     *
     * @param executeHandler 执行处理器
     * @author weiba
     * @date 2024/3/19 11:02
     */
    void addExecuteHandler(IHandler<ExecutorParams<HttpRequest, HttpResponse>> executeHandler);

}
