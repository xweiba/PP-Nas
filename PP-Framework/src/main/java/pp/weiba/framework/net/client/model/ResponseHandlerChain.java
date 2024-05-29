package pp.weiba.framework.net.client.model;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IProcessor;
import pp.weiba.framework.net.client.AbstractClientHandlerChain;

/**
 * 响应处理器
 *
 * @author weiba
 * @date 2024/3/18 16:04
 */
@Log4j2
public class ResponseHandlerChain extends AbstractClientHandlerChain<HttpResponse> {

    public ResponseHandlerChain(IProcessor<HttpResponse> dataProcessor) {
        super(dataProcessor);
    }

}
