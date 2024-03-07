package pp.weiba.utils;

import java.util.List;

/**
 * 集合处理工具
 *
 * @author weiba
 * @date 2024/3/7 10:36
 */
public class ListUtils {

    /**
     * 集合中是否包含某个类型的元素
     *
     * @param list     集合
     * @param checkObj 检查对象
     * @return 是or否
     * @author weiba
     * @date 2024/3/7 10:37
     */
    private <T> boolean checkListContains(List<T> list, Object checkObj) {
        if (list == null || list.isEmpty() || checkObj == null) {
            return false;
        }
        return list.stream().noneMatch(responseFilter -> responseFilter.getClass().equals(checkObj.getClass()));
    }

}
