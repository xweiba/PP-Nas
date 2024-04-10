package pp.weiba.framework.resource.model;

import lombok.Getter;

import java.util.Objects;

/**
 * 资源状态
 *
 * @author caleb_L
 * @date 2024/3/5 15:50
 */

@Getter
public enum ResourceStatus {

    NORMAL(0), // 正常
    RECYCLE(1), // 回收
    DELETE(2) // 删除
    ;

    private final Integer type;

    ResourceStatus(Integer type) {
        this.type = type;
    }

    public static ResourceStatus get(Integer type) {
        if (type != null) {
            for (ResourceStatus entity : ResourceStatus.values()) {
                if (Objects.equals(entity.getType(), type)) {
                    return entity;
                }
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return type.toString();
    }


}
