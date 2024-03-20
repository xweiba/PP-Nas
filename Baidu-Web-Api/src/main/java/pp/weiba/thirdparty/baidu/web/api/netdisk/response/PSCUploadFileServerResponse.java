package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/9/22
 **/

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PSCUploadFileServerResponse {

    private String clientIp;
    private List<String> server;
    private String host;
    private Integer expire;
    private Long requestId;

}
