package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度网盘 Response StatusCode
 *
 * @author weiba
 * @date 2024/3/7 13:43
 */
public class ErrorStatus {
    private static final Map<String, String> statusCodeMap;

    static {
        statusCodeMap = new HashMap<>();
        statusCodeMap.put("BadRequest", "请求错误，请检查参数是否正常");
    }

    public static String getMessage(String statusCode) {
        String msg = statusCodeMap.get(statusCode);
        if (StrUtil.isBlank(msg)) {
            msg = "未知状态码：" + statusCode;
        }
        return msg;
    }
}
