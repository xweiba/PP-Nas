package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 百度网盘异步任务Response
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2024/4/1 13:46
 */

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsyncTaskResponse<T> {
    private Integer errno;
    private Long requestId;
    private Integer taskErrno;
    private String status;
    private List<T> list;
    private Integer total;

    @NoArgsConstructor
    @Data
    public static class Delete {
        private Integer baseRevision;
        private String isdir;
        private String path;
        private String realServerMtime;
        private String serverMtime;
        private String size;
    }

    @NoArgsConstructor
    @Data
    public static class FromTo {
        private String from;
        private String to;
    }

}
