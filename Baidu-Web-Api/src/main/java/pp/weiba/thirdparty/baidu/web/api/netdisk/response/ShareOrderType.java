package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import cn.hutool.core.util.StrUtil;

public enum ShareOrderType {

    SIZE("size"),
    TIME("ctime"),
    NAME("name");

    private static final ShareOrderType DEFULLT = TIME;
    private final String value;

    ShareOrderType(String value) {
        this.value = value;
    }

    public static ShareOrderType getByValue(String value) {
        ShareOrderType type = ShareOrderType.DEFULLT;
        if (StrUtil.isNotBlank(value)) {
            for (ShareOrderType shareOrderType : values()) {
                if (shareOrderType.getValue().endsWith(value)) {
                    type = shareOrderType;
                    break;
                }
            }
        }
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
