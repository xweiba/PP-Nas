package pp.weiba.thirdparty.baidu.web.resource.client.ahc;

import lombok.Getter;
import org.asynchttpclient.request.body.multipart.FilePart;
import pp.weiba.framework.core.client.UploadFileChunk;

import java.io.File;

/**
 * 分片Part
 *
 * @author weiba
 * @date 2024/3/22 14:54
 */
@Getter
public class FileChunkPart extends FilePart {

    private UploadFileChunk chunk;

    public FileChunkPart(String name, File file) {
        super(name, file);
    }

    public FileChunkPart(String name, File file, UploadFileChunk chunk) {
        super(name, file);
        this.chunk = chunk;
    }
}
