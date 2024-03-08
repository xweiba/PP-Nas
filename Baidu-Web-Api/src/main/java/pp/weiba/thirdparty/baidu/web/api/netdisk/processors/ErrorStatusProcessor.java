package pp.weiba.thirdparty.baidu.web.api.netdisk.processors;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.api.client.HttpResponse;
import pp.weiba.thirdparty.baidu.web.api.client.IDataProcessor;
import pp.weiba.thirdparty.baidu.web.api.netdisk.ErrorStatus;

/**
 * 请求响应错误码处理器
 *
 * @author weiba
 * @date 2024/3/7 16:01
 */
@Log4j2
public class ErrorStatusProcessor implements IDataProcessor<HttpResponse> {

    @Override
    public HttpResponse process(HttpResponse request) {
        String body = request.getBody();
        if (StrUtil.isNotBlank(body) && body.contains("errno")) {
            int statusCode = getStatusCode(body);
            if (statusCode != 0) {
                // 接口异常
                throw new RuntimeException(ErrorStatus.getMessage(statusCode));
            }
        }
        return request;
    }

    private int getStatusCode(String body) {
        String errno = body.substring(body.indexOf("errno") + 7);
        errno = errno.substring(0, errno.indexOf(","));
        return Integer.valueOf(errno);
    }
}
