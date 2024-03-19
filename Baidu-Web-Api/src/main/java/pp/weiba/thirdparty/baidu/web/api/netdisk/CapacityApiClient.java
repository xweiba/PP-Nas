package pp.weiba.thirdparty.baidu.web.api.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.AbstractApiHttpClient;
import pp.weiba.framework.core.client.IHttpClient;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.CapacityResponse;

/**
 * 基础信息
 *
 * @author weiba
 * @date 2024/3/7 9:50
 */
@Log4j2
public class CapacityApiClient extends AbstractApiHttpClient {

    public CapacityApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 获取网盘容量信息
     *
     * @return 容量信息
     * @author weiba
     * @date 2024/3/7 14:57
     */
    public CapacityResponse getCapacity() {
        return execute(UrlConstants.GET_QUOTA, new TypeReference<CapacityResponse>() {
        });
    }

}
