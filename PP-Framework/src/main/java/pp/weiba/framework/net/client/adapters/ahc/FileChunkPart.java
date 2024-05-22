package pp.weiba.framework.net.client.adapters.ahc;

import lombok.Getter;
import org.asynchttpclient.request.body.multipart.FilePart;
import pp.weiba.utils.model.FileChunk;

import java.io.File;

/**
 * 分片Part
 *
 * @author weiba
 * @date 2024/3/22 14:54
 */
@Getter
public class FileChunkPart extends FilePart {

    private FileChunk chunk;

    public FileChunkPart(String name, File file) {
        super(name, file);
    }

    public FileChunkPart(String name, File file, FileChunk chunk) {
        super(name, file);
        this.chunk = chunk;
    }
}
