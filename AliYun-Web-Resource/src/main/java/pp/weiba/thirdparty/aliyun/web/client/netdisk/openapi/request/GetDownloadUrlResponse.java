package pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi.request;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import lombok.experimental.Accessors;

/**
* 
*
* @author weiba
* @date 2024/5/24 13:42
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetDownloadUrlResponse {

    @JSONField(name = "url")
    private String url;

    @JSONField(name = "expiration")
    private String expiration;

    @JSONField(name = "method")
    private String method;
    
}
