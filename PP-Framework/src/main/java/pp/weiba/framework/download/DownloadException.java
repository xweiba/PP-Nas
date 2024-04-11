package pp.weiba.framework.download;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.PPException;

/**
 * 下载异常
 *
 * @author weiba
 * @date 2024/4/11 16:35
 */
@Log4j2
public class DownloadException extends PPException {


    public DownloadException(String message) {
        super(message);
    }
}
