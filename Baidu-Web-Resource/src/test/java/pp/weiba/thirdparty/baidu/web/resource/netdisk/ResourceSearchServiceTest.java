package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IResourceSearch;
import pp.weiba.framework.resource.ResourceInfo;
import pp.weiba.framework.resource.ResourceQueryParams;
import pp.weiba.thirdparty.baidu.web.api.netdisk.FileSearchApiClient;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.WebNetDiskAuthenticationTest;
import pp.weiba.utils.LogUtils;

import java.util.List;

@Log4j2
class ResourceSearchServiceTest extends WebNetDiskAuthenticationTest {

    IResourceSearch searchService = new ResourceSearchService(new FileSearchApiClient(httpClient));

    protected List<ResourceInfo> queryResult;

    @Test
    void query() {
        queryResult = searchService.query(new ResourceQueryParams().setPath("/")
                .setKeyWord(".txt")
                .setPageNo(1)
                .setPageSize(10));
        LogUtils.info(log, queryResult);
    }

    void initDatas() {
        query();
        Assertions.assertTrue(CollUtil.isNotEmpty(queryResult), "我的资源为空");
    }
}
