package pp.weiba.service.download.adapters;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.download.adapters.IDownloadAdapter;
import pp.weiba.framework.download.model.DownloadQueryParams;
import pp.weiba.framework.download.model.DownloadStatus;
import pp.weiba.framework.download.model.DownloadTaskInfo;
import pp.weiba.framework.net.client.adapters.hutool.HutoolHttpClientAdapter;
import pp.weiba.framework.test.DefaultTest;
import pp.weiba.service.download.client.Aria2RpcApiClient;
import pp.weiba.utils.LogUtils;

import java.util.List;

@Log4j2
class Aria2DownloadAdapterTest extends DefaultTest {

    private final Aria2RpcApiClient aria2RpcApiClient = new Aria2RpcApiClient("https://aria2.com/aria2/jsonrpc", "xxxxxxxx", new HutoolHttpClientAdapter());

    private final IDownloadAdapter adapter = new Aria2DownloadAdapter(aria2RpcApiClient);

    @Test
    void query() {
        List<DownloadTaskInfo> result = queryActive();
        LogUtils.info(log, result);
        result = queryPause();
        LogUtils.info(log, result);
        result = querySTOP();
        LogUtils.info(log, result);
        result = queryFinish();
        LogUtils.info(log, result);
    }

    @Test
    void add() {
    }

    @Test
    void get() {
    }

    @Test
    void getProgress() {
    }

    @Test
    void changeStatus() {
    }

    List<DownloadTaskInfo> queryActive() {
        return query(DownloadStatus.ACTIVE);
    }

    List<DownloadTaskInfo> queryPause() {
        return query(DownloadStatus.PAUSE);
    }

    List<DownloadTaskInfo> querySTOP() {
        return query(DownloadStatus.STOP);
    }

    List<DownloadTaskInfo> queryFinish() {
        return query(DownloadStatus.FINISH);
    }

    List<DownloadTaskInfo> query(DownloadStatus status) {
        return adapter.query(new DownloadQueryParams().setStatus(status));
    }

}
