package pp.weiba.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/11
 **/

public class ArrayUtils {

    /**
     * 数组是否为空
     *
     * @param <T>   数组元素类型
     * @param array 数组
     * @return 是否为空
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static Object[] argsToJsonStr(boolean newline, Object... args) {
        if (args == null || args.length == 0) return null;
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null || arg instanceof String) continue;
            args[i] = (newline ? "/n" : "") + JSONUtils.toJsonPrettyStr(arg);
        }
        return args;
    }

    public static <T> List<T> asListAndFilterNull(T... params) {
        List<T> result = new ArrayList<>();
        for (T param : params) {
            if (param != null) {
                result.add(param);
            }
        }
        return result;
    }
}
