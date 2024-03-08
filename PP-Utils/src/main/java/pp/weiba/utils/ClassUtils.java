package pp.weiba.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/10
 **/

public class ClassUtils {
    /**
     * 获取参数最多的方法参数
     *
     * @param clazz      : clazz
     * @param methodName : methodName
     * @return java.util.List<java.lang.String>
     * @author xiaoweiba1028@gmail.com
     * @date 2022/9/22 13:21
     */
    public static List<String> getMethodParams(Class clazz, String methodName) {
        List<String> paramterList = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                Parameter[] params = method.getParameters();
                if (params.length > paramterList.size()) {
                    for (Parameter parameter : params) {
                        paramterList.add(parameter.getName());
                    }
                }
            }
        }
        return paramterList;
    }
}
