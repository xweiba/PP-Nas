package pp.weiba.thirdparty.baidu.web.client.netdisk.request;

/**
 * 任务类型
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2024/4/1 13:47
 */
public enum TaskExecuteType {

    // 异步
    ASYNC("2"),
    // 同步
    SYNC("1");

    private final String value;

    TaskExecuteType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
