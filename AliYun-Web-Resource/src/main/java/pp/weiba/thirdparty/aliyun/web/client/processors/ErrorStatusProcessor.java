package pp.weiba.thirdparty.aliyun.web.client.processors;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IProcessor;
import pp.weiba.framework.net.client.model.HttpResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.ErrorStatus;
import pp.weiba.utils.JSONUtils;
import pp.weiba.utils.StringUtils;

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
        if (StrUtil.isNotBlank(body) && !body.contains("<!DOCTYPE html>") && (request.getStatusCode() != 200 && request.getStatusCode() != 201 && request.getStatusCode() != 409)) {
            String statusCode = String.valueOf(request.getStatusCode());
            if (body.contains("<?xml version=")) {
                statusCode = StringUtils.substring(body, "<Code>", "</Code>");
            } else if (JSONUtils.isStrJSONValid(body) && body.contains("code")) {
                statusCode = getStatusCode(body, "code");
            }

            String message = ErrorStatus.getMessage(statusCode);
            if (StrUtil.isNotBlank(statusCode) && StrUtil.isNotBlank(message)) {
                // 接口异常
                log.error("HttpResponse Body: {}", body);
                throw new RuntimeException(message);
            }
        }
        return request;
    }

    private String getStatusCode(String body, String key) {
        String errno = body.substring(body.indexOf(key) + key.length() + 3);
        errno = errno.substring(0, errno.indexOf("\""));
        return errno;
    }
}
