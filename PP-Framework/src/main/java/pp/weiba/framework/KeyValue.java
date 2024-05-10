
package pp.weiba.framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
 * 键值对对象
 *
 * @author weiba
 * @date 2024/3/5 16:01
 */
@Log4j2
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KeyValue {

    private String key;

    private String value;

}
