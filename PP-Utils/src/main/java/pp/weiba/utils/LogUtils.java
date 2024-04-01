package pp.weiba.utils;

import org.apache.logging.log4j.Logger;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 日志工具类
 * @date 2023/5/30 21:28
 */
public class LogUtils {

    public static void info(Logger log, String format, Object... args) {
        log.info(formatJson(format, args));
    }

    public static void info(Logger log, Object... args) {
        String format = builderDefaultFormatStr(args);
        log.info(formatJson(format, args));
    }

    private static String builderDefaultFormatStr(Object[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("args 不能为空");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append("{} ");
        }
        return sb.toString().trim();
    }

    public static void debug(Logger log, String format, Object... args) {
        log.debug(formatJson(format, args));
    }

    public static void debug(Logger log, Object... args) {
        log.debug(formatJson("{}", args));
    }

    public static void error(Logger log, String format, Object... args) {
        log.error(formatJson(format, args));
    }

    public static void error(Logger log, Object... args) {
        log.error(formatJson("{}", args));
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
