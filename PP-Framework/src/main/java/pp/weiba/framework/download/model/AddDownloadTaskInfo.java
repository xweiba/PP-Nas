package pp.weiba.framework.download.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 新增下载任务
 *
 * @author weiba
 * @date 2024/4/11 13:21
 */
@Data
@Accessors(chain = true)
public class AddDownloadTaskInfo {

    private DownloadInfo downloadInfo;

    private DownloadTaskOptionInfo option;

}
