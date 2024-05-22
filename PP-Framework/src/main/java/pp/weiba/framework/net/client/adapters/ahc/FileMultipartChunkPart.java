package pp.weiba.framework.net.client.adapters.ahc;

import cn.hutool.core.util.ReflectUtil;
import io.netty.buffer.ByteBuf;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.request.body.multipart.part.FileMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartState;
import pp.weiba.utils.FileUtils;

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
    private InputStream inputStream;

    private final long maxLength;

    public FileMultipartChunkPart(FileChunkPart part, byte[] boundary) {
        super(part, boundary);
        if (part.getChunk() == null) {
            throw new IllegalArgumentException("part chunk doesn't null");
        }
        file = part.getFile();
        this.maxLength = part.getChunk().getLength() + part.getChunk().getStart();
        // 反射强制修改
        ReflectUtil.setFieldValue(this, "position", part.getChunk().getStart());
        ReflectUtil.setFieldValue(this, "length", part.getChunk().getLength());
    }

    @Override
    public long transferContentTo(ByteBuf target) throws IOException {
        FileChannel channel = ReflectUtil.invoke(this, "getChannel");
        long position = (long) ReflectUtil.getFieldValue(this, "position");
        long length = (long) ReflectUtil.getFieldValue(this, "length");


        long transferred = getPositionToChannel(target, channel, position, length);
        //long transferred = getPositionToInputStream(target, position, length);

        return transferred;
    }

    /*@SneakyThrows
    private InputStream initInputStream() {
        if (inputStream == null) {
            long position = (long) ReflectUtil.getFieldValue(this, "position");
            long length = (long) ReflectUtil.getFieldValue(this, "length");
            inputStream = FileUtils.getZopyCopyInputStream(file, position, length);
        }
        return inputStream;
    }

    private long getPositionToInputStream(ByteBuf target, long position, long length) throws IOException {
        initInputStream();
        int transferred = target.writeBytes(inputStream, target.writableBytes());
        if (transferred > 0) {
            position += transferred;
        }

        if (position == this.getContentLength() || transferred < 0) {
            this.state = MultipartState.POST_CONTENT;
            this.close();
        }
        log.info("position:{}, length:{}, transferred:{}", position, length, transferred);
        ReflectUtil.setFieldValue(this, "position", position);
        return transferred;
    }*/

    private long getPositionToChannel(ByteBuf target, FileChannel channel, long position, long length) throws IOException {
        long transferred = Math.min(target.writableBytes(), maxLength - position);

        transferred = target.writeBytes(channel, position, (int) transferred);
        if (transferred > 0) {
            position += transferred;
        }

        if (position >= maxLength) {
            this.state = MultipartState.POST_CONTENT;
            if (channel.isOpen()) {
                channel.close();
            }
        }
        log.info("position:{}, maxLength: {}, length:{}, transferred:{}", position, maxLength, length, transferred);
        ReflectUtil.setFieldValue(this, "position", position);
        return transferred;
    }

    @SneakyThrows
    @Override
    public void close() {
        super.close();
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
