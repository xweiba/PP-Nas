package pp.weiba.framework.security.authentication.qr;

public enum ScanQRStatusType {

    NEW("NEW"), // 二维码未扫描,初始化状态
    SCANED("SCANED"), // 二维码已扫描，请确认登录
    EXPIRED("EXPIRED"), // 二维码已失效，请刷新并扫描新二维码
    CANCELED("CANCELED"), // 二维码已扫描，但取消登录，请刷新并扫描新二维码
    CONFIRMED("CONFIRMED"); // 已扫码并登录

    public static final ScanQRStatusType DEFULLT = NEW;
    private final String value;

    ScanQRStatusType(String value) {
        this.value = value;
    }

    public static ScanQRStatusType getByValue(String value) {
        ScanQRStatusType type = DEFULLT;
        for (ScanQRStatusType typeTemp : ScanQRStatusType.values()) {
            if (typeTemp.value.equals(value)) {
                type = typeTemp;
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
