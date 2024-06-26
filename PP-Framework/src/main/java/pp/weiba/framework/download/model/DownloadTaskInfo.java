package pp.weiba.framework.download.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 下载任务信息
 *
 * @author weiba
 * @date 2024/4/11 10:00
 */
@Data
@Accessors(chain = true)
public class DownloadTaskInfo {

    private String downloadTaskId;

    // 任务状态
    private DownloadStatus status = DownloadStatus.ACTIVE;

    // 已下载长度
    private Long completedSize;

    // 任务上传速度
    private Long uploadSpeed;

    // 任务下载速度
    private Long downloadSpeed;

    private DownloadInfo downloadInfo;

    // 下载配置
    private DownloadTaskOptionInfo option;

}
