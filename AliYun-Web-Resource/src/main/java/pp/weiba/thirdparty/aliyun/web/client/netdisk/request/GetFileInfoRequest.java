package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.AliYunClientConstants;

/**
 * 获取文件信息
 *
 * @author weiba
 * @date 2024/4/30 11:05
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetFileInfoRequest {

    public GetFileInfoRequest(boolean isBackupDrive, String fileId) {
        this.driveId = isBackupDrive ? AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG : AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
        this.fileId = fileId;
    }

    public GetFileInfoRequest(String driveId, String fileId) {
        this.driveId = driveId;
        this.fileId = fileId;
    }

    @JSONField(name = "drive_id")
    private String driveId;
    @JSONField(name = "file_id")
    private String fileId;

    /* 下面的 只有需要预览图的才需要携带， 如图片，视频*/
    @JSONField(name = "image_thumbnail_process")
    private String imageThumbnailProcess;
    @JSONField(name = "image_url_process")
    private String imageUrlProcess;
    @JSONField(name = "video_thumbnail_process")
    private String videoThumbnailProcess;
    @JSONField(name = "url_expire_sec")
    private Integer urlExpireSec;

    public GetFileInfoRequest enablePreview() {
        this.imageThumbnailProcess = "image/resize,w_256/format,avif";
        this.imageUrlProcess = "image/resize,w_1920/format,avif";
        this.videoThumbnailProcess = "video/snapshot,t_120000,f_jpg,m_lfit,w_256,ar_auto,m_fast";
        this.urlExpireSec = 1600;
        return this;
    }
}
