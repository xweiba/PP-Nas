package pp.weiba.framework.convert;

/**
 * 类型转换处理器
 *
 * @author weiba
 * @date 2024/3/7 14:34
 */
public interface ITypeReferenceProcessor<F> {

    <T> T process(F data, TypeReference<T> typeReference);

}
