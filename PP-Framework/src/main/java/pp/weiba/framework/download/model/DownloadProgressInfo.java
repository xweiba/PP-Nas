package pp.weiba.framework.download.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 下载进度信息
 *
 * @author weiba
 * @date 2024/4/11 13:27
 */
@Data
@Accessors(chain = true)
public class DownloadProgressInfo {

    private String downloadTaskId;

    private Long completedSize;

    private Long fileSize;

}
