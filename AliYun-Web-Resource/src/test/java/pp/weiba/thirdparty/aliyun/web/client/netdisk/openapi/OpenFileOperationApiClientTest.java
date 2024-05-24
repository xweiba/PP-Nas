package pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.download.Aria2RpcApiClientTest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi.request.GetDownloadUrlRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi.request.GetDownloadUrlResponse;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.WebNetDiskAuthenticationTest;

import static org.junit.jupiter.api.Assertions.*;

public class OpenFileOperationApiClientTest extends Aria2RpcApiClientTest {

    OpenFileOperationApiClient fileOperationApiClient = new OpenFileOperationApiClient(httpClient);

    GetDownloadUrlResponse downloadUrl;

    @Test
    @Disabled
    void getDownloadUrl() {
        GetDownloadUrlResponse downloadUrl = fileOperationApiClient.getDownloadUrl(new GetDownloadUrlRequest(true, "664d99e2c55d0f7366ca41c78786c8b670add6a5"));
        addDownloadTask(true, downloadUrl.getUrl(), "rom_2.zip", 364408302L);
    }

    public void addDownloadTask(boolean isBackupDrive, String fileId, String fileName, long fileSize) {
        GetDownloadUrlResponse downloadUrl = fileOperationApiClient.getDownloadUrl(new GetDownloadUrlRequest(isBackupDrive, fileId));
        addDownloadTask(downloadUrl.getUrl(), fileName, fileSize);
    }

}
