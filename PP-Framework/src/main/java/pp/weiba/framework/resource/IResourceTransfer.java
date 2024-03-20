package pp.weiba.framework.resource;

import java.io.IOException;
import java.util.List;

/**
 * 资源传输接口
 *
 * @author weiba
 * @date 2024/3/5 15:54
 */
public interface IResourceTransfer {


    /**
     * 初始化分片上传
     *
     * @param resourceInfo 要上传的资源对象
     * @return 上传会话标识符 uploadSessionId
     * @throws IOException 发生 I/O 错误时抛出
     * @author weiba
     * @date 2024/3/5 15:54
     */
    String initiateMultipartUpload(ResourceInfo resourceInfo) throws IOException;

    /**
     * 上传分片
     *
     * @param uploadSessionId 上传会话标识符
     * @param partNumber      分片编号
     * @param partData        分片数据
     * @return 分片上传结果
     * @throws IOException 发生 I/O 错误时抛出
     * @author weiba
     * @date 2024/3/5 15:54
     */
    PartUploadResult uploadPart(String uploadSessionId, int partNumber, byte[] partData) throws IOException;

    /**
     * 完成分片上传
     *
     * @param uploadSessionId 上传会话标识符
     * @param uploadedParts   所有上传分片的结果列表
     * @return 资源标识符
     * @throws IOException 发生 I/O 错误时抛出
     * @author weiba
     * @date 2024/3/5 15:54
     */
    String completeMultipartUpload(String uploadSessionId, List<PartUploadResult> uploadedParts) throws IOException;

    /**
     * 取消分片上传
     *
     * @param uploadSessionId 上传会话标识符
     * @throws IOException 发生 I/O 错误时抛出
     * @author weiba
     * @date 2024/3/5 15:54
     */
    void abortMultipartUpload(String uploadSessionId) throws IOException;
}
