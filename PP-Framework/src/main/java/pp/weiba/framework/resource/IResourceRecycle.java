package pp.weiba.framework.resource;

import java.util.List;

/**
 * 资源回收站
 *
 * @author weiba
 * @date 2024/4/7 10:40
 */
public interface IResourceRecycle {

    List<ResourceRecycleInfo> query(int pageSize, int pageNo);

    boolean delete(String key);

    boolean restore(String key);

}
