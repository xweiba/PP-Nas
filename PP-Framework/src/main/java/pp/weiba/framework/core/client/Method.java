package pp.weiba.framework.core.client;

import lombok.Getter;

import java.util.Objects;

/**
 * @author weiba
 * @date 2024/3/7 9:58
 */
@Getter
public enum Method {
    GET("get"),
    POST("post"),
    HEAD("head"),
    OPTIONS("options"),
    PUT("put"),
    DELETE("delete"),
    TRACE("trace"),
    CONNECT("connect"),
    PATCH("patch");

    private final String type;

    Method(String type) {
        this.type = type;
    }

    public static Method get(String type) {
        if (type != null) {
            for (Method entity : Method.values()) {
                if (Objects.equals(entity.getType(), type)) {
                    return entity;
                }
            }
        }
        return null;
    }
}
