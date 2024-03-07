package pp.weiba.thirdparty.baidu.web.netdisk.processors;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.HttpResponse;
import pp.weiba.thirdparty.baidu.web.client.IDataProcessor;
import pp.weiba.thirdparty.baidu.web.netdisk.ErrorStatus;

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
        return 0;
    }
}
