package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.Digest;
import pp.weiba.framework.DigestType;
import pp.weiba.framework.resource.IResourceSearch;
import pp.weiba.framework.resource.model.ResourceInfo;
import pp.weiba.framework.resource.model.ResourceQueryParams;
import pp.weiba.framework.resource.model.ResourceStatus;
import pp.weiba.framework.resource.model.ResourceType;
import pp.weiba.thirdparty.baidu.web.api.netdisk.FileSearchApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.BaiduNetDiskWebSearchFilePathResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.DirChildNodeResponse;
import pp.weiba.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源搜索服务
 *
 * @author weiba
 * @date 2024/4/8 14:43
 */
@Log4j2
public class ResourceSearchService implements IResourceSearch {

    private final FileSearchApiClient fileSearchApiClient;

    public ResourceSearchService(FileSearchApiClient fileSearchApiClient) {
        this.fileSearchApiClient = fileSearchApiClient;
    }


    @Override
    public List<ResourceInfo> query(ResourceQueryParams params) {
        List<ResourceInfo> result = null;
        if (StrUtil.isBlank(params.getKeyWord())) {
            result = queryDirChild(params);
        } else {
            result = searchByPathAndKey(params);
        }
        return result;
    }

    private List<ResourceInfo> queryDirChild(ResourceQueryParams params) {
        DirChildNodeResponse dirChildNodeResponse = fileSearchApiClient.queryDirChild(params.getPath(), params.getOrderType(), params.getSortType(), params.getPageSize(), params.getPageNo());
        List<ResourceInfo> result = new ArrayList<>();
        if (CollUtil.isNotEmpty(dirChildNodeResponse.getList())) {
            for (DirChildNodeResponse.ListBO item : dirChildNodeResponse.getList()) {
                boolean isDir = item.getIsdir() == 1;
                ResourceType type = isDir ? ResourceType.FOLDER : ResourceType.FILE;
                result.add(new ResourceInfo().setId(item.getFsId())
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
                        .setServerUpdateTime(item.getServerMtime()));
            }
        }
        return result;
    }

    private List<ResourceInfo> searchByPathAndKey(ResourceQueryParams params) {
        BaiduNetDiskWebSearchFilePathResponse dirChildNodeResponse = fileSearchApiClient.searchByPath(params.getPath(), params.getKeyWord(), params.getOrderType(), params.getSortType(), params.getPageSize(), params.getPageNo());
        List<ResourceInfo> result = new ArrayList<>();
        if (CollUtil.isNotEmpty(dirChildNodeResponse.getList())) {
            for (BaiduNetDiskWebSearchFilePathResponse.ListBO item : dirChildNodeResponse.getList()) {
                boolean isDir = item.getIsdir() == 1;
                ResourceType type = isDir ? ResourceType.FOLDER : ResourceType.FILE;
                result.add(new ResourceInfo().setId(item.getFsId())
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
                        .setServerUpdateTime(item.getServerMtime()));
            }
        }
        return result;
    }
}
