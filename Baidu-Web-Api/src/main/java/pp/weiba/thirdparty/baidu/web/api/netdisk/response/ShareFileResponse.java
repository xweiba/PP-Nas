package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/11
 **/

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShareFileResponse extends ApiResponse {

    private String aheadmsg;
    private String createsharetipsLdlj;
    private Date ctime;
    private Integer expiredType;
    private Date expiretime;
    private Integer imagetype;
    private String link;
    private String newno;
    private Boolean premis;
    private Integer promptType;
    private String qrcodeurl;
    private String shareid;
    private String shorturl;
    private String showMsg;
    private String tailmsg;

}
