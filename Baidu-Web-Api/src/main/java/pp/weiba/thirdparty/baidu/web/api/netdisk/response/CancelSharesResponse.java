package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 百度批量取消分享接口Response
 * @date 2023/5/23 0:14
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class CancelSharesResponse extends ApiResponse {

    private String newno;
    private String show_msg;
}
