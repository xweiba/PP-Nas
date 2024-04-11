package pp.weiba.framework.download;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.download.adapters.IDownloadAdapter;
import pp.weiba.framework.download.model.*;
import pp.weiba.framework.model.StatusResultInfo;

import java.util.List;

/**
 * 管理下载请求和下载任务
 *
 * @author weiba
 * @date 2024/4/11 9:23
 */
@Log4j2
public class DownloadManager implements IDownloadAdapter {

    private final IDownloadAdapter downloadAdapter;

    public DownloadManager(IDownloadAdapter downloadAdapter) {
        this.downloadAdapter = downloadAdapter;
    }

    @Override
    public List<DownloadTaskInfo> query(DownloadQueryParams params) {
        return downloadAdapter.query(params);
    }

    @Override
    public DownloadTaskInfo add(AddDownloadTaskInfo addDownloadTaskInfo) {
        return downloadAdapter.add(addDownloadTaskInfo);
    }

    @Override
    public DownloadTaskInfo get(String downloadTaskId) {
        return downloadAdapter.get(downloadTaskId);
    }

    @Override
    public List<DownloadProgressInfo> getProgress(List<String> downloadTaskIds) {
        return downloadAdapter.getProgress(downloadTaskIds);
    }

    @Override
    public List<StatusResultInfo> changeStatus(List<ChangeDownloadTaskStatus> changeDownloadTaskStatuses) {
        return downloadAdapter.changeStatus(changeDownloadTaskStatuses);
    }
}
