package pp.weiba.framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
 * 资源签名信息
 *
 * @author weiba
 * @date 2024/3/5 15:30
 */
@Log4j2
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Digest {

    private DigestType type;

    private String value;

}
