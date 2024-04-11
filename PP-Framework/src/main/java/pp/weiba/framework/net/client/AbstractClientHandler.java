package pp.weiba.framework.net.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IProcessor;
import pp.weiba.framework.core.handler.AbstractHandler;

/**
 * 客户端处理器
 *
 * @author weiba
 * @date 2024/3/18 16:06
 */
@Log4j2
public abstract class AbstractClientHandler<T> extends AbstractHandler<T> {

    private final IProcessor<T> dataProcessor;

    public AbstractClientHandler(IProcessor<T> dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    @Override
    protected T process(T input) {
        return dataProcessor.process(input);
    }

}
