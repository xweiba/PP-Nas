package pp.weiba.utils;

/**
 * @author xiaoweiba1028@gmail.com
 * @description
 * @date 2023/6/7 22:58
 */
public class CpuUtils {

    public static int availableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }
}
