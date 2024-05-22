package pp.weiba.utils.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文件分块
 *
 * @author weiba
 * @date 2024/3/21 14:16
 */
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileChunk {

    public FileChunk(long start, long length, int partSeq) {
        this.start = start;
        this.length = length;
        this.partSeq = partSeq;
    }

    private long start;

    private long length;

    private int partSeq;

    private String md5;
}
