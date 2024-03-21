package pp.weiba.framework.resource;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
 * 上传资源信息
 *
 * @author weiba
 * @date 2024/3/20 13:24
 */
@Log4j2
@Data
@Accessors(chain = true)
public class UploadResourceInfo<T> {

    private String uploadId;

    // 目标资源路径
    private String dstDirPath;

    // 上传信息
    private T entity;

}
