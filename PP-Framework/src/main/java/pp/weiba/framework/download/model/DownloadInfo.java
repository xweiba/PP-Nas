package pp.weiba.framework.download.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 下载资源信息
 *
 * @author weiba
 * @date 2024/4/11 9:22
 */
@Data
@Accessors(chain = true)
public class DownloadInfo {

    // 下载id
    private String downloadId;

    // 下载地址
    private String downloadUrl;

    // 文件大小
    private Long fileSize;

    // 额外多下载地址， 支持分片的话，可以从多个地址下载实现并发下载加速
    private List<String> downloadUrls;

    /* 下载认证信息 */
    private DownloadAuthInfo authInfo;

}
