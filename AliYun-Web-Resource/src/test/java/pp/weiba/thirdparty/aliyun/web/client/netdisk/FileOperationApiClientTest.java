package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.*;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.*;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.WebNetDiskAuthenticationTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileOperationApiClientTest extends WebNetDiskAuthenticationTest {

    private final FileOperationApiClient fileOperationApiClient = new FileOperationApiClient(httpClient);

    public static GetRecycleResponse recycles;

    /* 当前是否为备份盘模式 */
    public static final Boolean IS_BACKUP_DRIVER_MODEL = true;

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

    @Test
    void copyToResource() {
        CopyToResourceResponse result = fileOperationApiClient.copyToResource("root", true, "638829ed5df082af754043cba40637f674d213b7");
        assertNotNull(result);
    }

    @Test
    void createShare() {
        CreateShareRequest createShareRequest = new CreateShareRequest().setExpiration(DateUtil.offsetDay(new Date(), 25)).setSharePwd("123456");
        createShareRequest.getFileIdList().add("66446acc76ccb0581e1b4dbd96ba029befa28526");
        CreateShareResponse result = fileOperationApiClient.createShare(createShareRequest);
        assertNotNull(result);
    }

    @Test
    void getMyShareList() {
        GetMyShareListResponse result = fileOperationApiClient.getMyShareList(new GetMyShareListRequest());
        assertNotNull(result);
    }

    @Test
    void cancelShare() {
        BatchResponse result = fileOperationApiClient.cancelShare("p46LoqhZhuB");
        assertNotNull(result);
    }

    public static final String shareId = "qA9VAdafGXe";
    public static final String sharePwd = "3gy0";

    private static GetShareTokenResponse shareTokenResponse;
    private static GetShareFilesResponse shareFiles;

    private static GetShareFileDetailResponse shareFileDetail;

    @Test
    void getShareToken() {
        shareTokenResponse = fileOperationApiClient.getShareToken(shareId, sharePwd);
        assertNotNull(shareTokenResponse);
    }

    @Test
    void getShareFiles() {
        getShareToken();
        shareFiles = fileOperationApiClient.getShareFiles(new GetShareFilesRequest(shareId), shareTokenResponse.getShareToken());
        assertNotNull(shareFiles);
    }

    @Test
    void getShareFileDetail() {
        getShareFiles();
        GetShareFilesResponse.ItemsResponse shareFile = shareFiles.getItems().get(0);

        shareFileDetail = fileOperationApiClient.getShareFileDetail(new GetShareFileDetailRequest(shareFile.getDriveId(), shareFile.getShareId(), shareFile.getFileId()), shareTokenResponse.getShareToken());
        assertNotNull(shareFileDetail);
    }

    private static GetFileChildDirResponse fileChildDirs;

    private static GetFileSimpleResponse simpleFile;
    @Test
    void getFileChildDir() {
        fileChildDirs = fileOperationApiClient.getFileChildDir(new GetFileChildDirRequest(IS_BACKUP_DRIVER_MODEL));
        assertNotNull(fileChildDirs);
    }

    @Test
    void getFileSimple() {
        getFileChildDir();
        GetFileChildDirResponse.ItemsResponse fileItem = fileChildDirs.getItems().get(0);
        simpleFile = fileOperationApiClient.getFileSimple(new GetFileSimpleRequest(IS_BACKUP_DRIVER_MODEL, fileItem.getFileId()));
        assertNotNull(simpleFile);
    }



    @Test
    void saveShareFile() {
        getFileSimple();
        getShareFileDetail();

        SaveShareFileBody saveShareFileBody = new SaveShareFileBody(IS_BACKUP_DRIVER_MODEL, simpleFile.getFileId(), shareFileDetail.getShareId(), shareFileDetail.getFileId());
        BatchResponse result = fileOperationApiClient.saveShareFile(shareTokenResponse.getShareToken(), saveShareFileBody);
        assertNotNull(result);
    }

}
