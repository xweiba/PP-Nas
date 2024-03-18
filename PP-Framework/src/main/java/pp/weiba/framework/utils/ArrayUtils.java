package pp.weiba.framework.utils;

import java.util.List;

/**
 * @author weiba
 * @date 2024/3/18 16:52
 */
public class ArrayUtils {


    /**
     * 集合中是否包含某个类型的元素
     *
     * @param list     集合
     * @param checkObj 检查对象
     * @return 是or否
     * @author weiba
     * @date 2024/3/7 10:37
     */
    private <E> boolean checkListContains(List<E> list, Object checkObj) {
        if (list == null || checkObj == null) {
            return false;
        }
        return list.stream().noneMatch(responseFilter -> responseFilter.getClass().equals(checkObj.getClass()));
    }

}
