package pp.weiba.thirdparty.baidu.web.client;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.handler.AbstractHandler;

/**
 * 接口限流器
 *
 * @author weiba
 * @date 2024/3/19 15:37
 */
@Log4j2
public class RateLimiterExecuteHandler<T> extends AbstractHandler<T> {

    private final RateLimiter rateLimiter;


    public RateLimiterExecuteHandler(double permitsPerSecond) {
        this.rateLimiter = RateLimiter.create(permitsPerSecond);
    }

    @Override
    protected T process(T input) {
        acquire();
        return doProcess(input);
    }

    private T doProcess(T input) {
        return input;
    }

    private void acquire() {
        rateLimiter.acquire();
    }
}
