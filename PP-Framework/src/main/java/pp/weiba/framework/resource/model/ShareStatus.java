package pp.weiba.framework.resource.model;

import lombok.Getter;

import java.util.Objects;

/**
 * 分享状态
 *
 * @author weiba
 * @date 2024/4/9 9:44
 */
@Getter
public enum ShareStatus {

    NORMAL(0), // 正常
    EXPIRE(-1) // 过期
    ;

    private final Integer value;

    ShareStatus(Integer value) {
        this.value = value;
    }

    public static ShareStatus get(Integer type) {
        if (type != null) {
            for (ShareStatus entity : ShareStatus.values()) {
                if (Objects.equals(entity.getValue(), type)) {
                    return entity;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
