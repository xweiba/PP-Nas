package pp.weiba.framework.resource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 分片资源信息
 *
 * @author weiba
 * @date 2024/3/21 10:22
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ShardResource<T> {

    // 分片后的资源
    private T entity;

    // 分片序号
    private int partSeq;

}
