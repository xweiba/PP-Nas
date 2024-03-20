package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.IResourceTransfer;
import pp.weiba.framework.resource.PartUploadResult;
import pp.weiba.framework.resource.ResourceInfo;

import java.io.IOException;
import java.util.List;

/**
 * 资源传输服务
 *
 * @author weiba
 * @date 2024/3/20 10:55
 */
@Log4j2
public class ResourceTransferService implements IResourceTransfer {

    @Override
    public String initiateMultipartUpload(ResourceInfo resourceInfo) throws IOException {
        return null;
    }

    @Override
    public PartUploadResult uploadPart(String uploadSessionId, int partNumber, byte[] partData) throws IOException {
        return null;
    }

    @Override
    public String completeMultipartUpload(String uploadSessionId, List<PartUploadResult> uploadedParts) throws IOException {
        return null;
    }

    @Override
    public void abortMultipartUpload(String uploadSessionId) throws IOException {

    }
}
