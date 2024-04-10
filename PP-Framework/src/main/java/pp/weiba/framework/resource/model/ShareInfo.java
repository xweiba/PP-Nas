package pp.weiba.framework.resource.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

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

    private String shareName;

    private String link;

    private String password;

    private Date createTime;

    /* 过期时间 */
    private Date expireTime;

    private ShareStatus status = ShareStatus.NORMAL;

    private List<String> resourceIds;


}
