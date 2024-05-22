package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.AliYunClientConstants;
import pp.weiba.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/*
{
    "limit": 20,
    "query": "name match \"test\"",
    "image_thumbnail_process": "image/resize,w_256/format,avif",
    "image_url_process": "image/resize,w_1920/format,avif",
    "video_thumbnail_process": "video/snapshot,t_120000,f_jpg,m_lfit,w_256,ar_auto,m_fast",
    "order_by": "updated_at DESC",
    "drive_id_list": [
        "13298650",
        "904891482"
    ]
}

图片 "query":"name match \"\" and category = \"image\""
视频 "query":"name match \"\" and category = \"video\""
文件夹 "query":"name match \"\" and type = \"folder\""
文档 "query":"name match \"\" and category = \"doc\""
图片 "query":"name match \"\" and category = \"audio\""
* */

/**
* 搜索
*
* @author weiba
* @date 2024/5/10 17:03
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchRequest {

    public SearchRequest(String keyword, String category) {
        this(keyword, category, null);
    }

    public SearchRequest(String keyword, String category, String marker) {
        this.keyword = keyword;
        this.category = category;
        this.marker = marker;
    }

    @JSONField(serialize=false)
    private String keyword = "";

    @JSONField(serialize=false)
    private String category = "";

    /**
     * limit
     */
    @JSONField(name = "limit")
    private Integer limit = 10;

    /**
     * marker nextMarker 从上个返回值从获取
     */
    @JSONField(name = "marker")
    private String marker;

    /**
     * query
     */
    @JSONField(name = "query")
    private String query;
    /**
     * imageThumbnailProcess
     */
    @JSONField(name = "image_thumbnail_process")
    private String imageThumbnailProcess = "image/resize,w_256/format,avif";
    /**
     * imageUrlProcess
     */
    @JSONField(name = "image_url_process")
    private String imageUrlProcess = "image/resize,w_1920/format,avif";
    /**
     * videoThumbnailProcess
     */
    @JSONField(name = "video_thumbnail_process")
    private String videoThumbnailProcess = "video/snapshot,t_120000,f_jpg,m_lfit,w_256,ar_auto,m_fast";
    /**
     * orderBy
     */
    @JSONField(name = "order_by")
    private String orderBy = "updated_at DESC";
    /**
     * driveIdList
     */
    @JSONField(name = "drive_id_list")
    private List<String> driveIdList = new ArrayList<String>(){{
        add(AliYunClientConstants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG);
        add(AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG);
    }};

    public String getQuery() {
        String keywordSql =  "name match \"{keyWord}\"";
        String categorySql =  "and category = \"{category}\"";
        String format = StringUtils.formatWith(keywordSql, "{keyWord}", keyword);
        if (StringUtils.isNotBlank(category)) {
            format = StringUtils.formatWith(categorySql, "{category}", category);
        }
        return format;
    }
}
