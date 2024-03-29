package pp.weiba.utils;

import org.apache.logging.log4j.Logger;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 日志工具类
 * @date 2023/5/30 21:28
 */
public class Log {

    public static void info(Logger log, String format, Object... args) {
        log.info(JSONUtils.formatJson(formatGetStackMethodName(format), args));
    }

    public static void debug(Logger log, String format, Object... args) {
        log.debug(JSONUtils.formatJson(formatGetStackMethodName(format), args));
    }

    public static void error(Logger log, String format, Object... args) {
        log.error(JSONUtils.formatJson(formatGetStackMethodName(format), args));
    }

    private static String formatGetStackMethodName(String format) {
        return StackTraceUtils.getStackMethodName() + ":\n" + format;
    }

    public static org.apache.logging.log4j.util.Supplier<?> formatJson(String format, Object... args) {
        return formatJson(format, false, args);
    }

    public static org.apache.logging.log4j.util.Supplier<?> formatJson(String format, boolean newline, Object... args) {
        return () -> JSONUtils.formatJson(formatGetStackMethodName(format), newline, args).get();
    }
}
