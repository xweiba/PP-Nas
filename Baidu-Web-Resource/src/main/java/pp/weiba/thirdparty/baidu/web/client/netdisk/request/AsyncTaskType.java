package pp.weiba.thirdparty.baidu.web.client.netdisk.request;

/**
 * 异步任务类型
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2024/4/1 13:47
 */
public enum AsyncTaskType {

    DELETE("delete"),
    MOVE("move"),
    COPY("copy"),
    RENAME("rename");

    private final String value;

    AsyncTaskType(String value) {
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
