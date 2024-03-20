package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.IShareOperation;
import pp.weiba.thirdparty.baidu.web.api.netdisk.ShareOperationApiClient;

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


}
