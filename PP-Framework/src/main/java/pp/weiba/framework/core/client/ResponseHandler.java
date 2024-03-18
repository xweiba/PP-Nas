package pp.weiba.framework.core.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IProcessor;

/**
 * 响应处理器
 *
 * @author weiba
 * @date 2024/3/18 16:04
 */
@Log4j2
public class ResponseHandler extends AbstractClientHandler<HttpResponse> {

    public ResponseHandler(IProcessor<HttpResponse> dataProcessor) {
        super(dataProcessor);
    }

}
