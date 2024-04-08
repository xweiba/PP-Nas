package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.IShareOperation;
import pp.weiba.framework.resource.ShareInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.ShareOperationApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.ShareExpireTime;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.ShareFileResponse;

/**
 * 分享管理服务
 *
 * @author weiba
 * @date 2024/3/20 16:30
 */
@Log4j2
public class ShareOperationService implements IShareOperation {

    private final ShareOperationApiClient shareOperationClient;

    public ShareOperationService(ShareOperationApiClient shareOperationClient) {
        this.shareOperationClient = shareOperationClient;
    }


    @Override
    public ShareInfo create(String ids, String password, Integer expireDay) {
        ShareFileResponse shareFileResponse = shareOperationClient.shareFiles(ids, password, ShareExpireTime.getByValue(expireDay));
        return null;
    }

    @Override
    public boolean cancel(String shareIds) {
        return false;
    }

    @Override
    public ShareInfo get(String shareKey, String password) {
        return null;
    }

    @Override
    public void save(String shareKey, String password, String saveKey) {

    }
}
