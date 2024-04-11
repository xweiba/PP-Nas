package pp.weiba.framework.net.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.PPException;

/**
 * HttpClient Api 适配异常
 *
 * @author weiba
 * @date 2024/4/11 10:58
 */
@Log4j2
public class HttpClientApiException extends PPException {


    public HttpClientApiException(String message) {
        super(message);
    }
}
