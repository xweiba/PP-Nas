package pp.weiba.framework.download.model;

import lombok.Getter;

/**
 * 下载状态
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2024/4/11 9:57
 */
@Getter
public enum DownloadStatus {

    ACTIVE("Active"),
    PAUSE("Pause"),
    STOP("Stop"),
    FINISH("Finish");

    private final String value;

    DownloadStatus(String value) {
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
