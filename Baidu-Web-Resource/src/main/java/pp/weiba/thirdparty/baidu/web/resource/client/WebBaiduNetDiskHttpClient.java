package pp.weiba.thirdparty.baidu.web.resource.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.*;
import pp.weiba.thirdparty.baidu.web.resource.processors.AddDefaultHeaderProcessor;
import pp.weiba.thirdparty.baidu.web.resource.processors.ErrorStatusProcessor;
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
    protected void initDefaultProcessors() {
        // 全局参数补全处理
        addRequestHandler(new RequestHandler(new UrlParameterCompletionProcessor(authentication)));

        // 全局参数头处理
        addRequestHandler(new RequestHandler(new AddDefaultHeaderProcessor()));

        // 接口异常处理
        addResponseHandler(new ResponseHandler(new ErrorStatusProcessor()));

    }
}
