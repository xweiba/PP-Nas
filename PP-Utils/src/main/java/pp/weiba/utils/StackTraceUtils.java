package pp.weiba.utils;

/**
 * @author xiaoweiba1028@gmail.com
 * @description
 * @date 2023/5/30 22:16
 */
public class StackTraceUtils {

    public static String getStackMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String stackMethodName = "";
        int stackMethodNameDepth = 0;
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackMethodNameDepth > 3) break;
            String className = stackTraceElement.getClassName();
            if (className.equals("java.lang.Thread") || className.startsWith("weiba.pp.framework.utils")) {
                continue;
            } else {
                stackMethodName = stackTraceElement.getFileName().split("\\.")[0] + "." + stackTraceElement.getMethodName() + "()-" + stackMethodName;
            }
            stackMethodNameDepth++;
        }
        return stackMethodName.substring(0, stackMethodName.length() - 1);
    }

}
