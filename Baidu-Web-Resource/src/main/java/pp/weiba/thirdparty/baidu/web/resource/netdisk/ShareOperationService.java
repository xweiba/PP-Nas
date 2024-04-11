package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.Digest;
import pp.weiba.framework.DigestType;
import pp.weiba.framework.KeyValue;
import pp.weiba.framework.resource.IShareOperation;
import pp.weiba.framework.resource.model.*;
import pp.weiba.thirdparty.baidu.web.client.netdisk.ShareOperationApiClient;
import pp.weiba.thirdparty.baidu.web.client.netdisk.request.BaiduNetDiskWebQueryShareFileParams;
import pp.weiba.thirdparty.baidu.web.client.netdisk.request.QueryShareOrderType;
import pp.weiba.thirdparty.baidu.web.client.netdisk.request.ShareExpireTime;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.*;
import pp.weiba.utils.FileUtils;

import java.util.*;

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
    public List<ShareInfo> query(OrderType orderType, SortType desc, Integer pageSize, Integer pageNo) {
        WebBaiduNetDiskQueryMyShareFilesResponse webBaiduNetDiskQueryMyShareFilesResponse = shareOperationClient.queryMyShareFiles(orderType, desc, pageSize, pageNo);

        List<ShareInfo> result = new ArrayList<>();
        for (WebBaiduNetDiskQueryMyShareFilesResponse.ListResponse item : webBaiduNetDiskQueryMyShareFilesResponse.getList()) {
            ShareInfo shareInfo = new ShareInfo()
                    .setShareId(item.getShareId())
                    .setShareName(item.getTypicalPath())
                    .setPassword(item.getPasswd())
                    .setLink(item.getShortlink())
                    .setCreateTime(item.getCtime())
                    .setExpireTime(item.getExpiredTime())
                    .setResourceIds(item.getFsIds());

            if (item.getTypicalCategory() == -1) {
                shareInfo.setStatus(ShareStatus.EXPIRE);
            }

            result.add(shareInfo);
        }
        return result;
    }

    @Override
    public String getSharePassword(String shareId) {
        MyShareResponse myShareDetailById = shareOperationClient.getMyShareDetailById(shareId);
        return myShareDetailById.getPwd();
    }

    @Override
    public ShareInfo create(String ids, String password, Integer expireDay) {
        ShareFileResponse shareFileResponse = shareOperationClient.shareFiles(ids, password, ShareExpireTime.getByValue(expireDay));
        ShareInfo shareInfo = new ShareInfo()
                .setShareId(shareFileResponse.getShareid())
                .setPassword(password)
                .setLink(shareFileResponse.getLink())
                .setCreateTime(shareFileResponse.getCtime())
                .setExpireTime(shareFileResponse.getExpiretime())
                .setResourceIds(Arrays.asList(ids.split(",")));
        return shareInfo;
    }

    @Override
    public boolean cancel(String shareIds) {
        CancelSharesResponse cancelSharesResponse = shareOperationClient.cancelShareFiles(shareIds);
        return true;
    }

    @Override
    public Map<String, List<KeyValue>> verify(String shareLink, String password) {
        KeyValue keyValue = shareOperationClient.verify(shareLink, password);
        return new HashMap<String, List<KeyValue>>() {{
            put("cookies", new ArrayList<KeyValue>() {{
                add(keyValue);
            }});
        }};
    }

    @Override
    public ShareDetailInfo getDetail(String shareLink, Map<String, List<KeyValue>> verifyMap) {

        BaiduNetDiskWebShareFileDetailResponse detail = shareOperationClient.getDetail(shareLink, getCookie(verifyMap));

        ShareDetailInfo shareDetailInfo = new ShareDetailInfo();
        List<ResourceInfo> resourceInfos = new ArrayList<>();
        shareDetailInfo.setShareId(detail.getShareid())
                .setShareUserId(detail.getShareUk())
                .setShareUserName(detail.getLinkusername())
                .setSharePhotoUrl(detail.getPhoto())
                .setUserId(detail.getUk())
                .setUserName(detail.getUsername())
                .setPhotoUrl(detail.getPhoto())
                .setResourceInfos(resourceInfos)
        ;
        if (detail.getFileList() != null) {
            for (BaiduNetDiskWebShareFileDetailResponse.FileListDTO item : detail.getFileList()) {
                boolean isDir = item.getIsdir() == 1;
                ResourceType type = isDir ? ResourceType.FOLDER : ResourceType.FILE;
                resourceInfos.add(
                        new ResourceInfo().setId(item.getFsId())
                                .setType(type)
                                .setName(item.getServerFilename())
                                .setState(ResourceStatus.NORMAL)
                                .setSize(item.getSize())
                                .setPath(item.getPath())
                                .setExt(isDir ? null : FileUtils.getExtByFileName(item.getServerFilename()))
                                .setDigests(isDir ? null : new ArrayList<Digest>() {{
                                    add(new Digest(DigestType.MD5, item.getMd5()));
                                }})
                                .setCreateTime(item.getServerCtime())
                                .setUpdateTime(item.getServerMtime())
                                .setServerCreateTime(item.getServerCtime())
                                .setServerUpdateTime(item.getServerMtime())
                );
            }
        }
        return shareDetailInfo;
    }

    @Override
    public List<ResourceInfo> getDetailChild(ShareQueryParams params) {
        BaiduNetDiskWebQueryShareFileParams baiduNetDiskWebQueryShareFileParams = new BaiduNetDiskWebQueryShareFileParams();
        baiduNetDiskWebQueryShareFileParams.setShareId(params.getShareId())
                .setShareUk(params.getShareUserId())
                .setDir(params.getPath())
                .setOrderType(QueryShareOrderType.getByValue(params.getOrderType().getValue()))
                .setSortType(params.getSortType())
                .setPageNo(params.getPageNo())
                .setPageSize(params.getPageSize())
                .setVerifyCookie(getCookie(params.getVerify()));
        BaiduNetDiskWebQueryShareFileResponse detailChild = shareOperationClient.getDetailChild(baiduNetDiskWebQueryShareFileParams);

        List<ResourceInfo> resourceInfos = new ArrayList<>();
        for (BaiduNetDiskWebQueryShareFileResponse.ListDTO item : detailChild.getList()) {
            boolean isDir = item.getIsdir() == 1;
            ResourceType type = isDir ? ResourceType.FOLDER : ResourceType.FILE;
            resourceInfos.add(
                    new ResourceInfo().setId(item.getFsId())
                            .setType(type)
                            .setName(item.getServerFilename())
                            .setState(ResourceStatus.NORMAL)
                            .setSize(item.getSize())
                            .setPath(item.getPath())
                            .setExt(isDir ? null : FileUtils.getExtByFileName(item.getServerFilename()))
                            .setDigests(isDir ? null : new ArrayList<Digest>() {{
                                add(new Digest(DigestType.MD5, item.getMd5()));
                            }})
                            .setCreateTime(item.getServerCtime())
                            .setUpdateTime(item.getServerMtime())
                            .setServerCreateTime(item.getServerCtime())
                            .setServerUpdateTime(item.getServerMtime())
            );
        }
        return resourceInfos;
    }

    @Override
    public boolean save(SaveShareParams saveShareParams) {
        BaiduNetDiskWebShareFileTransferResponse response = shareOperationClient.shareFileTransferExecute(saveShareParams.getShareId(), saveShareParams.getShareUserId(), saveShareParams.getUserId(), getCookie(saveShareParams.getVerify()), saveShareParams.getSavePath(), saveShareParams.getResourceIds());
        return true;
    }

    private KeyValue getCookie(Map<String, List<KeyValue>> verify) {
        // 使用Optional防止空指针，从verifyMap获取cookies集合中的BDCLND值
        KeyValue verifyCookie = Optional.ofNullable(verify)
                .flatMap(map -> Optional.ofNullable(map.get("cookies")))
                .flatMap(cookieList -> cookieList.stream()
                        .filter(keyValue -> "BDCLND".equals(keyValue.getKey()))
                        .findFirst())
                .orElse(null);
        return verifyCookie;
    }
}
