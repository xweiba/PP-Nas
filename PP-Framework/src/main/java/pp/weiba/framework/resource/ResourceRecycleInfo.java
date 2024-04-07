package pp.weiba.framework.resource;

import lombok.Data;
import lombok.experimental.Accessors;
import pp.weiba.framework.Digest;

import java.util.Date;
import java.util.List;

/**
 * 回收站资源信息
 *
 * @author weiba
 * @date 2024/4/7 11:12
 */
@Data
@Accessors(chain = true)
public class ResourceRecycleInfo {

    // 资源标识
    private String id;

    // 资源类型
    private ResourceType type;

    // 资源名称
    private String name;

    // 资源路径，网络或本地路径
    private String path;

    // 资源签名信息
    private List<Digest> digests;

    /* 资源真实时间 */
    private Date createTime;

    /* 资源真实修改 */
    private Date updateTime;

    /* 资源添加到服务器的时间 */
    private Date serverCreateTime;

    /* 资源在服务器修改的信息时间，删除时间 */
    private Date serverUpdateTime;

    // 剩余有效时间, 单位天
    private int leftTime;


}
