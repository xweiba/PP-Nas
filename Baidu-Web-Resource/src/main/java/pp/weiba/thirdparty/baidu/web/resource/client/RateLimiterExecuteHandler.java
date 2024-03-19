package pp.weiba.thirdparty.baidu.web.resource.client;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.HttpRequest;
import pp.weiba.framework.core.client.HttpResponse;
import pp.weiba.framework.core.handler.BaseHandler;
import pp.weiba.framework.core.handler.ExecutorParams;

/**
 * 接口限流器
 *
 * @author weiba
 * @date 2024/3/19 15:37
 */
@Log4j2
public class RateLimiterExecuteHandler extends BaseHandler<ExecutorParams<HttpRequest, HttpResponse>> {

    private final RateLimiter rateLimiter;


    public RateLimiterExecuteHandler(double permitsPerSecond) {
        this.rateLimiter = RateLimiter.create(permitsPerSecond);
    }

    @Override
    protected ExecutorParams<HttpRequest, HttpResponse> process(ExecutorParams<HttpRequest, HttpResponse> input) {
        rateLimiter.acquire();
        return input;
    }
}
