package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.KeyValue;
import pp.weiba.framework.resource.IShareOperation;
import pp.weiba.framework.resource.model.*;
import pp.weiba.thirdparty.baidu.web.client.netdisk.ShareOperationApiClient;
import pp.weiba.thirdparty.baidu.web.client.netdisk.UrlConstants;
import pp.weiba.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
class ShareOperationServiceTest extends ResourceSearchServiceTest {

    IShareOperation shareOperationService = new ShareOperationService(new ShareOperationApiClient(httpClient));

    private List<ShareInfo> myShares;

    private ShareInfo shareInfo = null;

    // 测试的分享url，必须是其他用户的，自己的不允许转存
    String shareLink = UrlConstants.SHARE_PREFIX + "Tw088PJ6TWdDqRShamRqQw";
    String password = "2x8z";

    private Map<String, List<KeyValue>> verify = new HashMap<String, List<KeyValue>>() {{
        put("cookies", new ArrayList<KeyValue>() {{
            add(new KeyValue("BDCLND", "9BAAT%2FoTfrOnMG4yf8wRNuJvMj%2Fb5XUe69RkY66KtAs%3D"));
        }});
    }};

    private ShareDetailInfo shareDetail;

    private ResourceInfo dirSharedResource;

    private List<ResourceInfo> dirSharedResourceChild;

    void initDatas() {
        myShares = shareOperationService.query(OrderType.CREATE_TIME, SortType.DESC, 10, 1);
        Assertions.assertTrue(CollUtil.isNotEmpty(myShares), "我的分享为空，请先创建分享");
    }

    void initData() {
        initDatas();
        for (ShareInfo item : myShares) {
            if (item.getStatus() == ShareStatus.NORMAL) {
                shareInfo = item;
                break;
            }
        }
        // 如果没有可用的分享，测试为失败
        Assertions.assertNotNull(shareInfo, "没有未过期的分享，请先创建新分享");
    }

    void initPwd() {
        initData();
        String sharePassword = shareOperationService.getSharePassword(shareInfo.getShareId());
        Assertions.assertTrue(StrUtil.isNotEmpty(sharePassword), "密码获取失败");
        shareInfo.setPassword(sharePassword);
    }

    void initVerify() {
        verify = initVerify(shareLink, password);
    }

    Map<String, List<KeyValue>> initVerify(String shareLink, String password) {
        // 注意频率，接口调用次数过多被禁止，报错：密码输入次数达到上限
        Map<String, List<KeyValue>> verify = shareOperationService.verify(shareLink, password);
        Assertions.assertTrue(CollUtil.isNotEmpty(verify), "密码认证失败");
        return verify;
    }

    void initDetail() {
        shareDetail = shareOperationService.getDetail(shareLink, verify);
        Assertions.assertNotNull(shareDetail, "未查询到分享详细信息");
        Assertions.assertTrue(CollUtil.isNotEmpty(shareDetail.getResourceInfos()), "分享详细信息文件不存在");
    }

    void initDirSharedResource() {
        initDetail();
        for (ResourceInfo myShare : shareDetail.getResourceInfos()) {
            if (myShare.getType() == ResourceType.FOLDER) {
                dirSharedResource = myShare;
                break;
            }
        }
        Assertions.assertNotNull(shareDetail, "分享信息中不存在文件夹");
    }

    void initDirSharedResourceChild() {
        initDirSharedResource();
        ShareQueryParams shareQueryParams = new ShareQueryParams();
        shareQueryParams.setShareId(shareDetail.getShareId());
        shareQueryParams.setShareUserId(shareDetail.getShareUserId());
        shareQueryParams.setPath(dirSharedResource.getPath());
        shareQueryParams.setVerify(verify);
        dirSharedResourceChild = shareOperationService.getDetailChild(shareQueryParams);
        Assertions.assertNotNull(dirSharedResourceChild, "子文件夹数据为空");
    }

    boolean initSave() {
        initDirSharedResourceChild();
        List<String> ids = dirSharedResourceChild.subList(0, 5).stream().map(ResourceInfo::getId).collect(Collectors.toList());
        SaveShareParams saveShareParams = new SaveShareParams()
                .setShareId(shareDetail.getShareId())
                .setShareUserId(shareDetail.getShareUserId())
                .setUserId(shareDetail.getUserId())
                .setSavePath("/test")
                .setResourceIds(ids)
                .setVerify(verify);
        boolean save = shareOperationService.save(saveShareParams);
        Assertions.assertTrue(save, "分享文件保存失败");
        return save;
    }

    @Test
    void query() {
        initDatas();
        LogUtils.info(log, myShares);
    }

    @Test
    void getSharePassword() {
        initPwd();
        LogUtils.info(log, shareInfo);
    }

    @Test
    void create() {
        super.initDatas();
        ResourceInfo resourceInfo = queryResult.get(0);
        ShareInfo result = shareOperationService.create(resourceInfo.getId(), RandomUtil.randomString(4), 1);
        LogUtils.info(log, result);
    }

    @Test
    void cancel() {
        initData();
        boolean result = shareOperationService.cancel(shareInfo.getShareId());
        LogUtils.info(log, result);
    }

    @Test
    void verify() {
        initVerify();
        LogUtils.info(log, verify);
    }

    @Test
    void getDetail() {
        initDetail();
        LogUtils.info(log, shareDetail);
    }

    @Test
    void getDetailChild() {
        initDirSharedResourceChild();
        LogUtils.info(log, dirSharedResourceChild);
    }

    @Test
    void save() {
        LogUtils.info(log, initSave());
    }
}
