package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.AddFolderRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.GetFileInfoRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.AddFolderResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.GetFileInfoResponse;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.WebNetDiskAuthenticationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileOperationApiClientTest extends WebNetDiskAuthenticationTest {

    private final FileOperationApiClient fileOperationApiClient = new FileOperationApiClient(httpClient);

    @Test
    void createDir() {
        AddFolderRequest addFolderRequest = new AddFolderRequest().setDriveId("13298650").setParentFileId("638829ed5df082af754043cba40637f674d213b7").setName("test222").setCheckNameMode("refuse").setType("folder");
        AddFolderResponse dir = fileOperationApiClient.createDir(addFolderRequest);
        assertNotNull(dir);
    }


    @Test
    void getFileInfo() {
        GetFileInfoRequest getFileInfoRequest = new GetFileInfoRequest().setDriveId("13298650").setFileId("65f89804c8b9c616dc1544b99a66b8b86618f1fa").enablePreview();
        GetFileInfoResponse fileInfo = fileOperationApiClient.getFileInfo(getFileInfoRequest);
        assertNotNull(fileInfo);
    }
}
