package pp.weiba.framework.resource;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 资源上传抽象
 *
 * @author weiba
 * @date 2024/3/21 10:04
 */
@Log4j2
public abstract class AbstractUploadResource<T> implements IUploadResource<T> {

    @Override
    public String upload(UploadResourceInfo<T> uploadResourceInfo) {
        String resourceId = this.checkResourceExist(uploadResourceInfo);

        if (StrUtil.isNotBlank(resourceId)) {
            return resourceId;
        }
        return doUpload(uploadResourceInfo);
    }

    protected abstract String doUpload(UploadResourceInfo<T> uploadResourceInfo);


}
