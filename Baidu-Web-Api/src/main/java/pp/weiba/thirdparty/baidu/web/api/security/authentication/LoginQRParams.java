package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 二维码参数
 * <p>
 * GET https://passport.baidu.com/v2/api/getqrcode?lp=pc&qrloginfrom=pc&gid=6DAFA44-71E9-48CB-9661-9A8420FFE6D3&callback=tangram_guid_1711359493755&apiver=v3&tt=1711419309679&tpl=netdisk&logPage=traceId%3Apc_loginv5_1711359494%2ClogPage%3Aloginv5&_=1711419309680
 * <p>
 * lp: pc
 * qrloginfrom: pc
 * gid: 6DAFA44-71E9-48CB-9661-9A8420FFE6D3
 * callback: tangram_guid_1711359493748
 * apiver: v3
 * tt: 1711416143209
 * tpl: netdisk
 * logPage: traceId:pc_loginv5_1711359494,logPage:loginv5
 * _: 1711416143210
 *
 * @author weiba
 * @date 2024/3/26 9:37
 */
@Data
@Accessors(chain = true)
public class LoginQRParams {

    private String lp = "pc";

    private String qrloginfrom = "pc";

    private String apiver = "v3";

    private String tpl = "netdisk";

    /* (new Date).getTime() */
    private Long tt;

    /* (js loginGuideRandom 生成)*/
    private String gid;

    /* tangram_guid + (new Date().getTime() ++ )
     * baidu.id.key = 'tangram_guid'
     * m = new Date().getTime()
     * var L = h.pop() || (baidu.id.key + "_" + (m++));
     * */
    private String callback;

    /*
        "traceId:" + (js loginTraceId 生成)  + ",logPage:loginv5"
         traceId:pc_loginv5_1711359494,logPage:loginv5
     */
    private String logPage;

    /* L = new Date().getTime() */
    private Long _;

}
