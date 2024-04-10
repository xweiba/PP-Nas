package pp.weiba.framework.resource;

import pp.weiba.framework.resource.model.ResourceRecycleInfo;

import java.util.List;

/**
 * 资源回收站
 *
 * @author weiba
 * @date 2024/4/7 10:40
 */
public interface IResourceRecycle {

    /**
     * 查询
     *
     * @param pageSize 页大小
     * @param pageNo   页码
     * @return 结果
     * @author weiba
     * @date 2024/4/7 16:59
     */
    List<ResourceRecycleInfo> query(int pageSize, int pageNo);

    /**
     * 删除回收站某个资源
     *
     * @param key 资源id
     * @return 执行状态
     * @author weiba
     * @date 2024/4/7 17:00
     */
    boolean delete(String key);

    /**
     * 恢复回收站某个资源
     *
     * @param key 资源id
     * @return 执行状态
     * @author weiba
     * @date 2024/4/7 17:00
     */
    boolean restore(String key);

}
