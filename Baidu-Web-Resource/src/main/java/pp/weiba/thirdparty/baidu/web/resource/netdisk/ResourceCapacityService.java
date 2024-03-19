package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.IResourceCapacity;
import pp.weiba.framework.resource.ResourceCapacity;
import pp.weiba.thirdparty.baidu.web.api.netdisk.base.AuthenticationApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.base.CapacityResponse;

/**
 * 网盘基础服务
 *
 * @author weiba
 * @date 2024/3/19 13:33
 */
@Log4j2
public class ResourceCapacityService implements IResourceCapacity {

    private final AuthenticationApiClient authenticationApiClient;

    public ResourceCapacityService(AuthenticationApiClient authenticationApiClient) {
        this.authenticationApiClient = authenticationApiClient;
    }

    @Override
    public ResourceCapacity getCapacity() {
        CapacityResponse capacity = this.authenticationApiClient.getCapacity();
        return new ResourceCapacity(capacity.getUsed(), capacity.getTotal());
    }

}
