package pp.weiba.framework.utils;

import cn.hutool.core.util.ClassUtil;

import java.util.HashMap;
import java.util.Map;

/**
* ThreadLocal 工具类
*
* @author weiba
* @date 2024/5/27 17:11
*/
public class ThreadLocalUtils {

    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal();
    
    /**
     * 保存
     *
     * @param key
     * @param value
     * @author weiba
     * @date 2024/5/27 17:12
     */
    public static void set(String key, Object value) {
        String saveKey = getSaveKey(key, value.getClass());
        Map<String, Object> valueMap = threadLocal.get();
        if (valueMap == null) {
            synchronized (threadLocal) {
                valueMap = new HashMap<>();
                threadLocal.set(valueMap);
            }
        }
        valueMap.put(saveKey, value);
    }

    /**
     * 获取存储的value
     *
     * @param name
     * @return
     * @author weiba
     * @date 2024/5/28 11:14
     */
    public static <T> T get(String key, Class<T> clazz) {
        String saveKey = getSaveKey(key, clazz);
        Map<String, Object> threadLocalMap = threadLocal.get();
        if (threadLocalMap != null && threadLocalMap.get(saveKey) != null) {
            return (T) threadLocalMap.get(saveKey);
        }
        return null;
    }

    private static String getSaveKey(String key, Class<?> aClass) {
        return ClassUtil.getClassName(aClass, false)+ ":" + key;
    }
    
}
