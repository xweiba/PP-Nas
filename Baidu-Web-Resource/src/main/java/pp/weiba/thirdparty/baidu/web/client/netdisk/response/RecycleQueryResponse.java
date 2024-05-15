package pp.weiba.thirdparty.baidu.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/11
 **/

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecycleQueryResponse {

    private Integer errno;
    private List<ListBO> list;
    private Long requestId;
    private Integer timestamp;

    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class ListBO {
        @JSONField(name = "category")
        private Integer category;
        @JSONField(name = "delete_type")
        private Integer deleteType;
        @JSONField(name = "fs_id")
        private String fsId;
        @JSONField(name = "isdir")
        private Integer isdir;
        @JSONField(name = "local_ctime")
        private Date localCtime;
        @JSONField(name = "local_mtime")
        private Date localMtime;
        @JSONField(name = "md5")
        private String md5;
        @JSONField(name = "oper_id")
        private Integer operId;
        @JSONField(name = "owner_id")
        private Integer ownerId;
        @JSONField(name = "path")
        private String path;
        @JSONField(name = "server_ctime")
        private Date serverCtime;
        @JSONField(name = "server_filename")
        private String serverFilename;
        @JSONField(name = "server_mtime")
        private Date serverMtime;
        @JSONField(name = "share")
        private Integer share;
        @JSONField(name = "size")
        private Integer size;
        @JSONField(name = "wpfile")
        private Integer wpfile;
        @JSONField(name = "thumbs")
        private ThumbsDTO thumbs;
        @JSONField(name = "docpreview")
        private String docpreview;
        @JSONField(name = "leftTime")
        private Integer leftTime;

        @NoArgsConstructor
        @Data
        public static class ThumbsDTO {
            @JSONField(name = "url1")
            private String url1;
            @JSONField(name = "url2")
            private String url2;
            @JSONField(name = "url3")
            private String url3;
        }
    }
}
