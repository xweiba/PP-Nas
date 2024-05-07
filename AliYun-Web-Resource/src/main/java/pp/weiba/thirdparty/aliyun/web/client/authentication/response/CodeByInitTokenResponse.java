package pp.weiba.thirdparty.aliyun.web.client.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.utils.StringUtils;

/**
 * @author weiba
 * @date 2024/5/7 15:59
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CodeByInitTokenResponse {


    /**
     * gotoX
     */
    @JSONField(name = "goto")
    private String gotoX;


    private String code;

    public String getCode() {
        if (StringUtils.isNotBlank(this.gotoX) && StringUtils.isBlank(this.code)) {
            this.code = StringUtils.substring(this.gotoX, "code=", "&");
        }
        return this.code;
    }
}
