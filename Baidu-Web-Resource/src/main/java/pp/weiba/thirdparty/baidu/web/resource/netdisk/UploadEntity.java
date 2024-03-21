package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.io.File;

/**
 * 上传实体信息
 *
 * @author weiba
 * @date 2024/3/21 11:28
 */
@Log4j2
@Data
@Accessors(chain = true)
public class UploadEntity {

    private String dstFilePath;

    private String dstDirPath;

    private String uk;

    private File file;

}
