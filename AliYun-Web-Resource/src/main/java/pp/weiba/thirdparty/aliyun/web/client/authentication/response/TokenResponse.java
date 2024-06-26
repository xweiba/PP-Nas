package pp.weiba.thirdparty.aliyun.web.client.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * {
 * "default_sbox_drive_id": "232",
 * "role": "user",
 * "device_id": "753c5d8e6b",
 * "user_name": "1",
 * "need_link": false,
 * "expire_time": "2024-04-30T11:29:04Z",
 * "pin_setup": true,
 * "need_rp_verify": false,
 * "avatar": "https://img.aliyundrive.com/avatar/.jpeg",
 * "user_data": {
 * "DingDingRobotUrl": "https://oapi.dingtalk.com/robot/send?access_token=0bec",
 * "EncourageDesc": "内测期间有效反馈前10名用户将获得终身免费会员",
 * "FeedBackSwitch": true,
 * "FollowingDesc": "34848372",
 * "back_up_config": {
 * "手机备份": {
 * "folder_id": "60596b9aff3978d6d26",
 * "photo_folder_id": "60596b9ab868669aaec2452596",
 * "sub_folder": {},
 * "video_folder_id": "60596b9a4d23af04fc20f4db6a92"
 * }
 * },
 * "ding_ding_robot_url": "https://oapi.dingtalk.com/robot/send?access_token=0b4a936d0e98c08608cd99f693393c18fa905aa0868215485a28497501916fec",
 * "encourage_desc": "内测期间有效反馈前10名用户将获得终身免费会员",
 * "feed_back_switch": true,
 * "following_desc": "34"
 * },
 * "token_type": "Bearer",
 * "access_token": "eyJIkpXVCJ9",
 * "default_drive_id": "544",
 * "domain_id": "bj29",
 * "path_status": "enabled",
 * "refresh_token": "",
 * "is_first_login": false,
 * "hlogin_url": "https://passport.aliyundrive.com/unity_login_dispatcher.htm?tokenType=aliyun_drive&token=CNzgyYzNjNDFjYTk2MTc5MTViNzlkMjBkM2E",
 * "user_id": "",
 * "nick_name": "",
 * "exist_link": [],
 * "state": "",
 * "expires_in": 7200,
 * "status": "enabled"
 * }
 */

/**
 * 刷新Token信息
 *
 * @author weiba
 * @date 2024/4/30 17:32
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenResponse {

    @JSONField(name = "default_sbox_drive_id")
    private String defaultSboxDriveId;
    @JSONField(name = "role")
    private String role;
    @JSONField(name = "device_id")
    private String deviceId;
    @JSONField(name = "user_name")
    private String userName;
    @JSONField(name = "need_link")
    private Boolean needLink;
    @JSONField(name = "expire_time")
    private String expireTime;
    @JSONField(name = "pin_setup")
    private Boolean pinSetup;
    @JSONField(name = "need_rp_verify")
    private Boolean needRpVerify;
    @JSONField(name = "avatar")
    private String avatar;
    @JSONField(name = "user_data")
    private UserDataDTO userData;
    @JSONField(name = "token_type")
    private String tokenType;
    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "default_drive_id")
    private String defaultDriveId;
    @JSONField(name = "domain_id")
    private String domainId;
    @JSONField(name = "path_status")
    private String pathStatus;
    @JSONField(name = "refresh_token")
    private String refreshToken;
    @JSONField(name = "is_first_login")
    private Boolean isFirstLogin;
    @JSONField(name = "hlogin_url")
    private String hloginUrl;
    @JSONField(name = "user_id")
    private String userId;
    @JSONField(name = "nick_name")
    private String nickName;
    @JSONField(name = "exist_link")
    private List<?> existLink;
    @JSONField(name = "state")
    private String state;
    @JSONField(name = "expires_in")
    private Integer expiresIn;
    @JSONField(name = "status")
    private String status;

    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class UserDataDTO {
        @JSONField(name = "DingDingRobotUrl")
        private String dingDingRobotUrl;
        @JSONField(name = "EncourageDesc")
        private String encourageDesc;
        @JSONField(name = "FeedBackSwitch")
        private Boolean feedBackSwitch;
        @JSONField(name = "FollowingDesc")
        private String followingDesc;
        @JSONField(name = "back_up_config")
        private BackUpConfigDTO backUpConfig;
        @JSONField(name = "ding_ding_robot_url")
        private String ding_ding_robot_url;
        @JSONField(name = "encourage_desc")
        private String encourage_desc;
        @JSONField(name = "feed_back_switch")
        private Boolean feed_back_switch;
        @JSONField(name = "following_desc")
        private String following_desc;

        @NoArgsConstructor
        @Data
        public static class BackUpConfigDTO {
            @JSONField(name = "手机备份")
            private 手机备份DTO 手机备份;

            @NoArgsConstructor
            @Data
            public static class 手机备份DTO {
                @JSONField(name = "folder_id")
                private String folderId;
                @JSONField(name = "photo_folder_id")
                private String photoFolderId;
                @JSONField(name = "sub_folder")
                private SubFolderDTO subFolder;
                @JSONField(name = "video_folder_id")
                private String videoFolderId;

                @NoArgsConstructor
                @Data
                public static class SubFolderDTO {
                }
            }
        }
    }
}
