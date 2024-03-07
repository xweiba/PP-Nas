package pp.weiba.framework.resource;

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

    private final Integer type;

    ResourceType(Integer type) {
        this.type = type;
    }

    public static ResourceType get(Integer type) {
        if (type != null) {
            for (ResourceType entity : ResourceType.values()) {
                if (Objects.equals(entity.getType(), type)) {
                    return entity;
                }
            }
        }
        return null;
    }
}
