package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IResourceRecycle;
import pp.weiba.framework.resource.model.ResourceRecycleInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.RecycleApiClient;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.WebNetDiskAuthenticationTest;
import pp.weiba.utils.LogUtils;

import java.util.List;

@Log4j2
class ResourceRecycleServiceTest extends WebNetDiskAuthenticationTest {

    IResourceRecycle resourceRecycle = new ResourceRecycleService(new RecycleApiClient(httpClient));

    ResourceRecycleInfo restoreResource;

    @Test
    void query() {
        List<ResourceRecycleInfo> result = resourceRecycle.query(50, 1);
        if (CollUtil.isNotEmpty(result)) {
            restoreResource = result.get(0);
        }
        LogUtils.info(log, result);
    }

    @Test
    void delete() {
        query();
        if (restoreResource == null) {
            return;
        }
        boolean result = resourceRecycle.delete(restoreResource.getId());
        LogUtils.info(log, result);
    }

    @Test
    void restore() {
        query();
        if (restoreResource == null) {
            return;
        }
        boolean result = resourceRecycle.restore(restoreResource.getId());
        LogUtils.info(log, result);

    }
}
