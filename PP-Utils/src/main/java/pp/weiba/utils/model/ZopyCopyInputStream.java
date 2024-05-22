package pp.weiba.utils.model;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
* 零拷贝
*
* @author weiba
* @date 2024/5/22 12:14
*/
@Log4j2
public class ZopyCopyInputStream extends InputStream {
    private final ByteBuffer buf;

    public ZopyCopyInputStream(ByteBuffer buf) {
        this.buf = buf;
    }

    @Override
    public int read() throws IOException {
        if (!buf.hasRemaining()) {
            return -1;
        }
        return buf.get() & 0xFF;
    }

    @Override
    public int read(byte[] bytes, int off, int len) throws IOException {
        if (!buf.hasRemaining()) {
            return -1;
        }

        len = Math.min(len, buf.remaining());
        buf.get(bytes, off, len);
        return len;
    }
}
