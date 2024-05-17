package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
* 
*
* @author weiba
* @date 2024/5/17 9:59
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetShareFileDetailRequest {

    public GetShareFileDetailRequest(String driveId, String shareId, String fileId) {
        this.driveId = driveId;
        this.shareId = shareId;
        this.fileId = fileId;
    }

    /**
     * driveId
     */
    @JSONField(name = "drive_id")
    private String driveId;
    /**
     * shareId
     */
    @JSONField(name = "share_id")
    private String shareId;
    /**
     * fileId
     */
    @JSONField(name = "file_id")
    private String fileId;
    /**
     * fields
     */
    @JSONField(name = "fields")
    private String fields = "*";
    /**
     * imageThumbnailProcess
     */
    @JSONField(name = "image_thumbnail_process")
    private String imageThumbnailProcess = "image/resize,w_1920/format,jpeg";
    /**
     * imageUrlProcess
     */
    @JSONField(name = "image_url_process")
    private String imageUrlProcess = "image/resize,w_375/format,jpeg";
    /**
     * videoThumbnailProcess
     */
    @JSONField(name = "video_thumbnail_process")
    private String videoThumbnailProcess = "video/snapshot,t_1000,f_jpg,ar_auto,w_375";
}
