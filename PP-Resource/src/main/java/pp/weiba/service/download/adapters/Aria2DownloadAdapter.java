package pp.weiba.service.download.adapters;

import pp.weiba.framework.download.adapters.IDownloadAdapter;
import pp.weiba.framework.download.model.*;
import pp.weiba.framework.model.StatusResultInfo;

import java.util.List;

/**
 * Aria2 下载适配器
 *
 * @author weiba
 * @date 2024/4/11 13:45
 */
public class Aria2DownloadAdapter implements IDownloadAdapter {


    @Override
    public List<DownloadTaskInfo> query(DownloadQueryParams params) {
        return null;
    }

    @Override
    public DownloadTaskInfo add(AddDownloadTaskInfo addDownloadTaskInfo) {
        return null;
    }

    @Override
    public DownloadTaskInfo get(String downloadTaskId) {
        return null;
    }

    @Override
    public List<DownloadProgressInfo> getProgress(List<String> downloadTaskIds) {
        return null;
    }

    @Override
    public List<StatusResultInfo> changeStatus(List<ChangeDownloadTaskStatus> changeDownloadTaskStatuses) {
        return null;
    }
}
