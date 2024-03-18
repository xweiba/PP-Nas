package pp.weiba.framework.utils;

import pp.weiba.framework.core.handler.IHandler;

/**
 * 处理器工具类
 *
 * @author weiba
 * @date 2024/3/18 16:37
 */
public class HandlerUtils {

    /**
     * 将处理器添加到链尾
     *
     * @param newHandler 待添加处理器
     * @author weiba
     * @date 2024/3/18 16:33
     */
    public static <T> void addHandlerToEnd(IHandler<T> current, IHandler<T> newHandler, boolean isFilterDuplicates) {
        if (current == null || newHandler == null) return;
        while (true) {
            if (isFilterDuplicates && (current == newHandler || current.getClass().equals(newHandler.getClass()))) {
                // 过滤重复的处理器
                break;
            }
            if (current.getNext() == null) {
                current.setNext(newHandler);
                break;
            }
            current = current.getNext();
        }
    }

}
