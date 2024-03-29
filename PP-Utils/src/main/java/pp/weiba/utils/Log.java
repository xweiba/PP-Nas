package pp.weiba.utils;

import cn.hutool.core.util.StrUtil;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 日志工具类
 * @date 2023/5/30 21:28
 */
public class Log {

    public static void info(Logger log, String format, Object... args) {
        log.info(formatJson(format(format), args));
    }

    public static void debug(Logger log, String format, Object... args) {
        log.debug(formatJson(format(format), args));
    }

    public static void error(Logger log, String format, Object... args) {
        log.error(formatJson(format(format), args));
    }

    private static String format(String format) {
        return StackTraceUtils.getStackMethodName() + ":\n" + format + "\n";
    }

    public static Supplier<?> formatJson(String format, Object... args) {
        return () -> {
            Object[] argsTemp = ArrayUtils.argsToJsonStr(true, args);
            if (argsTemp != null && argsTemp.length > 0) {
                return StrUtil.format(format, argsTemp);
            }
            return format;
        };
    }

}
