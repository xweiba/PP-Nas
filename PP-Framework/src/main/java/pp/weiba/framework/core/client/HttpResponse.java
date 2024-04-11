package pp.weiba.framework.core.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

/**
 * Http响应信息
 *
 * @author weiba
 * @date 2024/3/7 10:21
 */
@EqualsAndHashCode(callSuper = true)
@Log4j2
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HttpResponse extends AuthInfo<HttpResponse> {

    private int statusCode;

    private String statusText;

    private String body;

    private String contentType;

    // 构建参数, 不参与请求
    private Map<String, Object> buildParams;

}
