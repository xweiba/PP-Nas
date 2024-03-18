package pp.weiba.framework.core.handler;

/**
 * 责任链处理器
 *
 * @author weiba
 * @date 2024/3/18 15:16
 */
public interface IHandler<T> {

    /**
     * 获取下一个处理器
     *
     * @author weiba
     * @date 2024/3/18 15:17
     */
    IHandler<T> getNext();

    /**
     * 设置下一个处理器
     *
     * @param next 下一个处理器
     * @author weiba
     * @date 2024/3/18 15:17
     */
    void setNext(IHandler<T> next);

    /**
     * 执行当前处理器
     *
     * @param input 处理器输入
     * @return 处理器输出
     * @author weiba
     * @date 2024/3/18 15:17
     */
    T handle(T input);

}
