package pp.weiba.utils;

/**
 * 数学工具类
 *
 * @author weiba
 * @date 2024/4/1 10:55
 */
public class MathUtils {

    public static long padZero(long value, int targetLength) {
        // countDigits(Long.MAX_VALUE) = 19
        if (targetLength > 19) {
            throw new IllegalArgumentException("Target length is too long");
        }
        int places = targetLength - countDigits(value);  // Assuming you want to pad two 'zeros'
        if (places == 0) {
            return value;
        }
        if (places < 0) {
            throw new IllegalArgumentException("Target length is too short");
        }
        // 通过位运算补齐零位
        long mask = (long) Math.pow(10, places);
        return value * mask;
    }

    public static int countDigits(Number num) {
        if (num.longValue() == 0) {
            return 1;
        }
        return (int) Math.log10(Math.abs(num.longValue())) + 1;
    }

}
