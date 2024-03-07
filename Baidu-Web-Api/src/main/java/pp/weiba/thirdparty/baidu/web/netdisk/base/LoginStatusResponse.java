package pp.weiba.thirdparty.baidu.web.netdisk.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.baidu.web.netdisk.ApiResponse;

/**
 * 登录状态信息
 *
 * @author weiba
 * @date 2024/3/7 17:36
 */
@Accessors(chain = true)
@Getter
@Setter
public class LoginStatusResponse extends ApiResponse {

    private String showMsg;

    private String newno;

    private LoginInfo loginInfo;

    @Accessors(chain = true)
    @Getter
    @Setter
    public static class LoginInfo {
        private String bdstoken;
        private String photoUrl;
        private Integer uk;
        private String ukStr;
        private String username;
        private String vipIdentity;
        private Integer vipLevel;
        private Integer vipPoint;
        private String vipType;
    }
}
