package pp.weiba.framework.resource;

public enum OrderType {

    SIZE("size"),
    TIME("time"),
    NAME("name");

    private final String value;

    OrderType(String value) {
        this.value = value;
    }

    public static OrderType getByValue(String value) {
        for (OrderType orderType : OrderType.values()) {
            if (orderType.value.equals(value)) {
                return orderType;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
