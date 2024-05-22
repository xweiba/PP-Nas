package pp.weiba.utils.model;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
* 零拷贝, 它并不是真正的零拷贝，只是利用了零拷贝技术，减少了内存拷贝次数，从而减少了内存占用。
*
* @author weiba
* @date 2024/5/22 12:14
*/
@Log4j2
public class FileChannelCopyInputStream extends InputStream {

    private final ByteBuffer buf;

    private final FileChannel channel;

    @SneakyThrows
    public FileChannelCopyInputStream(FileChannel channel, long start, long length) {
        this.channel = channel;
        this.buf = channel.map(FileChannel.MapMode.READ_ONLY, start, length);
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

    @Override
    public void close() throws IOException {
        super.close();
        // 释放资源
        channel.close();
    }
}
