package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.download.Aria2RpcApiClientTest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi.OpenFileOperationApiClientTest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.*;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileOperationApiClientTest extends OpenFileOperationApiClientTest {

    private final FileOperationApiClient fileOperationApiClient = new FileOperationApiClient(httpClient);

    public static GetRecycleResponse recycles;

    /* 当前是否为备份盘模式 */
    public static final Boolean IS_BACKUP_DRIVER_MODEL = true;

    public static final String UPLOAD_FILE_PATH = "D:\\download\\11\\rom\\rom_2.zip";

    public static final String UPLOAD_FILE_NAME = "rom_2.zip";


    @Test
    void createDir() {
        AddFolderRequest addFolderRequest = new AddFolderRequest().setDriveId("18654654").setParentFileId("638829ed5df082af754043cba40637f674d213b7").setName("test222").setCheckNameMode("refuse").setType("folder");
        AddFolderResponse dir = fileOperationApiClient.createDir(addFolderRequest);
        assertNotNull(dir);
    }

    GetFileInfoResponse fileInfo = null;
    @Test
    void getFileInfo() {
        GetFileInfoRequest getFileInfoRequest = new GetFileInfoRequest(IS_BACKUP_DRIVER_MODEL, "619404e699bbc0ca16d64e268e7808d303cac214").enablePreview();
        fileInfo = fileOperationApiClient.getFileInfo(getFileInfoRequest);
        assertNotNull(fileInfo);
    }

    @Test
    void downloadFile() {
        getFileInfo();
        addDownloadTask(IS_BACKUP_DRIVER_MODEL, fileInfo.getFileId(), fileInfo.getName(), fileInfo.getSize());
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

    SearchResponse search;
    @Test
    void search() {
        search = fileOperationApiClient.search("rom_2.zip", null, null);
//        search = fileOperationApiClient.search("1", null, search.getNextMarker());
        assertNotNull(search);
    }

    @Test
    void copyToResource() {
        CopyToResourceResponse result = fileOperationApiClient.copyToResource("root", true, "664d99e2c55d0f7366ca41c78786c8b670add6a5");
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

    @Test
    void checkFileExist() {
        CreateWithFoldersResponse result =  fileOperationApiClient.checkFileExist(IS_BACKUP_DRIVER_MODEL, "root", UPLOAD_FILE_NAME, UPLOAD_FILE_PATH);
        assertNotNull(result);
    }

    @Test
    void fileTransferInSecond() {
        CreateWithFoldersResponse result =  fileOperationApiClient.fileTransferInSecond(IS_BACKUP_DRIVER_MODEL, "root", UPLOAD_FILE_NAME, UPLOAD_FILE_PATH);
        assertNotNull(result);
    }

    @Test
    void uploadFile() {
        CreateWithFoldersResponse result =  fileOperationApiClient.uploadFile(IS_BACKUP_DRIVER_MODEL, "root", UPLOAD_FILE_NAME, UPLOAD_FILE_PATH);
        assertNotNull(result);
    }


}
