package pp.weiba.thirdparty.baidu.web.client.netdisk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.HttpClientApiException;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.net.client.model.HttpResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.request.AsyncTaskType;
import pp.weiba.thirdparty.baidu.web.client.netdisk.request.TaskExecuteType;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.AsyncTaskResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.CreateDirResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.FileDetailByFSIdResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.FileOperaAsyncTaskResponse;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 百度网盘文件管理API
 *
 * @author weiba
 * @date 2024/3/6 15:17
 */
@Log4j2
public class FileOperationApiClient extends AbstractApiHttpClient {

    // dlink 大于20Mb的需要添加 User-Agent: pan.baidu.com 和 BDUSS Cookie 才能下载，小于20Mb的不能添加, 它不是真实下载地址，推荐追踪302获取到真实下载地址再推给下载器
    public static Long LARGE_MAX_SIZE = 20971520L;

    // 超过阀值
    public static String LARGE_FILE_USER_AGENT = "pan.baidu.com";


    public FileOperationApiClient(IHttpClient httpClient) {
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
    public CreateDirResponse createDir(String newDstPath) {
        if (StrUtil.isBlank(newDstPath) || !newDstPath.startsWith("/")) {
            String msg = String.format("路径应以 '/' 开头! 错误路径: %s", newDstPath);
            throw new IllegalArgumentException(msg);
        }
        return postExecute(UrlConstants.POST_CREATE_DIR, new HashMap<String, Object>() {{
            put("isdir", 1);
            put("block_list", "[]");
            put("path", newDstPath);
        }}, new TypeReference<CreateDirResponse>() {
        });
    }


    /**
     * 根据FsIds获取资源信息
     *
     * @param fsIds 资源id
     * @return 资源信息
     * @author weiba
     * @date 2024/4/1 13:43
     */
    public FileDetailByFSIdResponse getFileInfoByFsIds(String fsIds) {
        return postExecute(XpanUrlConstants.POST_FILE_MULTIMEDIA, new HashMap<String, Object>() {{
            put("method", "filemetas");
            put("dlink", 1);
            put("fsids", Arrays.toString(fsIds.split(",")));
        }}, new TypeReference<FileDetailByFSIdResponse>() {
        });
    }

    /**
     * 获取不需要认证的Url
     *
     * @param dLinkUrl 接口返回的url
     * @param fileSize 文件大小
     * @return
     * @author weiba
     * @date 2024/4/11 10:49
     */
    public HttpResponse getRealDownloadUrl(String dLinkUrl, Long fileSize) {
        if (StrUtil.isBlank(dLinkUrl) || fileSize == null || fileSize <= 0) {
            throw new IllegalArgumentException("参数不能为空");
        }
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(dLinkUrl);
        if (fileSize >= LARGE_MAX_SIZE) {
            httpRequest.addheader("User-Agent", LARGE_FILE_USER_AGENT);
        }

        HttpResponse response = executeResponse(httpRequest);
        String realDownloadUrl = response.getHeaderMap().get("Location");
        if (StrUtil.isBlank(realDownloadUrl)) {
            throw new HttpClientApiException("无需鉴权下载地址获取失败");
        }
        return response;
    }



    /**
     * 文件操作异步接口
     *
     * @param opera     任务类型
     * @param executeType 任务执行类型 1:同步 2:异步
     * @param paramsMap 参数
     * @return 任务新增结果
     * @author weiba
     * @date 2024/4/1 13:58
     */
    public <T> FileOperaAsyncTaskResponse<T> fileOperaAsyncTask(AsyncTaskType opera, TaskExecuteType executeType, HashMap<String, Object> paramsMap) {
        if (opera == null || CollUtil.isEmpty(paramsMap)) {
            throw new IllegalArgumentException("参数不能为空");
        }
        return postExecute(paramsMap, new TypeReference<FileOperaAsyncTaskResponse<T>>() {
        }, UrlConstants.POST_ASYNC_FILE_MANAGER, opera.getValue(), executeType.getValue());
    }

    /**
     * 查询异步任务结果
     *
     * @param taskId 任务id
     * @return 任务结果
     * @author weiba
     * @date 2024/4/1 13:58
     */
    public <T> AsyncTaskResponse<T> getAsyncTaskResult(Long taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("taskId 不能为空");
        }
        return execute(UrlConstants.GET_SYNC_TASK, new HashMap<String, String>() {{
            put("taskid", String.valueOf(taskId));
        }}, new TypeReference<AsyncTaskResponse<T>>() {
        });
    }

}
