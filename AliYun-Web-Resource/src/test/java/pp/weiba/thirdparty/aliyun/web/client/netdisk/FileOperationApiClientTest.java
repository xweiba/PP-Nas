package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import cn.hutool.core.collection.CollUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.AddFolderRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.GetFileInfoRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.GetRecycleRsequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.*;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.WebNetDiskAuthenticationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileOperationApiClientTest extends WebNetDiskAuthenticationTest {

    private final FileOperationApiClient fileOperationApiClient = new FileOperationApiClient(httpClient);

    public static GetRecycleResponse recycles;

    @Test
    void createDir() {
        AddFolderRequest addFolderRequest = new AddFolderRequest().setDriveId("18654654").setParentFileId("638829ed5df082af754043cba40637f674d213b7").setName("test222").setCheckNameMode("refuse").setType("folder");
        AddFolderResponse dir = fileOperationApiClient.createDir(addFolderRequest);
        assertNotNull(dir);
    }


    @Test
    void getFileInfo() {
        GetFileInfoRequest getFileInfoRequest = new GetFileInfoRequest().setDriveId("18654654").setFileId("65f89804c8b9c616dc1544b99a66b8b86618f1fa").enablePreview();
        GetFileInfoResponse fileInfo = fileOperationApiClient.getFileInfo(getFileInfoRequest);
        assertNotNull(fileInfo);
    }

    @Test
    @Disabled
    void delele() {
        BatchResponse batchResponse = fileOperationApiClient.delele("663db23c8caba03781a540b9b0848e1434db38be");
        assertNotNull(batchResponse);
    }

    @Test
    @Disabled
    void move() {
        BatchResponse batchResponse = fileOperationApiClient.move("663dd748e83859e8bebe4333b15555ed72ac7fb7", "root");
        assertNotNull(batchResponse);
    }

    @Test
    @Disabled
    void rename() {
        RenameResponse rename = fileOperationApiClient.rename("638829ed5df082af754043cba40637f674d213b7", "test");
        assertNotNull(rename);
    }

    @Test
    @Disabled
    void getFileDownloadUrl() {
        GetFileDownloadUrlResponse result = fileOperationApiClient.getFileDownloadUrl("663dd748e83859e8bebe4333b15555ed72ac7fb7");
        assertNotNull(result);
    }

    @Test
    @Disabled
    void fileToRecycle() {
        BatchResponse batchResponse = fileOperationApiClient.fileToRecycle("663dbb87a45ed768219f465a82ab7a4f9d337967");
        assertNotNull(batchResponse);
    }

    @Test
    void getRecycles() {
        recycles = fileOperationApiClient.getRecycles(new GetRecycleRsequest());
        assertNotNull(recycles);
    }

    @Test
    void recycleToFile() {
        getRecycles();
        if (recycles != null && CollUtil.isNotEmpty(recycles.getItems())) {
            BatchResponse batchResponse = fileOperationApiClient.fileToRecycle(recycles.getItems().get(0).getFileId());
            assertNotNull(batchResponse);
        }
    }

    @Test
    void search() {
        SearchResponse search = fileOperationApiClient.search("1", null, null);
        search = fileOperationApiClient.search("1", null, search.getNextMarker());
        assertNotNull(search);
    }
}
