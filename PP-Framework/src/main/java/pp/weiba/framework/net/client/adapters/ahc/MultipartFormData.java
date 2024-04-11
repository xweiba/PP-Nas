package pp.weiba.framework.net.client.adapters.ahc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
 * @author weiba
 * @date 2024/3/22 15:37
 */
@Log4j2
@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class MultipartFormData {

    private byte[] boundary;

    private String contentType;

}
