package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/*
{
    "drive_id": "23298650",
    "sbox_used_size": 47030506502,
    "sbox_real_used_size": 0,
    "sbox_total_size": 53687091200,
    "recommend_vip": "svip",
    "pin_setup": true,
    "locked": true,
    "insurance_enabled": false
}
* */

/**
 * 环境信息
 *
 * @author weiba
 * @date 2024/4/30 14:06
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SBoxInfo {

    @JSONField(name = "drive_id")
    private String driveId;
    @JSONField(name = "sbox_used_size")
    private Long sboxUsedSize;
    @JSONField(name = "sbox_real_used_size")
    private Integer sboxRealUsedSize;
    @JSONField(name = "sbox_total_size")
    private Long sboxTotalSize;
    @JSONField(name = "recommend_vip")
    private String recommendVip;
    @JSONField(name = "pin_setup")
    private Boolean pinSetup;
    @JSONField(name = "locked")
    private Boolean locked;
    @JSONField(name = "insurance_enabled")
    private Boolean insuranceEnabled;
}
