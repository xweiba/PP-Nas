package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/*
获取个人信息
https://api.aliyundrive.com/v2/databox/get_personal_info
{
    "personal_rights_info": {
        "spu_id": "non-vip",
        "name": "普通用户",
        "is_expires": false,
        "privileges": [
            {
                "feature_id": "download",
                "feature_attr_id": "speed_limit",
                "quota": -1
            },
            {
                "feature_id": "drive",
                "feature_attr_id": "size_limit",
                "quota": 107374182400
            },
            {
                "feature_id": "resource_drive_size_limit",
                "feature_attr_id": "",
                "quota": 0
            },
            {
                "feature_id": "safe_box",
                "feature_attr_id": "size_limit",
                "quota": 53687091200
            },
            {
                "feature_id": "share_link_file_count_limit",
                "feature_attr_id": "",
                "quota": 200
            },
            {
                "feature_id": "upload",
                "feature_attr_id": "size_limit",
                "quota": 2199023255552
            },
            {
                "feature_id": "video",
                "feature_attr_id": "backup",
                "quota": 1
            },
            {
                "feature_id": "video",
                "feature_attr_id": "clarity_limit",
                "quota": 3
            }
        ]
    },
    "personal_space_info": {
        "used_size": 856942784072,
        "total_size": 863445712896
    }
}
* */

/**
 * 个人信息
 *
 * @author weiba
 * @date 2024/4/29 16:49
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class PersonalInfoResponse {


    private PersonalRightsInfoDTO personalRightsInfo;
    private PersonalSpaceInfoDTO personalSpaceInfo;

    @NoArgsConstructor
    @Data
    public static class PersonalRightsInfoDTO {
        private String spuId;
        private String name;
        private Boolean isExpires;
        private List<PrivilegesDTO> privileges;

        @NoArgsConstructor
        @Data
        public static class PrivilegesDTO {
            private String featureId;
            private String featureAttrId;
            private Integer quota;
        }
    }

    @NoArgsConstructor
    @Data
    public static class PersonalSpaceInfoDTO {
        private Long usedSize;
        private Long totalSize;
    }
}
