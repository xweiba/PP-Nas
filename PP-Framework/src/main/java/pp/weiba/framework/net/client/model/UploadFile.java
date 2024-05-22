package pp.weiba.framework.net.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.utils.model.FileChunk;

import java.io.File;

/**
 * 上传文件信息
 *
 * @author weiba
 * @date 2024/3/21 14:59
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadFile {

    // 待上传文件
    private File file;

    // 分块信息， 如有，则是取文件的指定内容上传
    private FileChunk chunk;

    // 上传方式
    private UploadType uploadType = UploadType.FORM;

}
