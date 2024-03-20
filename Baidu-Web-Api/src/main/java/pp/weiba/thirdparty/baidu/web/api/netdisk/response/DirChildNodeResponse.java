package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 目录下的文件信息
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/10
 **/

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DirChildNodeResponse extends ApiResponse {

    private List<ListBO> list;
    private String guidInfo;
    private Integer guid;

    @NoArgsConstructor
    @Data
    public static class ListBO {
        private Integer tkbindId;
        private Integer ownerType;
        private Integer category;
        private String realCategory;
        private String fsId;
        private String md5;
        private Integer operId;
        private Date serverCtime;
        private Integer extentTinyint7;
        private Integer wpfile;
        private Date localMtime;
        private Long size;
        private Integer fromType;
        private Integer pl;
        private Integer share;
        private Date serverAtime;
        private String path;
        private Date localCtime;
        private Date serverMtime;
        private String serverFilename;
        private Integer ownerId;
        private Integer unlist;
        private Integer isdir;
        private String docpreview;
        private String lodocpreview;
        private ThumbsBO thumbs;

        @NoArgsConstructor
        @Data
        public static class ThumbsBO {
            private String url3;
            private String url2;
            private String url1;
        }
    }
}
