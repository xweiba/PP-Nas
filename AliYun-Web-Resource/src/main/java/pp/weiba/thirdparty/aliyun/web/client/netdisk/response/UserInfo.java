package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/*

{
    "avatar": "https://img.aliyundrive.com/avatar/fbc2e8161f1043b8a8c98731505f92d0.jpeg",
    "email": "",
    "phone": "15377681760",
    "role": "user",
    "status": "enabled",
    "description": "",
    "punishments": null,
    "creator": "",
    "backup_drive_id": "18654654",
    "resource_drive_id": "321312333",
    "user_id": "0075833233234dd187c395ee3c7747b0",
    "domain_id": "bj29",
    "user_name": "153***760",
    "nick_name": "weiba",
    "default_drive_id": "18654654",
    "sbox_drive_id": null,
    "created_at": 1616472677512,
    "updated_at": 1650170668447,
    "user_data": {
        "back_up_config": {
            "手机备份": {
                "folder_id": "60596b9aff3978d6d262469f944562b098850600",
                "photo_folder_id": "60596b9ab868669aaec2452596038fc0a19fce6b",
                "sub_folder": {},
                "video_folder_id": "60596b9a4df9237af51f4923af04fc20f4db6a92"
            }
        }
    },
    "punish_flag": null,
    "default_location": "",
    "deny_change_password_by_self": false,
    "expire_at": null,
    "last_login_time": 1714380471830,
    "need_change_password_next_login": false,
    "phone_region": "",
    "vip_identity": "member",
    "creator_level": "Lv.0",
    "display_name": "weiba"
}


* */


/**
 * 个人信息
 *
 * @author weiba
 * @date 2024/4/30 13:58
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfo {

    @JSONField(name = "avatar")
    private String avatar;
    @JSONField(name = "email")
    private String email;
    @JSONField(name = "phone")
    private String phone;
    @JSONField(name = "role")
    private String role;
    @JSONField(name = "status")
    private String status;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "punishments")
    private Object punishments;
    @JSONField(name = "creator")
    private String creator;
    @JSONField(name = "backup_drive_id")
    private String backupDriveId;
    @JSONField(name = "resource_drive_id")
    private String resourceDriveId;
    @JSONField(name = "user_id")
    private String userId;
    @JSONField(name = "domain_id")
    private String domainId;
    @JSONField(name = "user_name")
    private String userName;
    @JSONField(name = "nick_name")
    private String nickName;
    @JSONField(name = "default_drive_id")
    private String defaultDriveId;
    @JSONField(name = "sbox_drive_id")
    private Object sboxDriveId;
    @JSONField(name = "created_at")
    private Long createdAt;
    @JSONField(name = "updated_at")
    private Long updatedAt;
    @JSONField(name = "user_data")
    private UserDataDTO userData;
    @JSONField(name = "punish_flag")
    private Object punishFlag;
    @JSONField(name = "default_location")
    private String defaultLocation;
    @JSONField(name = "deny_change_password_by_self")
    private Boolean denyChangePasswordBySelf;
    @JSONField(name = "expire_at")
    private Object expireAt;
    @JSONField(name = "last_login_time")
    private Long lastLoginTime;
    @JSONField(name = "need_change_password_next_login")
    private Boolean needChangePasswordNextLogin;
    @JSONField(name = "phone_region")
    private String phoneRegion;
    @JSONField(name = "vip_identity")
    private String vipIdentity;
    @JSONField(name = "creator_level")
    private String creatorLevel;
    @JSONField(name = "display_name")
    private String displayName;

    @NoArgsConstructor
    @Data
    public static class UserDataDTO {
        @JSONField(name = "back_up_config")
        private BackUpConfigDTO backUpConfig;

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
