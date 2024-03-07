package pp.weiba.thirdparty.baidu.web.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.IHttpClient;
import pp.weiba.thirdparty.baidu.web.client.api.AbstractApiClient;
import pp.weiba.thirdparty.baidu.web.netdisk.processors.ErrorStatusProcessor;
import pp.weiba.thirdparty.baidu.web.netdisk.processors.UrlParameterCompletionProcessor;

/**
 * 百度网盘抽象客户端
 *
 * @author weiba
 * @date 2024/3/7 15:50
 */
@Log4j2
public abstract class AbstractBaiduNetDiskApiClient extends AbstractApiClient {

    public AbstractBaiduNetDiskApiClient(IHttpClient httpClient) {
        super(httpClient);
        // 全局参数补全处理
        httpClient.addRequestDataProcessor(new UrlParameterCompletionProcessor());
        // 接口异常处理
        httpClient.addResponseDataProcessor(new ErrorStatusProcessor());
    }
}
