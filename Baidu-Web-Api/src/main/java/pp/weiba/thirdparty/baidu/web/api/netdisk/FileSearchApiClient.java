package pp.weiba.thirdparty.baidu.web.api.netdisk;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.resource.model.OrderType;
import pp.weiba.framework.resource.model.SortType;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.BaiduNetDiskWebSearchFilePathResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.DirChildNodeResponse;
import pp.weiba.utils.StringUtils;

/**
 * 文件搜索API
 *
 * @author weiba
 * @date 2024/4/8 14:44
 */
@Log4j2
public class FileSearchApiClient extends AbstractApiHttpClient {

    public FileSearchApiClient(IHttpClient httpClient) {
        super(httpClient);
    }


    /**
     * 文件搜索接口
     *
     * @param path      路径
     * @param keyWord   关键字
     * @param orderType 排序类型
     * @param sortType  排序方式
     * @param pageSize  每页大小
     * @param pageNo    页码
     * @return 搜索结果
     * @author weiba
     * @date 2024/3/20 16:20
     */
    public BaiduNetDiskWebSearchFilePathResponse searchByPath(String path, String keyWord, OrderType orderType, SortType sortType, Integer pageSize, Integer pageNo) {
        if (StringUtils.isBlank(keyWord) && StringUtils.isBlank(path)) return null;

        // 该接口关键字是必须的,没有关键字尝试从path中获取, 实现通过文件路径查找文件详情的功能.
        if (StringUtils.isBlank(keyWord) && !StringUtils.isBlank(path)
                && path.lastIndexOf("/") < path.lastIndexOf(".")
        ) {
            keyWord = path.substring(path.lastIndexOf("/") + 1);
            path = path.substring(0, path.lastIndexOf("/") + 1);
        }

        if (StrUtil.isBlank(keyWord)) { // 不能为空
            log.error("资源搜索异常， keyWord不能为空");
            throw new IllegalArgumentException("资源搜索异常， keyWord不能为空");
        }
        if (orderType == null) orderType = OrderType.CREATE_TIME;
        if (sortType == null) sortType = SortType.DESC;
        if (pageSize == null || pageSize == 0) pageSize = 10;
        if (pageNo == null || pageNo == 0) pageNo = 1;

        return execute(new TypeReference<BaiduNetDiskWebSearchFilePathResponse>() {
        }, UrlConstants.GET_SERACH, path, keyWord, orderType, sortType, pageSize, pageNo);
    }

    /**
     * 查询目录下的文件信息
     *
     * @param dstPath   : 指定路径
     * @param orderType : orderType 排序规则
     * @param desc      : desc: 1 降序 0 升序
     * @param pageSize  : 每页数量
     * @param pageNo    : 页码，从1开始。
     * @return weiba.pp.components.netdisc.bd.web.bo.BaiduNetDiskWebQueryFilePathResponse
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/10 15:07
     */
    public DirChildNodeResponse queryDirChild(String dstPath, OrderType orderType, SortType desc, Integer pageSize, Integer pageNo) {
        if (StrUtil.isBlank(dstPath) || !dstPath.startsWith("/")) {
            log.error("queryPathFiles error! dstPath:{}", dstPath);
            throw new IllegalArgumentException("queryPathFiles error!");
        }
        if (orderType == null) orderType = OrderType.CREATE_TIME;
        if (desc == null) desc = SortType.DESC;
        if (pageSize == null) pageSize = 100;
        if (pageNo == null) pageNo = 1;

        return execute(StrUtil.format(UrlConstants.GET_QUERY_DIR_CHILD, dstPath, orderType, desc, pageSize, pageNo), new TypeReference<DirChildNodeResponse>() {
        });
    }

}
