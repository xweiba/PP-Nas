package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * {"errno":0,"isElink":0,"newno":"","pwd":"hbr2","request_id":2772123190175288706,"shorturl":"mxYtDwtDE5LsKjLj8AoCTw","show_msg":""}
 *
 * @author xiaoweiba1028@gmail.com
 * @description 百度批量取消分享接口Response
 * @date 2023/5/23 0:14
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
@SuperBuilder
public class MyShareResponse {

    private int errno;
    private int isElink;
    private String newno;
    private String pwd;
    private long request_id;
    private String shorturl;
    private String show_msg;

}
