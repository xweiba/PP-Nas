package pp.weiba.thirdparty.baidu.web.netdisk.file;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.AbstractApiClient;
import pp.weiba.thirdparty.baidu.web.client.IHttpClient;

/**
 * 百度网盘文件管理API
 *
 * @author weiba
 * @date 2024/3/6 15:17
 */
@Log4j2
public class FileManagementApiClient extends AbstractApiClient {

    public FileManagementApiClient(IHttpClient httpClient) {
        super(httpClient);
    }


}
