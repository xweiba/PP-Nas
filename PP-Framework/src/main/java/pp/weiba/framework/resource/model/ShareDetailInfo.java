package pp.weiba.framework.resource.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分享详细信息
 *
 * @author weiba
 * @date 2024/4/8 14:19
 */
@Data
@Accessors(chain = true)
public class ShareDetailInfo {

    private String shareId;

    // 当前登录者标识
    private String userId;

    private String userName;

    private String photoUrl;

    // 分享者标识
    private String shareUserId;

    private String shareUserName;

    private String sharePhotoUrl;

    // 分享文件
    private List<ResourceInfo> resourceInfos;

}
