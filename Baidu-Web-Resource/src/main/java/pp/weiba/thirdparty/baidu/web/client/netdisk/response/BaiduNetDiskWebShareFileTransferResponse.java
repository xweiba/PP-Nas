package pp.weiba.thirdparty.baidu.web.client.netdisk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/9/24
 **/

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaiduNetDiskWebShareFileTransferResponse {

    private Integer errno;
    private ExtraBO extra;
    private List<InfoBO> info;
    private String newno;
    private Long requestId;
    private String showMsg;
    private Integer taskId;

    @NoArgsConstructor
    @Data
    public static class ExtraBO {
        private List<ListBO> list;

        @NoArgsConstructor
        @Data
        public static class ListBO {
            private String from;
            private Long fromFsId;
            private String to;
            private Long toFsId;
        }
    }

    @NoArgsConstructor
    @Data
    public static class InfoBO {
        private Integer errno;
        private String fsId;
        private String path;
    }
}
