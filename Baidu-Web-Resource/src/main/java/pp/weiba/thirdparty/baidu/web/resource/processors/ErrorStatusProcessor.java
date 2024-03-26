package pp.weiba.thirdparty.baidu.web.resource.processors;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.HttpResponse;
import pp.weiba.framework.core.convert.IProcessor;
import pp.weiba.thirdparty.baidu.web.api.netdisk.ErrorStatus;

/**
 * 请求响应错误码处理器
 *
 * @author weiba
 * @date 2024/3/7 16:01
 */
@Log4j2
public class ErrorStatusProcessor implements IProcessor<HttpResponse> {

    @Override
    public HttpResponse process(HttpResponse request) {
        String body = request.getBody();
        if (StrUtil.isNotBlank(body) && !body.contains("<!DOCTYPE html>")) {
            Integer statusCode = null;
            if (body.contains("error_code")) {
                statusCode = getStatusCode(body, "error_code");
            } else if (body.contains("errno")) {
                statusCode = getStatusCode(body, "errno");
            }
            if (statusCode != null) {
                // 404 表示未找到资源
                if (statusCode != 0 && statusCode != 404) {
                    // 接口异常
                    log.debug("HttpResponse Body: {}", body);
                    throw new RuntimeException(ErrorStatus.getMessage(statusCode));
                }
            }
        }
        return request;
    }

    private int getStatusCode(String body, String key) {
        String errno = body.substring(body.indexOf(key) + key.length() + 2);
        errno = errno.substring(0, errno.indexOf(","));
        return Integer.valueOf(errno);
    }
}
