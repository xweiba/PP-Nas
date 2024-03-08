package pp.weiba.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2023/6/30 9:52
 */
public class MapUtils {

    public static Map<String, String> mergeMap(Map<String, String> dstMap, Map<String, String> addMap, String delimiter) {
        if (addMap == null || CollUtil.isEmpty(addMap)) return null;

        if (dstMap == null) dstMap = new HashMap<>();

        if (StringUtils.isBlank(delimiter)) {
            throw new RuntimeException("分隔符不能为空");
        }

        for (Map.Entry<String, String> addItemTemp : addMap.entrySet()) {
            String key = addItemTemp.getKey();
            String value = addItemTemp.getValue();
            if (dstMap.containsKey(key)) {
                String returnValue = dstMap.get(key).trim();
                if (returnValue.endsWith(delimiter)) {
                    returnValue += delimiter;
                }
                value = returnValue + value + delimiter;
            }
            dstMap.put(key, value);
        }

        return dstMap;
    }
}
