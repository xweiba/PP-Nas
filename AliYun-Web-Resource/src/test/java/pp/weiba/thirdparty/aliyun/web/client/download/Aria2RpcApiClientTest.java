package pp.weiba.thirdparty.aliyun.web.client.download;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.download.adapters.IDownloadAdapter;
import pp.weiba.framework.download.model.AddDownloadTaskInfo;
import pp.weiba.framework.download.model.DownloadAuthInfo;
import pp.weiba.framework.download.model.DownloadInfo;
import pp.weiba.framework.download.model.DownloadTaskOptionInfo;
import pp.weiba.framework.net.client.adapters.hutool.HutoolHttpClientAdapter;
import pp.weiba.service.download.adapters.Aria2DownloadAdapter;
import pp.weiba.service.download.client.Aria2RpcApiClient;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.WebNetDiskAuthenticationTest;

/**
* 
*
* @author weiba
* @date 2024/5/24 14:04
*/
@Log4j2
public class Aria2RpcApiClientTest extends WebNetDiskAuthenticationTest {

    public final Aria2RpcApiClient aria2RpcApiClient = new Aria2RpcApiClient("https://api.weiba.pp.ua:32880/aria2/jsonrpc", "656CjCKQ0iQUB78eXgd2VpYmExMDI4", new HutoolHttpClientAdapter());

    public final IDownloadAdapter adapter = new Aria2DownloadAdapter(aria2RpcApiClient);

    public void addDownloadTask(String downloadUrl, long fileSize, String downloadName) {
        DownloadAuthInfo authInfo = new DownloadAuthInfo();
        authInfo.addheader("Referer", "https://www.aliyundrive.com/");
        DownloadInfo downloadInfo = new DownloadInfo()
                .setDownloadUrl(downloadUrl)
                .setFileSize(fileSize)
                .setAuthInfo(authInfo);
        DownloadTaskOptionInfo options = new DownloadTaskOptionInfo()
                .setDownloadName(downloadName)
                .setDstDirPath("/downloads")
                ;
        adapter.add(new AddDownloadTaskInfo(downloadInfo, options));
    }
    
}
