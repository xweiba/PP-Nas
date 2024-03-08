package pp.weiba.framework.convert;

/**
 * 请求处理器
 *
 * @author weiba
 * @date 2024/3/7 10:25
 */
public interface IDataProcessor<T> {

    T process(T request);

}