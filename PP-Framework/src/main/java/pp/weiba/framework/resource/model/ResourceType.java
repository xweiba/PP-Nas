package pp.weiba.framework.resource.model;

import lombok.Getter;

import java.util.Objects;

/**
 * 资源类型, 文件或文件夹
 *
 * @author caleb_L
 * @date 2024/3/5 15:25
 */
@Getter
public enum ResourceType {

    FILE(1),

    FOLDER(0);

    private final Integer value;

    ResourceType(Integer value) {
        this.value = value;
    }

    public static ResourceType get(Integer type) {
        if (type != null) {
            for (ResourceType entity : ResourceType.values()) {
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
