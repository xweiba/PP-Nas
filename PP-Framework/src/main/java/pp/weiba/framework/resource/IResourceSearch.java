package pp.weiba.framework.resource;

import java.util.List;

/**
 * 资源搜索接口
 *
 * @author weiba
 * @date 2024/3/5 15:59
 */
public interface IResourceSearch {

    /**
     * 根据查询参数获取资源
     *
     * @param params 资源查询参数
     * @return 返回资源列表
     * @author weiba
     * @date 2024/3/5 16:03
     */
    List<ResourceInfo> query(ResourceQueryParams params);

}
