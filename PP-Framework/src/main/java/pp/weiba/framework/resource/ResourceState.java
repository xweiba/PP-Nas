package pp.weiba.framework.resource;

import lombok.Getter;

/**
 * 资源状态
 *
 * @author caleb_L
 * @date 2024/3/5 15:50
 */

@Getter
public enum ResourceState {

    NORMAL(0), // 正常
    RECYCLE(1), // 回收
    DELETE(2) // 删除
    ;

    private final Integer type;

    ResourceState(Integer type) {
        this.type = type;
    }

    public static ResourceState get(Integer type) {
        if (type != null) {
            for (ResourceState entity : ResourceState.values()) {
                if (entity.getType() == type) {
                    return entity;
                }
            }
        }
        return null;
    }
}
