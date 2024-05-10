package pp.weiba.thirdparty.aliyun.web.client.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/*
{
    "url": "https://passport.aliyundrive.com/iv/remote/h5/request.htm?havana_iv_token=CN-SPLIT-AQigvcWXrUAQ2AQiDWhhdmFuYV9hcHBfaXYyAQE42qfEhPYxQAFKEAUgkf75E2qc7C5WIvaXQ9KEybjAg7oSjsvgIPwXeDwF-zJjIw"
}
* */
/**
* 
*
* @author weiba
* @date 2024/5/10 11:43
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IVResponse {


    /**
     * url
     */
    @JSONField(name = "url")
    private String url;
}
