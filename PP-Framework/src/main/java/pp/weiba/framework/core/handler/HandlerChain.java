package pp.weiba.framework.core.handler;

import lombok.extern.log4j.Log4j2;

/**
 * 责任链处理器抽象类
 *
 * @author weiba
 * @date 2024/3/18 15:17
 */
@Log4j2
public abstract class HandlerChain<T> implements IHandlerChain<T> {

    private IHandlerChain<T> next;

    @Override
    public IHandlerChain<T> getNext() {
        return this.next;
    }

    @Override
    public void setNext(IHandlerChain<T> next) {
        this.next = next;
    }

    @Override
    public T processHandle(T input) {
        T result = process(input);
        if (this.canHandle(result) && getNext() != null) {
            return getNext().processHandle(result);
        }
        return result;
    }

    protected abstract T process(T input);

}
