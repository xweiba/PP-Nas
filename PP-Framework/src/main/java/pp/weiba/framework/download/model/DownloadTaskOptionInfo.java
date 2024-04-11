package pp.weiba.framework.download.model;

import lombok.Data;

/**
 * 下载任务配置信息
 *
 * @author weiba
 * @date 2024/4/11 9:48
 */
@Data
public class DownloadTaskOptionInfo {

    // 下载文件名
    private String downloadName;

    // 下载文件夹
    private String dstDirPath;

    /* 最大并发连接数 */
    private String maxConnectionCount;

    /* 分片大小 */
    private String piecelength;

    /* 任务最高速度下载速度 */
    private String maxDownloadLimit;

    /* 任务最高上传速度 */
    private String maxUploadLimit;

    /* 下载超时时间 */
    private String timeout;

    /* 断点续传 */
    private boolean isContinue;

}
