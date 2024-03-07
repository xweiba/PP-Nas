package pp.weiba.thirdparty.baidu.web.netdisk.base;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.AbstractApiClient;
import pp.weiba.thirdparty.baidu.web.client.HttpRequest;
import pp.weiba.thirdparty.baidu.web.client.IHttpClient;
import pp.weiba.thirdparty.baidu.web.client.TypeReference;
import pp.weiba.thirdparty.baidu.web.netdisk.UrlConstants;
import pp.weiba.thirdparty.baidu.web.netdisk.utils.BaiduWebApiUtils;

/**
 * 基础信息
 *
 * @author weiba
 * @date 2024/3/7 9:50
 */
@Log4j2
public class BaseApiClient extends AbstractApiClient {

    private BaseApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 获取网盘容量信息
     *
     * @return 容量信息
     * @author weiba
     * @date 2024/3/7 14:57
     */
    public Capacity getCapacity() {
        return httpClient.execute(HttpRequest.urlFormatBuilder(UrlConstants.GET_QUOTA, BaiduWebApiUtils.getDpLogId()), new TypeReference<Capacity>() {
        });
    }
}
