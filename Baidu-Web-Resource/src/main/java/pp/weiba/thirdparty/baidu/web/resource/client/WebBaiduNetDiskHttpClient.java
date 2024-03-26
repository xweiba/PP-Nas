package pp.weiba.thirdparty.baidu.web.resource.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.*;
import pp.weiba.thirdparty.baidu.web.resource.processors.AddDefaultHeaderProcessor;
import pp.weiba.thirdparty.baidu.web.resource.processors.ErrorStatusProcessor;
import pp.weiba.thirdparty.baidu.web.resource.processors.ParameterCompletionProcessor;
import pp.weiba.thirdparty.baidu.web.resource.processors.UrlParameterCompletionProcessor;

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

    /**
     * 添加默认处理器
     */
    protected void initHandlers() {
        // 全局参数补全处理
        addRequestHandler(new RequestHandler(new UrlParameterCompletionProcessor(authentication)));
        addRequestHandler(new RequestHandler(new ParameterCompletionProcessor(authentication)));

        // 全局参数头处理
        addRequestHandler(new RequestHandler(new AddDefaultHeaderProcessor()));

        // 接口响应错误码处理
        addResponseHandler(new ResponseHandler(new ErrorStatusProcessor()));

        // 接口添加限流，注意，限流必须最后再加进去，保证是限制实际的请求
        addExecuteHandler(new RateLimiterExecuteHandler(1.0));

    }
}
