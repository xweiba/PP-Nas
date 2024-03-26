package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/9/30
 **/

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDetailByFSIdResponse {

    private String errmsg;
    private Integer errno;
    private List<ListBO> list;
    private NamesBO names;
    private String requestId;

    @NoArgsConstructor
    @Data
    public static class NamesBO {
    }

    @NoArgsConstructor
    @Data
    public static class ListBO {
        private Integer category;
        private String dlink;
        private String filename;
        private String fsId;
        private Integer isdir;
        private String md5;
        private Integer operId;
        private String path;
        private Date serverCtime;
        private Date serverMtime;
        private Long size;
    }
}
