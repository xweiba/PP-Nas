package pp.weiba.framework.download.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 修改下载任务状态
 *
 * @author weiba
 * @date 2024/4/11 13:31
 */
@Data
@Accessors(chain = true)
public class ChangeDownloadTaskStatus {

    private String downloadTaskId;

    private DownloadStatus status;

}
