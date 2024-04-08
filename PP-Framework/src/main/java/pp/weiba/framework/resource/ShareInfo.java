package pp.weiba.framework.resource;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 分享信息
 *
 * @author weiba
 * @date 2024/4/8 14:19
 */
@Data
@Accessors(chain = true)
public class ShareInfo {

    private String shareId;

    private String link;

    private String password;

    private Date createTime;

    /* 过期时间 */
    private Date expireTime;


}
