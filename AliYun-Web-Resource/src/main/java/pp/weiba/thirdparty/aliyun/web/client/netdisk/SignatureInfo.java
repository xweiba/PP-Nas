package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 签名信息
 *
 * @author weiba
 * @date 2024/4/30 16:48
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SignatureInfo {

    private String privateKey;

    private String publicKey;

    private String appId;

    private String userId;

    private String xDeviceId;

    private Integer nonce;

}
