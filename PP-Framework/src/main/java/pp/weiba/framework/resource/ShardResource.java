package pp.weiba.framework.resource;

import lombok.extern.log4j.Log4j2;

/**
 * 分片资源信息
 *
 * @author weiba
 * @date 2024/3/21 10:22
 */
@Log4j2
public class ShardResource<T> {

    // 分片后的资源
    private T entity;

    // 分片序号
    private int partSeq;

}
