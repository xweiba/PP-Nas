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

    private final String value;

    Method(String value) {
        this.value = value;
    }

    public static Method get(String type) {
        if (type != null) {
            for (Method entity : Method.values()) {
                if (Objects.equals(entity.getValue(), type)) {
                    return entity;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}
