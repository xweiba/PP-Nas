package pp.weiba.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

/**
 * 二维码工具类
 *
 * @author weiba
 * @date 2024/3/26 10:49
 */
public class QRUtils {

    public static String decode(BufferedImage image) throws NotFoundException {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Hashtable<DecodeHintType, Object> qrParam = new Hashtable<>();
        qrParam.put(DecodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        return new MultiFormatReader().decode(binaryBitmap, qrParam).getText();
    }

    public static String decode(String urlString) {
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            is = url.openStream();
            BufferedImage image = ImageIO.read(is);

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void printQr(String text) {
        printQr(text, false);
    }

    public static void printQr(String text, boolean blackBackground) {
        String s = "生成二维码失败";
        int width = 5;  //随便，足够小即可，反正最后不管设置多小，控制台输出的二维码都不会变小了
        int height = 5;
        Hashtable<EncodeHintType, Object> qrParam = new Hashtable<>();
        qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        qrParam.put(EncodeHintType.CHARACTER_SET, "utf-8");
        qrParam.put(EncodeHintType.MARGIN, 1);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, qrParam);
            s = toAscii(bitMatrix, blackBackground);
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(s);
    }

    public static String toAscii(BitMatrix bitMatrix, boolean blackBackground) {
        StringBuilder sb = new StringBuilder();
        for (int rows = 0; rows < bitMatrix.getHeight(); rows++) {
            for (int cols = 0; cols < bitMatrix.getWidth(); cols++) {
                boolean x = bitMatrix.get(rows, cols);
                if (!x) {
                    if (blackBackground) {
                        sb.append("\033[40m   \033[0m");
                    } else {
                        sb.append("\033[107m   \033[0m");
                    }
                } else {
                    if (blackBackground) {
                        sb.append("\033[107m   \033[0m");
                    } else {
                        sb.append("\033[40m   \033[0m");
                    }
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
