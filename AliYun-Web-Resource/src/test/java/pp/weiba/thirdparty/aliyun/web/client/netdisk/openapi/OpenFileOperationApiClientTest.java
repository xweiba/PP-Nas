package pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi;

import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.download.Aria2RpcApiClientTest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi.request.GetDownloadUrlRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi.request.GetDownloadUrlResponse;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.WebNetDiskAuthenticationTest;

import static org.junit.jupiter.api.Assertions.*;

class OpenFileOperationApiClientTest extends Aria2RpcApiClientTest {

    OpenFileOperationApiClient fileOperationApiClient = new OpenFileOperationApiClient(httpClient);

    GetDownloadUrlResponse downloadUrl;

    @Test
    void getDownloadUrl() {
        GetDownloadUrlResponse downloadUrl = fileOperationApiClient.getDownloadUrl(new GetDownloadUrlRequest(true, "664d99e2c55d0f7366ca41c78786c8b670add6a5"));
        addDownloadTask(downloadUrl.getUrl(), "", 364408302L, "rom_2.zip");
    }


}
