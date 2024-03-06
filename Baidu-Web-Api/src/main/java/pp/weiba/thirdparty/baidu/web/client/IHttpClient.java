package pp.weiba.thirdparty.baidu.web.client;

import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.asynchttpclient.filter.RequestFilter;
import org.asynchttpclient.filter.ResponseFilter;

/**
 * Http客户端
 *
 * @author weiba
 * @date 2024/3/6 16:23
 */
public interface IHttpClient {

    /**
     * 发送请求方法
     *
     * @param request 请求参数
     * @return 请求结果
     * @author weiba
     * @date 2024/3/6 17:06
     */
    Response syncExecuteRequest(RequestBuilder request);

    /**
     * 添加请求过滤器，默认实现同类型过滤器只会被添加一次
     *
     * @param filter 过滤器
     * @author weiba
     * @date 2024/3/6 17:07
     */
    void addRequestFilter(RequestFilter filter);

    /**
     * 添加响应过滤器，默认实现同类型过滤器只会被添加一次
     *
     * @param filter 过滤器
     * @author weiba
     * @date 2024/3/6 17:07
     */
    void addResponseFilter(ResponseFilter filter);
    
}
