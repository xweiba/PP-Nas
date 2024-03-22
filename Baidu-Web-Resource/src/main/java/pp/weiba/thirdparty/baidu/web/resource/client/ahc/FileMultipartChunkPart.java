package pp.weiba.thirdparty.baidu.web.resource.client.ahc;

import cn.hutool.core.util.ReflectUtil;
import io.netty.buffer.ByteBuf;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.request.body.multipart.part.FileMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartState;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 文件分片Part
 *
 * @author weiba
 * @date 2024/3/22 14:21
 */
@Log4j2
public class FileMultipartChunkPart extends FileMultipartPart {

    private final File file;
    private InputStream in;

    public FileMultipartChunkPart(FileChunkPart part, byte[] boundary) {
        super(part, boundary);
        if (part.getChunk() == null) {
            throw new IllegalArgumentException("part chunk doesn't null");
        }
        file = part.getFile();
        // 反射强制修改, test
        ReflectUtil.setFieldValue(this, "position", part.getChunk().getStart());
        ReflectUtil.setFieldValue(this, "length", part.getChunk().getLength());
    }

    @Override
    public long transferContentTo(ByteBuf target) throws IOException {
        FileChannel channel = ReflectUtil.invoke(this, "getChannel");
        long position = (long) ReflectUtil.getFieldValue(this, "position");
        long length = (long) ReflectUtil.getFieldValue(this, "length");


        long transferred = getPositionToChannel(target, channel, position, length);
        //        long transferred = getPositionToInputStream(target, position, length);

        return transferred;
    }

    @SneakyThrows
    private InputStream getInputStream() {
        if (in == null) {
            long position = (long) ReflectUtil.getFieldValue(this, "position");
            long length = (long) ReflectUtil.getFieldValue(this, "length");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            byte[] buffer = new byte[(int) length];
            randomAccessFile.seek(position);
            randomAccessFile.readFully(buffer);
            in = new ByteArrayInputStream(buffer);
        }
        return in;
    }

    private long getPositionToInputStream(ByteBuf target, long position, long length) throws IOException {
        InputStream inputStream = getInputStream();
        int transferred = target.writeBytes(inputStream, target.writableBytes());
        if (transferred > 0) {
            position += transferred;
        }

        if (position == this.getContentLength() || transferred < 0) {
            this.state = MultipartState.POST_CONTENT;
            inputStream.close();
        }
        log.info("position:{}, length:{}, transferred:{}", position, length, transferred);
        ReflectUtil.setFieldValue(this, "position", position);
        return transferred;
    }

    private long getPositionToChannel(ByteBuf target, FileChannel channel, long position, long length) throws IOException {
        int transferred;
        transferred = target.writeBytes(channel, position, target.writableBytes());
        if (transferred > 0) {
            position += transferred;
        }

        if (position == this.getContentLength() || transferred < 0) {
            this.state = MultipartState.POST_CONTENT;
            if (channel.isOpen()) {
                channel.close();
            }
        }
        log.info("position:{}, length:{}, transferred:{}", position, length, transferred);
        ReflectUtil.setFieldValue(this, "position", position);
        return transferred;
    }
}
