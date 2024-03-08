package pp.weiba.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 输入输出流工具
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2023/6/29 13:58
 */
public class IOUtils {


    public static byte[] inputStreamMultiPartByReda(InputStream inputStream, int multiPartSize) throws IOException {
        byte[] partialByte = new byte[multiPartSize];
        inputStream.read(partialByte, 0, multiPartSize);
        return partialByte;
    }
}
