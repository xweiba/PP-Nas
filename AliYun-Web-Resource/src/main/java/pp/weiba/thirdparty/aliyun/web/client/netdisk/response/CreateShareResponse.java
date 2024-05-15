package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 创建分享返回
*
* @author weiba
* @date 2024/5/15 16:01
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateShareResponse {


    /**
     * category
     */
    @JSONField(name = "category")
    private String category;
    /**
     * popularity
     */
    @JSONField(name = "popularity")
    private Integer popularity;
    /**
     * shareId
     */
    @JSONField(name = "share_id")
    private String shareId;
    /**
     * shareMsg
     */
    @JSONField(name = "share_msg")
    private String shareMsg;
    /**
     * shareName
     */
    @JSONField(name = "share_name")
    private String shareName;
    /**
     * description
     */
    @JSONField(name = "description")
    private String description;
    /**
     * expiration
     */
    @JSONField(name = "expiration")
    private String expiration;
    /**
     * expired
     */
    @JSONField(name = "expired")
    private Boolean expired;
    /**
     * sharePwd
     */
    @JSONField(name = "share_pwd")
    private String sharePwd;
    /**
     * shareUrl
     */
    @JSONField(name = "share_url")
    private String shareUrl;
    /**
     * creator
     */
    @JSONField(name = "creator")
    private String creator;
    /**
     * driveId
     */
    @JSONField(name = "drive_id")
    private String driveId;
    /**
     * fileId
     */
    @JSONField(name = "file_id")
    private String fileId;
    /**
     * fileIdList
     */
    @JSONField(name = "file_id_list")
    private List<String> fileIdList;
    /**
     * previewCount
     */
    @JSONField(name = "preview_count")
    private Integer previewCount;
    /**
     * saveCount
     */
    @JSONField(name = "save_count")
    private Integer saveCount;
    /**
     * downloadCount
     */
    @JSONField(name = "download_count")
    private Integer downloadCount;
    /**
     * status
     */
    @JSONField(name = "status")
    private String status;
    /**
     * createdAt
     */
    @JSONField(name = "created_at")
    private String createdAt;
    /**
     * updatedAt
     */
    @JSONField(name = "updated_at")
    private String updatedAt;
    /**
     * firstFile
     */
    @JSONField(name = "first_file")
    private FirstFileResponse firstFile;
    /**
     * enableFileChangedNotify
     */
    @JSONField(name = "enable_file_changed_notify")
    private Boolean enableFileChangedNotify;
    /**
     * isPhotoCollection
     */
    @JSONField(name = "is_photo_collection")
    private Boolean isPhotoCollection;
    /**
     * shareSubtitle
     */
    @JSONField(name = "share_subtitle")
    private String shareSubtitle;
    /**
     * syncToHomepage
     */
    @JSONField(name = "sync_to_homepage")
    private Boolean syncToHomepage;
    /**
     * popularityStr
     */
    @JSONField(name = "popularity_str")
    private String popularityStr;
    /**
     * fullShareMsg
     */
    @JSONField(name = "full_share_msg")
    private String fullShareMsg;
    /**
     * shareTitle
     */
    @JSONField(name = "share_title")
    private String shareTitle;
    /**
     * displayName
     */
    @JSONField(name = "display_name")
    private String displayName;
}
