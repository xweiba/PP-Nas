package pp.weiba.thirdparty.baidu.web.api.netdisk;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.RecycleDeleteResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.RecycleQueryResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.RecycleRestoreResponse;
import pp.weiba.utils.ArrayUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 回收站客户端
 *
 * @author weiba
 * @date 2024/4/7 9:23
 */
@Log4j2
public class RecycleApiClient extends AbstractApiHttpClient {

    public RecycleApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 查询回收站内的文件信息
     *
     * @param pageSize : 页大小
     * @param pageNo   : 页码
     * @return 回收站内容
     * @author weiba
     * @date 2024/4/7 9:23
     */
    public RecycleQueryResponse query(int pageSize, int pageNo) {
        return execute(new TypeReference<RecycleQueryResponse>() {
        }, UrlConstants.GET_QUERY_RECYCLES, pageSize, pageNo);
    }


    /**
     * 回收站文件删除接口
     *
     * @param fsIds 待删除ids
     * @return 删除结果
     * @author weiba
     * @date 2024/4/7 9:34
     */
    public RecycleDeleteResponse deleteByFsIds(String fsIds) {
        if (StrUtil.isBlank(fsIds)) throw new IllegalArgumentException("fsId 不能为空");
        RecycleDeleteResponse result = postExecute(UrlConstants.POST_RECYCLE_DEL_URL,
                new HashMap<String, Object>() {{
                    put("fidlist", StrUtil.format("[{}]", fsIds));
                }}, new TypeReference<RecycleDeleteResponse>() {
                });

        return result;
    }

    public RecycleRestoreResponse restoreByFsIds(String fsIds) {
        if (StrUtil.isBlank(fsIds)) throw new IllegalArgumentException("fsId 不能为空");
        List<String> fsIdList = ArrayUtils.asListAndFilterNull(fsIds.split(","));

        RecycleRestoreResponse result = postExecute(UrlConstants.POST_RECYCLE_RESTORE_URL,
                new HashMap<String, Object>() {{
                    put("fidlist", StrUtil.format("[{}]", fsIds));
                }}, new TypeReference<RecycleRestoreResponse>() {
                });

        return result;
    }

}
