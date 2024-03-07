package pp.weiba.framework;

import lombok.Getter;

import java.util.Objects;

/**
 * 签名类型
 *
 * @author caleb_L
 * @date 2024/3/5 15:51
 */
@Getter
public enum DigestType {
    SHA1(1),
    MD5(2);

    private final Integer type;

    DigestType(Integer type) {
        this.type = type;
    }

    public static DigestType get(Integer type) {
        if (type != null) {
            for (DigestType entity : DigestType.values()) {
                if (Objects.equals(entity.getType(), type)) {
                    return entity;
                }
            }
        }
        return null;
    }
}
