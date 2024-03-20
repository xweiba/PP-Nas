package pp.weiba.thirdparty.baidu.web.api.netdisk.request;

public enum QueryShareOrderType {

    SIZE("size"),
    TIME("time"),
    OTHER("other"),
    FILE_NAME("server_filename");

    public static final QueryShareOrderType DEFULLT = FILE_NAME;
    private final String value;

    QueryShareOrderType(String value) {
        this.value = value;
    }

    public static QueryShareOrderType getByValue(String value) {
        QueryShareOrderType type = DEFULLT;
        for (QueryShareOrderType baiduNetDiskWebQueryFileOrderType : QueryShareOrderType.values()) {
            if (baiduNetDiskWebQueryFileOrderType.value.equals(value)) {
                type = baiduNetDiskWebQueryFileOrderType;
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
