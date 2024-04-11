package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.Digest;
import pp.weiba.framework.DigestType;
import pp.weiba.framework.resource.IResourceRecycle;
import pp.weiba.framework.resource.model.ResourceRecycleInfo;
import pp.weiba.framework.resource.model.ResourceType;
import pp.weiba.thirdparty.baidu.web.client.netdisk.RecycleApiClient;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.RecycleDeleteResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.RecycleQueryResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.RecycleRestoreResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 回收站服务
 *
 * @author weiba
 * @date 2024/4/7 11:24
 */
@Log4j2
public class ResourceRecycleService implements IResourceRecycle {

    private final RecycleApiClient recycleApiClient;

    public ResourceRecycleService(RecycleApiClient recycleApiClient) {
        this.recycleApiClient = recycleApiClient;
    }

    @Override
    public List<ResourceRecycleInfo> query(int pageSize, int pageNo) {
        RecycleQueryResponse response = recycleApiClient.query(pageSize, pageNo);
        List<ResourceRecycleInfo> result = new ArrayList<>();
        for (RecycleQueryResponse.ListBO item : response.getList()) {
            result.add(new ResourceRecycleInfo()
                    .setId(item.getFsId())
                    .setName(item.getServerFilename())
                    .setPath(item.getPath())
                    .setType(item.getIsdir() == 1 ? ResourceType.FOLDER : ResourceType.FILE)
                    .setCreateTime(item.getLocalCtime())
                    .setUpdateTime(item.getLocalMtime())
                    .setServerUpdateTime(item.getServerMtime())
                    .setServerCreateTime(item.getServerCtime())
                    .setDigests(item.getIsdir() == 0 ? new ArrayList<Digest>() {
                        {
                            add(new Digest().setType(DigestType.MD5).setValue(item.getMd5()));
                        }
                    } : null)
                    .setLeftTime(item.getLeftTime())
            );
        }
        return result;
    }

    @Override
    public boolean delete(String key) {
        RecycleDeleteResponse result = recycleApiClient.deleteByFsIds(key);
        return Boolean.TRUE;
    }

    @Override
    public boolean restore(String key) {
        RecycleRestoreResponse result = recycleApiClient.restoreByFsIds(key);
        return Boolean.TRUE;
    }
}
