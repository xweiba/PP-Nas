package pp.weiba.framework.core.handler;

/**
 * 执行接口
 *
 * @author weiba
 * @date 2024/3/18 17:19
 */
public interface IExecutor<T, F> {

    /**
     * 执行方法
     *
     * @param params 输入
     * @return 输出
     * @author weiba
     * @date 2024/3/18 17:20
     */
    F execute(T params);

}
