package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
* 获取分享的文件列表
*
* @author weiba
* @date 2024/5/17 9:50
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetShareFilesRequest {

    public GetShareFilesRequest(String shareId) {
        this.shareId = shareId;
    }

    /**
     * shareId
     */
    @JSONField(name = "share_id")
    private String shareId;
    /**
     * parentFileId
     */
    @JSONField(name = "parent_file_id")
    private String parentFileId = "root";
    /**
     * limit
     */
    @JSONField(name = "limit")
    private Integer limit = 20;
    /**
     * imageThumbnailProcess
     */
    @JSONField(name = "image_thumbnail_process")
    private String imageThumbnailProcess = "image/resize,w_256/format,jpeg";
    /**
     * imageUrlProcess
     */
    @JSONField(name = "image_url_process")
    private String imageUrlProcess = "image/resize,w_1920/format,jpeg/interlace,1";
    /**
     * videoThumbnailProcess
     */
    @JSONField(name = "video_thumbnail_process")
    private String videoThumbnailProcess = "video/snapshot,t_1000,f_jpg,ar_auto,w_256";
    /**
     * orderBy
     */
    @JSONField(name = "order_by")
    private String orderBy = "name";
    /**
     * orderDirection
     */
    @JSONField(name = "order_direction")
    private String orderDirection = "DESC";

    @JSONField(name = "marker")
    private String marker;
}
