package pp.weiba.thirdparty.baidu.web.api.netdisk.request;

import cn.hutool.core.util.StrUtil;

public enum SortType {

    DESC(1),
    ASC(0);

    public static final SortType DEFULLT = DESC;
    private final Integer value;

    SortType(Integer value) {
        this.value = value;
    }

    public static SortType getByValue(String value) {
        SortType order = DEFULLT;
        if (StrUtil.isNotBlank(value)) {
            for (SortType sortType : SortType.values()) {
                if (sortType.value.equals(value)) {
                    order = sortType;
                    break;
                }
            }
        }
        return order;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
