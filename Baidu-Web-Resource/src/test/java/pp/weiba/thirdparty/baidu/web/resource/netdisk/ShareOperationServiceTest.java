package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IShareOperation;
import pp.weiba.framework.resource.ResourceInfo;
import pp.weiba.framework.resource.ShareInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.ShareOperationApiClient;
import pp.weiba.utils.LogUtils;

import java.util.List;

@Log4j2
class ShareOperationServiceTest extends ResourceSearchServiceTest {


    IShareOperation resourceRecycle = new ShareOperationService(new ShareOperationApiClient(httpClient));

    @Test
    void create() {
        query();
        if (CollUtil.isNotEmpty(queryResult)) {
            StrBuilder idSb = new StrBuilder();
            List<ResourceInfo> shareFile = queryResult.subList(0, 1);
            shareFile.forEach(item -> idSb.append(item.getId()).append(","));
            String ids = idSb.delTo(idSb.length() - 1).toString();
            ShareInfo result = resourceRecycle.create(ids, RandomUtil.randomString(4), 1);
            LogUtils.info(log, result);
        }
    }

    @Test
    void cancel() {
    }

    @Test
    void get() {
    }

    @Test
    void save() {
    }
}
