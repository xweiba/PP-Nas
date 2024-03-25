package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 分片文件信息
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/11
 **/

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadFileChunkResponse {

    private String md5;
    private Integer partseq;
    private Long requestId;
    private String uploadid;

}
