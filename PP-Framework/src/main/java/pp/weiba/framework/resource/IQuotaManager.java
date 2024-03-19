package pp.weiba.framework.resource;

/**
 * 配额管理接口
 *
 * @author weiba
 * @date 2024/3/5 15:42
 */
public interface IQuotaManager {

    /**
     * 资源容量信息接口，用于获取资源的容量信息。
     *
     * @return 资源容量信息
     * @author weiba
     * @date 2024/3/5 15:42
     */
    QuotaInfo getCapacity();
    
}
