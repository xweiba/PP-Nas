package pp.weiba.framework.utils;

import pp.weiba.framework.KeyValue;
import pp.weiba.framework.core.CoreConstants;

/**
* 用户信息工具类
*
* @author weiba
* @date 2024/5/28 11:29
*/
public class UserInfoUtils {
    
    /**
     * 获取当前线程用户信息
     *
     * @return
     * @author weiba
     * @date 2024/5/28 11:30
     */
    public static KeyValue getCurrentThreadUserInfo() {
        return ThreadLocalUtils.get(CoreConstants.THREAD_LOCAL_USER_KEY, KeyValue.class);
    }

    /**
     * 设置当前线程用户信息
     *
     * @param value
     * @author weiba
     * @date 2024/5/28 11:32
     */
    public static void setCurrentThreadUserInfo(KeyValue value) {
        ThreadLocalUtils.set(CoreConstants.THREAD_LOCAL_USER_KEY, value);
    }


    public static String getCurrentThreadUserType() {
        return getCurrentThreadUserInfoNotNull().getKey();
    }


    public static String getCurrentThreadUserId() {
        return getCurrentThreadUserInfoNotNull().getValue();
    }

    public static KeyValue getCurrentThreadUserInfoNotNull() {
        KeyValue currentThreadUserInfo = getCurrentThreadUserInfo();
        if (currentThreadUserInfo == null) {
            throw new RuntimeException("请先设置当前登录用户信息");
        }
        return currentThreadUserInfo;
    }
}
