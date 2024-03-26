package pp.weiba.thirdparty.baidu.web.api.netdisk;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.AbstractApiHttpClient;
import pp.weiba.framework.core.client.IHttpClient;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.OrderType;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.SortType;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.CreateDirResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.DirChildNodeResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.FileDetailByFSIdResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.SearchResponse;
import pp.weiba.utils.StringUtils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 百度网盘文件管理API
 *
 * @author weiba
 * @date 2024/3/6 15:17
 */
@Log4j2
public class FileOperationApiClient extends AbstractApiHttpClient {

    public FileOperationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 创建指定文件夹，支持多层, 存在同名文件夹时服务器会自动添加后缀
     *
     * @param newDstPath : 文件夹路径，以 '/' 开头
     * @return 返回信息
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/11 9:36
     */
    public CreateDirResponse createDir(String newDstPath) {
        if (StrUtil.isBlank(newDstPath) || !newDstPath.startsWith("/")) {
            String msg = String.format("路径应以 '/' 开头! 错误路径: %s", newDstPath);
            throw new IllegalArgumentException(msg);
        }
        return postExecute(UrlConstants.POST_CREATE_DIR, new HashMap<String, Object>() {{
            put("isdir", 1);
            put("block_list", "[]");
            put("path", newDstPath);
        }}, new TypeReference<CreateDirResponse>() {
        });
    }


    public FileDetailByFSIdResponse getFileDetailByFsIds(String fsIds) {
        return postExecute(XpanUrlConstants.POST_FILE_MULTIMEDIA, new HashMap<String, Object>() {{
            put("method", "filemetas");
            put("dlink", 1);
            put("fsids", Arrays.toString(fsIds.split(",")));
        }}, new TypeReference<FileDetailByFSIdResponse>() {
        });
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
        if (orderType == null) orderType = OrderType.TIME;
        if (desc == null) desc = SortType.DESC;
        if (pageSize == null) pageSize = 100;
        if (pageNo == null) pageNo = 1;

        return execute(StrUtil.format(UrlConstants.GET_QUERY_DIR_CHILD, dstPath, orderType, desc, pageSize, pageNo), new TypeReference<DirChildNodeResponse>() {
        });
    }

    /**
     * 文件搜索接口
     *
     * @param path      路径
     * @param keyWord   关键字
     * @param orderType 排序类型
     * @param desc      排序方式
     * @param pageSize  每页大小
     * @param pageNo    页码
     * @return 搜索结果
     * @author weiba
     * @date 2024/3/20 16:20
     */
    public SearchResponse search(String path, String keyWord, OrderType orderType, SortType desc, Integer pageSize, Integer pageNo) {
        if (StringUtils.isBlank(keyWord) && StringUtils.isBlank(path)) return null;

        // 该接口关键字是必须的,没有关键字尝试从path中获取, 实现通过文件路径查找文件详情的功能.
        if (StringUtils.isBlank(keyWord) && !StringUtils.isBlank(path)
                && path.lastIndexOf("/") < path.lastIndexOf(".")
        ) {
            keyWord = path.substring(path.lastIndexOf("/") + 1);
            path = path.substring(0, path.lastIndexOf("/") + 1);
        }

        if (StrUtil.isBlank(keyWord)) { // 不能为空
            log.error("searchByPath error! dstPath:{}", path);
            throw new RuntimeException("searchByPath error!");
        }
        if (orderType == null) orderType = OrderType.TIME;
        if (desc == null) desc = SortType.DESC;
        if (pageSize == null || pageSize == 0) pageSize = 10;
        if (pageNo == null || pageNo == 0) pageNo = 1;

        return execute(StrUtil.format(UrlConstants.GET_FILE_SEARCH, path, keyWord, orderType, desc, pageSize, pageNo), new TypeReference<SearchResponse>() {
        });
    }


}
