package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;

/**
 * 原始
 * {"deviceName":"Edge浏览器","modelName":"Windows网页版","pubKey":"044d21d7d12b9c9d5490df11a74b2b636c14eaaf27d1c15852c26a1d88a25a6bab8f3af5367e3ce95299c30748f87c10f98e463f0b43c25e29636ef892dbc2f4e6"}
 */

/**
 * 创建会话
 *
 * @author weiba
 * @date 2024/4/30 16:10
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSessionRequest {

    public CreateSessionRequest(String pubKey) {
        // 04 表示未加密的公钥
        this.pubKey = "04" + pubKey;
    }

    @JSONField(name = "deviceName")
    private String deviceName = UrlConstants.DEVICE_NAME;
    @JSONField(name = "modelName")
    private String modelName = "Windows网页版";
    @JSONField(name = "pubKey")
    private String pubKey;

}
