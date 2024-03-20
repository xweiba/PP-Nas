package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/11
 **/

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDuplicateDetectionResponse extends ApiResponse {

    private Info info;

    @NoArgsConstructor
    @Data
    public static class Info {
        private String md5;
        private Integer category;
        private String fsId;
        private Double requestId;
        private Integer fromType;
        private Long size;
        private Integer isdir;
        private Date mtime;
        private Date ctime;
        private String path;
    }
}
