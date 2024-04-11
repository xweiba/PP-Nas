package pp.weiba.thirdparty.baidu.web.client.netdisk.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
    @Data
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
