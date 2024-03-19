package pp.weiba.framework.core.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
 * 执行处理器参数
 *
 * @author weiba
 * @date 2024/3/19 10:19
 */
@Log4j2
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExecutorParams<T, F> {
    private T input;
    private F output;
}
