package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 文件预创建
 *
 * @author weiba
 * @date 2024/3/20 10:17
 */
@Accessors(chain = true)
@Getter
@Setter
public class PreCreateFileResponse extends ApiResponse {

    private Integer returnType;
    private List<Integer> blockList;
    private String uploadid;

}
