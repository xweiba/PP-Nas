package pp.weiba.framework.core.convert;

/**
 * 请求处理器
 *
 * @author weiba
 * @date 2024/3/7 10:25
 */
public interface IProcessor<T> {

    T process(T request);

}
