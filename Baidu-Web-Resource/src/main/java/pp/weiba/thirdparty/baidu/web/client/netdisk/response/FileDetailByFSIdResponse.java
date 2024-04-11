package pp.weiba.thirdparty.baidu.web.client.netdisk.response;

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
        // dlink 大于20Mb的需要添加 User-Agent: pan.baidu.com 和 BDUSS Cookie 才能下载，没有ua会报：31326错误，没有BDUSS会报31045错误。
        // 小于20Mb的不能添加, 它不是真实下载地址，推荐追踪302获取到真实下载地址再推给下载器
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
