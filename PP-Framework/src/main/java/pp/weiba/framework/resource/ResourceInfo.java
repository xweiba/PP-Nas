package pp.weiba.framework.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.Digest;

import java.util.Date;
import java.util.List;

/**
 * 资源信息
 *
 * @author weiba
 * @date 2024/3/5 15:24
 */
@Log4j2
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceInfo {

    // 资源标识
    private String id;

    // 资源类型
    private ResourceType type;

    // 资源名称
    private String name;

    // 资源状态
    private ResourceState state = ResourceState.NORMAL;

    // 资源大小
    private Long size;

    // 资源路径，网络或本地路径
    private String path;

    // 资源扩展名称
    private String ext;

    // 资源签名信息
    private List<Digest> digests;

    /* 资源真实时间 */
    private Date createTime;

    /* 资源真实修改 */
    private Date updateTime;

    /* 资源添加到服务器的时间 */
    private Date serverCreateTime;

    /* 资源在服务器修改的信息时间 */
    private Date serverUpdateTime;

}
