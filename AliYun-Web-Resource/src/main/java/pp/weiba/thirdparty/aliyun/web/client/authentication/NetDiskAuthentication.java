package pp.weiba.thirdparty.aliyun.web.client.authentication;

import lombok.Data;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.AliYunUtils;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.SignatureInfo;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SBoxInfo;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.UserInfo;

/**
 * 认证信息
 *
 * @author weiba
 * @date 2024/4/29 17:08
 */
@Data
@Accessors(chain = true)
public class NetDiskAuthentication {

    private String appId = AliYunUtils.APP_ID;

    private String authorization;

    // UUID
    private String xDeviceId;

    // 签名 与xDeviceId一起更新
    private String xSignature;

    private UserInfo userInfo;

    private SBoxInfo sBoxInfo;

    private SignatureInfo signatureInfo;

}
