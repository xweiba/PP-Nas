package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 登录效验信息
 * https://passport.baidu.com/v3/login/main/qrbdusslogin?v=1711502766541
 * <p>
 * {
 * "bduss": "528c984c73f67b8688d9c1504456c869",
 * "u": "https%3A%2F%2Fpan.baidu.com%2Fdisk%2Fmain%3Fredirecturl%3Dhttps%253A%252F%252Fpan.baidu.com%252Fdisk%252Fmain%253Fredirecturl%253Dhttps%25253A%25252F%25252Fpan.baidu.com%25252Fdisk%25252Fmain%25253Ffrom%25253DhomeFlow%252526_at_%25253D1711434566250%252523%25252Findex%25253Fcategory%25253Dall%2526_at_%253D1711438534882%2523%252Findex%253Fcategory%253Dall%26_at_%3D1711502742473%23%2Findex%3Fcategory%3Dall",
 * "loginVersion": "v4",
 * "qrcode": 1,
 * "tpl": "netdisk",
 * "maskId": "",
 * "fileId": "",
 * "apiver": "v3",
 * "tt": 1711502774162,
 * "traceid": ""
 * }
 *
 * @author weiba
 * @date 2024/3/27 9:26
 */
@Data
@Accessors(chain = true)
public class LonginParams {

    @JSONField(name = "bduss")
    private String bduss;

    @JSONField(name = "errno")
    private String u = "https%3A%2F%2Fpan.baidu.com%2Fdisk%2Fmain%3Fredirecturl%3Dhttps%253A%252F%252Fpan.baidu.com%252Fdisk%252Fmain%253Fredirecturl%253Dhttps%25253A%25252F%25252Fpan.baidu.com%25252Fdisk%25252Fmain%25253Ffrom%25253DhomeFlow%252526_at_%25253D1711434566250%252523%25252Findex%25253Fcategory%25253Dall%2526_at_%253D1711438534882%2523%252Findex%253Fcategory%253Dall%26_at_%3D1711502742473%23%2Findex%3Fcategory%3Dall";

    @JSONField(name = "loginVersion")
    private String loginVersion = "v4";

    @JSONField(name = "qrcode")
    private int qrcode = 1;

    @JSONField(name = "netdisk")
    private String tpl = "netdisk";

    @JSONField(name = "maskId")
    private String maskId;

    @JSONField(name = "fileId")
    private String fileId;

    @JSONField(name = "apiver")
    private String apiver = "v3";

    @JSONField(name = "tt")
    private long tt = new Date().getTime();

    @JSONField(name = "traceid")
    private String traceid;

    @JSONField(name = "v")
    private long v = new Date().getTime();

    @JSONField(name = "time")
    private long time;

    @JSONField(name = "alg")
    private String alg;

    @JSONField(name = "sig")
    private String sig;

    @JSONField(name = "elapsed")
    private int elapsed;

    @JSONField(name = "shaOne")
    private String shaOne;

    /* 可以不计算，没有效验，计算端的各种属性的 */
    @JSONField(name = "rinfo")
    private String rinfo = "{\"fuid\":\"\"}";

    /*
    e = 'bd__cbs__'
    e + Math.floor(2147483648 * Math.random()).toString(36) */
    @JSONField(name = "callback")
    private String callback;


}
