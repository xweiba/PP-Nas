package pp.weiba.thirdparty.baidu.web.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.net.client.AbstractHttpClientWrap;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.IHttpClientAuthentication;
import pp.weiba.framework.net.client.model.RequestHandler;
import pp.weiba.framework.net.client.model.ResponseHandler;
import pp.weiba.thirdparty.baidu.web.client.processors.AddDefaultHeaderProcessor;
import pp.weiba.thirdparty.baidu.web.client.processors.ErrorStatusProcessor;
import pp.weiba.thirdparty.baidu.web.client.processors.ParameterCompletionProcessor;
import pp.weiba.thirdparty.baidu.web.client.processors.UrlParameterCompletionProcessor;

/**
 * 百度网盘抽象客户端
 *
 * @author weiba
 * @date 2024/3/7 15:50
 */
@Log4j2
public class WebBaiduNetDiskHttpClient extends AbstractHttpClientWrap {

    public WebBaiduNetDiskHttpClient(IHttpClient httpClient, IHttpClientAuthentication authentication) {
        super(httpClient, authentication);
    }

    @Override
    protected void initRequestHandlers() {
        // 全局参数补全处理
        addRequestHandler(new RequestHandler(new UrlParameterCompletionProcessor(authentication)));
        addRequestHandler(new RequestHandler(new ParameterCompletionProcessor(authentication)));

        // 全局参数头处理
        addRequestHandler(new RequestHandler(new AddDefaultHeaderProcessor()));
    }

    @Override
    protected void initResponseHandlers() {
        // 接口响应错误码处理
        addResponseHandler(new ResponseHandler(new ErrorStatusProcessor()));
    }

    @Override
    protected void initExecuteHandlers() {
        // 接口添加限流，注意，限流必须最后再加进去，保证是限制实际的请求
        addExecuteHandler(new RateLimiterExecuteHandler<>(1.0));
    }
}