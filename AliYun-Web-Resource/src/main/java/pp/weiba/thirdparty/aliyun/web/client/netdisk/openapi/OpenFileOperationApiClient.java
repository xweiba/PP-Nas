package pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.OpenApiUrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi.request.GetDownloadUrlRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.openapi.request.GetDownloadUrlResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.PersonalInfo;

/**
* 
*
* @author weiba
* @date 2024/5/24 13:35
*/
@Log4j2
public class OpenFileOperationApiClient extends AbstractApiHttpClient {

    public OpenFileOperationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }


    /**
     * 获取文件下载地址
     *
     * @param params 文件信息
     * @return 文件下载地址
     * @author weiba
     * @date 2024/5/24 13:44
     */
    public GetDownloadUrlResponse getDownloadUrl(GetDownloadUrlRequest params) {
        return postSrtExecute(OpenApiUrlConstants.POST_GET_DOWNLOAD_URL, params, new TypeReference<GetDownloadUrlResponse>() {
        });
    }


}
