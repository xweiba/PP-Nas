package pp.weiba.thirdparty.baidu.web.client.security.authentication;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.netdisk.UrlConstants;
import pp.weiba.thirdparty.baidu.web.client.netdisk.utils.BaiduNetDiskWebMoonShadV3Script;
import pp.weiba.thirdparty.baidu.web.client.netdisk.utils.BaiduNetDiskWebScript;
import pp.weiba.utils.JSONUtils;

import java.util.Date;

/**
 * 百度 WEB 二维码扫码登录
 *
 * @author weiba
 * @date 2024/3/26 10:13
 */
@Log4j2
public class WebQRScanLonginApiClient {

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

    public static String qrLogInUrl(LonginParams params) {
        return StrUtil.format(UrlConstants.GET_QR_LOGIN, params.getCallback(), params.getBduss(), params.getV(), params.getTt(), params.getTime(), params.getSig(), params.getShaOne(), params.getElapsed());
    }

    public static LonginParams buildQRLoginParams(String bduss) {
        /*
        签名参数：
        * {"bduss":"52daaa4c2a8651ef61ef0bcfdbf873ed","u":"https%3A%2F%2Fpan.baidu.com%2Fdisk%2Fmain%3Fredirecturl%3Dhttps%253A%252F%252Fpan.baidu.com%252Fdisk%252Fmain%253Fredirecturl%253Dhttps%25253A%25252F%25252Fpan.baidu.com%25252Fdisk%25252Fmain%25253Fredirecturl%25253Dhttps%2525253A%2525252F%2525252Fpan.baidu.com%2525252Fdisk%2525252Fmain%2525253Ffrom%2525253DhomeFlow%25252526_at_%2525253D1711434566250%25252523%2525252Findex%2525253Fcategory%2525253Dall%252526_at_%25253D1711438534882%252523%25252Findex%25253Fcategory%25253Dall%2526_at_%253D1711502742473%2523%252Findex%253Fcategory%253Dall%26_at_%3D1711504384808%23%2Findex%3Fcategory%3Dall","loginVersion":"v4","qrcode":1,"tpl":"netdisk","maskId":"","fileId":"","apiver":"v3","tt":1711506136888,"traceid":""}
        *
        * */

        LonginParams longinParams = new LonginParams().setBduss(bduss);
        String sigAndShaOneStr = BaiduNetDiskWebMoonShadV3Script.O0OOO0(JSONUtils.toJsonStr(longinParams));
        LonginParams sigParams = JSONUtils.toBean(sigAndShaOneStr, LonginParams.class);
        String callback = BaiduNetDiskWebScript.getUniqueId("bd__cbs__");
        sigParams.setCallback(callback);
        sigParams.setBduss(bduss);
        return sigParams;
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
