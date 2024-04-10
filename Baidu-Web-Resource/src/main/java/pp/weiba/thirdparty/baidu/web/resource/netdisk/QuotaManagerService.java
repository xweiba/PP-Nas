package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.IQuotaManager;
import pp.weiba.framework.resource.model.QuotaInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.CapacityApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.CapacityResponse;

/**
 * 网盘基础服务
 *
 * @author weiba
 * @date 2024/3/19 13:33
 */
@Log4j2
public class QuotaManagerService implements IQuotaManager {

    private final CapacityApiClient capacityApiClient;

    public QuotaManagerService(CapacityApiClient authenticationApiClient) {
        this.capacityApiClient = authenticationApiClient;
    }

    @Override
    public QuotaInfo getCapacity() {
        CapacityResponse capacity = this.capacityApiClient.getCapacity();
        return new QuotaInfo(capacity.getUsed(), capacity.getTotal());
    }

}
