package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.AddFolderRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.GetFileInfoRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.AddFolderResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.GetFileInfoResponse;

/**
 * 文件管理API
 *
 * @author weiba
 * @date 2024/4/30 9:54
 */
@Log4j2
public class FileOperationApiClient extends AbstractApiHttpClient {

    public FileOperationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 新建文件夹
     *
     * @param addFolderParam 新建文件夹参数
     * @return 新建文件夹信息
     * @author weiba
     * @date 2024/4/30 10:57
     */
    public AddFolderResponse createDir(AddFolderRequest addFolderParam) {
        return postSrtExecute(UrlConstants.POST_ADD_FOLDER_URL, addFolderParam, new TypeReference<AddFolderResponse>() {
        });
    }


    /**
     * 获取文件信息
     *
     * @param getFileInfoParam 获取文件信息参数
     * @return 文件信息
     * @author weiba
     * @date 2024/4/30 10:57
     */
    public GetFileInfoResponse getFileInfo(GetFileInfoRequest getFileInfoParam) {
        return postSrtExecute(UrlConstants.POST_GET_FILE_INFO_URL, getFileInfoParam, new TypeReference<GetFileInfoResponse>() {
        });
    }



}
