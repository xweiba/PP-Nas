package pp.weiba.framework.net.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 上传文件分块信息
 *
 * @author weiba
 * @date 2024/3/21 15:00
 */

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadFileChunk {


    private long start;

    private long length;

    private int partSeq;

}
