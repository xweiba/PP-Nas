package pp.weiba.framework.net.client.model;

import cn.hutool.core.util.StrUtil;
import pp.weiba.framework.resource.model.SortType;

public enum UploadType {

    // 表单
    FORM("form"),
    // 字节
    BYTE("byte");

    public static final UploadType DEFULLT = FORM;

    private final String value;

    UploadType(String value) {
        this.value = value;
    }

    public static UploadType getByValue(String value) {
        UploadType type = DEFULLT;
        if (StrUtil.isNotBlank(value)) {
            for (UploadType temp : UploadType.values()) {
                if (temp.value.equals(value)) {
                    type = temp;
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
