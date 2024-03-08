package pp.weiba.thirdparty.baidu.web.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.AbstractHttpClientWrap;
import pp.weiba.thirdparty.baidu.web.client.IHttpClient;
import pp.weiba.thirdparty.baidu.web.netdisk.processors.AddDefaultHeaderProcessor;
import pp.weiba.thirdparty.baidu.web.netdisk.processors.AddHeaderCookieProcessor;
import pp.weiba.thirdparty.baidu.web.netdisk.processors.ErrorStatusProcessor;
import pp.weiba.thirdparty.baidu.web.netdisk.processors.UrlParameterCompletionProcessor;

/**
 * 百度网盘抽象客户端
 *
 * @author weiba
 * @date 2024/3/7 15:50
 */
@Log4j2
public class WebBaiduNetDiskHttpClient extends AbstractHttpClientWrap {

    public WebBaiduNetDiskHttpClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 添加默认处理器
     */
    protected void initDefaultProcessors() {
        // 全局参数补全处理
        addRequestDataProcessor(new UrlParameterCompletionProcessor());

        // 全局参数头处理
        addRequestDataProcessor(new AddDefaultHeaderProcessor());

        // 全局参数cookie处理
        addRequestDataProcessor(new AddHeaderCookieProcessor());

        // 接口异常处理
        addResponseDataProcessor(new ErrorStatusProcessor());
    }
}
