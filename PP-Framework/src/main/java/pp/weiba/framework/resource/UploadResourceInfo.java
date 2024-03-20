package pp.weiba.framework.resource;

import lombok.extern.log4j.Log4j2;

/**
 * 上传资源信息
 *
 * @author weiba
 * @date 2024/3/20 13:24
 */
@Log4j2
public class UploadResourceInfo<T> {

    // 目标资源路径
    private String dstDirPath;

    // 上传信息
    private T entity;

}
