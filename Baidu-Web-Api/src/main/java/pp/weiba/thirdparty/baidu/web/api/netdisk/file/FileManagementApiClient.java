package pp.weiba.thirdparty.baidu.web.api.netdisk.file;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.api.client.AbstractApiHttpClient;
import pp.weiba.thirdparty.baidu.web.api.client.IHttpClient;
import pp.weiba.thirdparty.baidu.web.api.client.TypeReference;
import pp.weiba.thirdparty.baidu.web.api.netdisk.UrlConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度网盘文件管理API
 *
 * @author weiba
 * @date 2024/3/6 15:17
 */
@Log4j2
public class FileManagementApiClient extends AbstractApiHttpClient {

    public FileManagementApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 创建指定文件夹，支持多层, 存在同名文件夹时服务器会自动添加后缀
     *
     * @param newDstPath : 文件夹路径，以 '/' 开头
     * @return 返回信息
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/11 9:36
     */
    public CreateDirResponse createDir(Map<String, Object> buildParams, String newDstPath) {
        if (StrUtil.isBlank(newDstPath) || !newDstPath.startsWith("/")) {
            String msg = String.format("路径应以 '/' 开头! 错误路径: %s", newDstPath);
            throw new IllegalArgumentException(msg);
        }
        return postExecute(UrlConstants.POST_CREATE_DIR, buildParams, new HashMap<String, Object>() {{
            put("isdir", 1);
            put("block_list", "[]");
            put("path", newDstPath);
        }}, new TypeReference<CreateDirResponse>() {
        });
    }
}
