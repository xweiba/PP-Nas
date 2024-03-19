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

    /**
     * 是否执行下一个处理器，用于缓存类的处理器，不往下执行了。
     *
     * @return 默认为true
     * @author weiba
     * @date 2024/3/19 11:44
     */
    default boolean canHandle(T input) {
        return true;
    }

}
