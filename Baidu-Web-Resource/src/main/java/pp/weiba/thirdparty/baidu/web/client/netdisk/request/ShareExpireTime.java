package pp.weiba.thirdparty.baidu.web.client.netdisk.request;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/9/23
 **/

public enum ShareExpireTime {
    FOREVER(0), DAY1(1), DAY7(7), DAY30(30);

    public static final ShareExpireTime DEFULLT = DAY7;
    private final Integer value;

    ShareExpireTime(Integer value) {
        this.value = value;
    }

    public static ShareExpireTime getByValue(Integer value) {
        ShareExpireTime result = DEFULLT;
        if (value != null) {
            for (ShareExpireTime shareFileTime : ShareExpireTime.values()) {
                if (shareFileTime.value.equals(value)) {
                    result = shareFileTime;
                }
            }
        }
        return result;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
