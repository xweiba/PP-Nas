package pp.weiba.thirdparty.baidu.web.netdisk.file;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.netdisk.ApiResponse;

import java.util.Date;

/**
 * 新增文件夹
 *
 * @author weiba
 * @date 2024/3/7 15:19
 */
@Log4j2
@Accessors(chain = true)
@Getter
@Setter
public class CreateDirResponse extends ApiResponse {

    private Integer ctime;
    private String fsId;
    private Integer isdir;
    private Date mtime;
    private String path;
    private Integer status;
    private String name;
    private Integer category;

}
