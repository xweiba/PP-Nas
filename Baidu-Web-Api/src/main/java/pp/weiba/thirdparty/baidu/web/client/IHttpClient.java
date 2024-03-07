package pp.weiba.thirdparty.baidu.web.client;

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
     * 添加请求过滤器，默认实现同类型过滤器只会被添加一次
     *
     * @param httpRequestProcessor 过滤器
     * @author weiba
     * @date 2024/3/6 17:07
     */
    void addRequestDataProcessor(IDataProcessor<HttpRequest> httpRequestProcessor);

    /**
     * 添加响应过滤器，默认实现同类型过滤器只会被添加一次
     *
     * @param httpResponseProcessor 过滤器
     * @author weiba
     * @date 2024/3/6 17:07
     */
    void addResponseDataProcessor(IDataProcessor<HttpResponse> httpResponseProcessor);
    
}
