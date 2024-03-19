package pp.weiba.framework.core.handler;

import lombok.extern.log4j.Log4j2;

/**
 * 责任链处理器抽象类
 *
 * @author weiba
 * @date 2024/3/18 15:17
 */
@Log4j2
public abstract class BaseHandler<T> implements IHandler<T> {

    private IHandler<T> next;

    @Override
    public IHandler<T> getNext() {
        return this.next;
    }

    @Override
    public void setNext(IHandler<T> next) {
        this.next = next;
    }

    @Override
    public T handle(T input) {
        T result = process(input);
        if (this.canHandle(result) && next != null) {
            return next.handle(result);
        }
        return result;
    }

    protected abstract T process(T input);

}
