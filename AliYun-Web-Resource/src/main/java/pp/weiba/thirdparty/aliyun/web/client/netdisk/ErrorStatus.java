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
        statusCodeMap.put("DeviceSessionSignatureInvalid", "设备签名无效,请检查[X-Signature]请求头");
        statusCodeMap.put("AccessTokenInvalid", "AccessToken已失效,请检查[Authorization]请求头");
        statusCodeMap.put("SharelinkCreateExceedDailyLimit", "普通用户每天只能使用分享功能5次；会员用户和Lv.1及以上的达人用户，每天可使用分享次数1000次。超过上限后，将提示「今日分享次数已达上限」。 ");
        statusCodeMap.put("QuotaExhausted.Drive", "文件大小超出网盘容量");
        statusCodeMap.put("SignatureDoesNotMatch", "签名不匹配");
    }

    public static String getMessage(String statusCode) {
        String msg = statusCodeMap.get(statusCode);
        if (StrUtil.isBlank(msg)) {
            msg = "未知状态码：" + statusCode;
        }
        return msg;
    }
}
