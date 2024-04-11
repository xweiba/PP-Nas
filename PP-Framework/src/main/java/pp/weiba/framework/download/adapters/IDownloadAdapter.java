package pp.weiba.framework.download.adapters;

import pp.weiba.framework.download.model.*;
import pp.weiba.framework.model.StatusResultInfo;

import java.util.List;

/**
 * 下载器适配器
 *
 * @author weiba
 * @date 2024/4/11 9:24
 */
public interface IDownloadAdapter {

    /**
     * 查询下载任务列表。
     *
     * @param params 封装了查询下载任务的参数
     * @return 返回符合查询条件的下载任务信息列表
     */
    List<DownloadTaskInfo> query(DownloadQueryParams params);

    /**
     * 添加一个新的下载任务。
     *
     * @param addDownloadTaskInfo 封装了添加下载任务必需的信息
     * @return 返回创建的下载任务信息
     */
    DownloadTaskInfo add(AddDownloadTaskInfo addDownloadTaskInfo);

    /**
     * 根据下载任务ID获取下载任务的详细信息。
     *
     * @param downloadTaskId 下载任务的唯一标识符
     * @return 返回指定ID的下载任务信息，如果不存在则返回null
     */
    DownloadTaskInfo get(String downloadTaskId);

    /**
     * 获取一个或多个下载任务的进度信息。
     *
     * @param downloadTaskIds 一个包含下载任务ID的列表
     * @return 返回下载任务的进度信息列表
     */
    List<DownloadProgressInfo> getProgress(List<String> downloadTaskIds);

    /**
     * 更改一个或多个下载任务的状态。
     *
     * @param changeDownloadTaskStatuses 一个包含下载任务状态改变请求的列表
     * @return 返回一个修改结果
     */
    List<StatusResultInfo> changeStatus(List<ChangeDownloadTaskStatus> changeDownloadTaskStatuses);

}
