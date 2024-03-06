package pp.weiba.thirdparty.baidu.web.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.IHttpClient;

/**
 * 百度网盘文件管理
 *
 * @author weiba
 * @date 2024/3/6 15:17
 */
@Log4j2
public class FileManagementClient {

    private final IHttpClient httpClient;

    public FileManagementClient(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }


}
