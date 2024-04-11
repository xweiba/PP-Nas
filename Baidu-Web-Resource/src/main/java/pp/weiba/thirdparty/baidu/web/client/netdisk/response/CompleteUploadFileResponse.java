package pp.weiba.thirdparty.baidu.web.client.netdisk.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/11
 **/

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompleteUploadFileResponse extends ApiResponse {

    private Integer category;
    private Date ctime;
    private Integer fromType;
    private String fsId;
    private Integer isdir;
    private String md5;
    private Date mtime;
    private String path;
    private String serverFilename;
    private Long size;
    private String name;

}
