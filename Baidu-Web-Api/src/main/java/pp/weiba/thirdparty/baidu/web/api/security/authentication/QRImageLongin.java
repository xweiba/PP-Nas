package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.api.netdisk.UrlConstants;
import pp.weiba.utils.JSONUtils;

import java.util.Date;

/**
 * 二维码扫码登录
 *
 * @author weiba
 * @date 2024/3/26 10:13
 */
@Log4j2
public class QRImageLongin {

    public static String getQRImageHttpUrl(LoginQRParams loginQRParams) {
        return StrUtil.format(UrlConstants.GET_QR_IMAGE, loginQRParams.getGid(), loginQRParams.getCallback(), loginQRParams.getTt(), loginQRParams.getLogPage(), loginQRParams.get_());
    }

    public static QrInfo getQRImageUrl(String getQRImageHttpUrlResponseBody) {
        String responseBody = getResponseBodyFormat(getQRImageHttpUrlResponseBody);
        return JSONUtils.toBean(responseBody, QrInfo.class);
    }


    public static String checkScanQRCallbackUrl(String channelId, String gid, String callback) {
        long tt = new Date().getTime();
        // 30s 超时，长轮训
        return StrUtil.format(UrlConstants.GET_CHECK_SCAN_QR_CALLBACK, channelId, gid, callback, tt, tt);
    }

    public static String qrLogInUrl(String bduss) {
        long tt = new Date().getTime();
        // time = Math.round((new Date).getTime() / 1e3),
        // o = a = (new Date).getTime();
        // alg = 'v3'
        // sig: h.encryption(i, r, e),
        // elapsed = (new Date).getTime() - o, 最后算，计算签名花费的时间


        return StrUtil.format(UrlConstants.GET_QR_LOGIN, bduss, tt, tt);
    }


    public static String getResponseBodyFormat(String getResponseBodyStr) {
        return getResponseBodyStr.substring(getResponseBodyStr.indexOf("{"), getResponseBodyStr.lastIndexOf(")"));
    }


    public static CheckLoginResponse buildCheckLoginResponse(String responseBodyStr) {
        JSONObject jsonObj = JSONUtils.toJSONObj(responseBodyStr);
        return new CheckLoginResponse()
                .setChannelId(jsonObj.getString("channel_id"))
                .setErrno(jsonObj.getInteger("errno"))
                .setChannelV(JSONUtils.toBean(jsonObj.getString("channel_v"), CheckLoginResponse.Channel.class))
                ;
    }
}
