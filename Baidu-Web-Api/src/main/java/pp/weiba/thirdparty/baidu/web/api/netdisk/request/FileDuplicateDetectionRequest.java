package pp.weiba.thirdparty.baidu.web.api.netdisk.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

/**
 * 文件重复检测参数
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2023/7/5 10:34
 */
@Log4j2
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileDuplicateDetectionRequest {

    /* 目标绝对路径 */
    private String dstFilePath;

    /* 目标文件夹 */
    private String dstDirPath;

    /* 文件大小 */
    private Long fileSize;

    /* 文件最后修改时间 */
    private Integer updateTime;

    /* 文件md5 */
    private String fileMd5;

    /* 文件前256kb的md5 */
    private String sliceMd5;

    /* 当前时间 */
    private int dataTime;

    /* 文件md5 和 dataTime 通过算法算出的偏移量 */
    private int dataOffset;

    /* 文件从dataOffset开始往后的256Kb内容，base64后字符串 */
    private String dataContentBase64;
}
