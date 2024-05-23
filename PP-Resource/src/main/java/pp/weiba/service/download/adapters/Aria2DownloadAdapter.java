package pp.weiba.service.download.adapters;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import pp.weiba.framework.download.DownloadException;
import pp.weiba.framework.download.adapters.IDownloadAdapter;
import pp.weiba.framework.download.model.*;
import pp.weiba.framework.model.StatusResultInfo;
import pp.weiba.service.download.client.Aria2RpcApiClient;
import pp.weiba.service.download.client.model.Aria2QueryStatusResponse;
import pp.weiba.service.download.client.model.Aria2TaskOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Aria2 下载适配器
 *
 * @author weiba
 * @date 2024/4/11 13:45
 */
public class Aria2DownloadAdapter implements IDownloadAdapter {

    private final Aria2RpcApiClient aria2RpcApiClient;

    public Aria2DownloadAdapter(Aria2RpcApiClient aria2RpcApiClient) {
        this.aria2RpcApiClient = aria2RpcApiClient;
    }

    @Override
    public List<DownloadTaskInfo> query(DownloadQueryParams params) {
        DownloadStatus status = params.getStatus();
        List<Aria2QueryStatusResponse> responseResults = null;
        switch (status) {
            case ACTIVE:
                responseResults = aria2RpcApiClient.tellActive().getResult();
                break;
            case PAUSE:
                responseResults = aria2RpcApiClient.tellWaiting().getResult();
                break;
            case STOP:
                responseResults = aria2RpcApiClient.tellStopped().getResult();
                break;
            case FINISH:
                responseResults = aria2RpcApiClient.tellStopped().getResult();
                break;
            default:
                throw new RuntimeException("未实现");
        }

        return tranQueryResults(responseResults);
    }

    private static List<DownloadTaskInfo> tranQueryResults(List<Aria2QueryStatusResponse> responseResults) {
        List<DownloadTaskInfo> result = null;
        if (CollUtil.isNotEmpty(responseResults)) {
            result = new ArrayList<>();
            for (Aria2QueryStatusResponse responseResult : responseResults) {
                DownloadTaskInfo task = tranQueryResult(responseResult);
                result.add(task);
            }
        }
        return result;
    }

    private static DownloadTaskInfo tranQueryResult(Aria2QueryStatusResponse responseResult) {
        if (CollUtil.isEmpty(responseResult.getFiles())) {
            throw new DownloadException("文件不能为空！");
        }
        Aria2QueryStatusResponse.FilesResponse file = responseResult.getFiles().get(0);
        List<Aria2QueryStatusResponse.FilesResponse.UrisResponse> uris = file.getUris();
        if (CollUtil.isEmpty(uris)) {
            throw new DownloadException("文件下载地址不能为空！");
        }

        DownloadTaskInfo task = new DownloadTaskInfo().setDownloadTaskId(responseResult.getGid())
                .setStatus(tranStatus(responseResult.getStatus()))
                .setCompletedSize(responseResult.getTotalLength())
                .setUploadSpeed(Long.valueOf(responseResult.getUploadSpeed()))
                .setDownloadSpeed(Long.valueOf(responseResult.getDownloadSpeed()));

        Set<String> urls = uris.stream().map(Aria2QueryStatusResponse.FilesResponse.UrisResponse::getUri)
                .collect(Collectors.toSet());
        DownloadInfo downloadInfo = new DownloadInfo()
                .setDownloadUrl(uris.get(0).getUri())
                .setFileSize(responseResult.getTotalLength())
                .setDownloadUrls(urls);
        task.setDownloadInfo(downloadInfo)
        ;
        String fileDstPath = file.getPath();
        if (StrUtil.isNotBlank(fileDstPath)) {
            int lastIndex = fileDstPath.lastIndexOf("/") + 1;
            DownloadTaskOptionInfo option = new DownloadTaskOptionInfo()
                    .setDownloadName(fileDstPath.substring(lastIndex))
                    .setDstDirPath(fileDstPath.substring(0, lastIndex));
            task.setOption(option);
        }
        return task;
    }

    public static DownloadStatus tranStatus(String status) {
        switch (status) {
            case "active":
                return DownloadStatus.ACTIVE;
            case "paused":
                return DownloadStatus.PAUSE;
            case "stopped":
                return DownloadStatus.STOP;
            default:
                throw new RuntimeException("未实现");
        }
    }

    @Override
    public DownloadTaskInfo add(AddDownloadTaskInfo addDownloadTaskInfo) {
        aria2RpcApiClient.add(addDownloadTaskInfo.getDownloadInfo().getDownloadUrl(), tranToAria2TaskOption(addDownloadTaskInfo));
        return null;
    }

    private Aria2TaskOption tranToAria2TaskOption(AddDownloadTaskInfo addDownloadTaskInfo) {
        DownloadTaskOptionInfo option = addDownloadTaskInfo.getOption();
        DownloadInfo downloadInfo = addDownloadTaskInfo.getDownloadInfo();

        List<String> headers = null;
        if (downloadInfo.getAuthInfo() != null && CollUtil.isNotEmpty(downloadInfo.getAuthInfo().getHeaderMap())) {
            headers = new ArrayList<>();
            for (Map.Entry<String, String> item :downloadInfo.getAuthInfo().getHeaderMap().entrySet()){
                headers.add(item.getKey() + ":" + item.getValue());
            }

        }


        Aria2TaskOption aria2TaskOption = new Aria2TaskOption()
                .setDir(option.getDstDirPath())
                .setOut(option.getDownloadName())
                .setHeader(headers)
                ;
        return aria2TaskOption;
    }

    @Override
    public DownloadTaskInfo get(String downloadTaskId) {
        return null;
    }

    @Override
    public List<DownloadProgressInfo> getProgress(List<String> downloadTaskIds) {
        return null;
    }

    @Override
    public List<StatusResultInfo> changeStatus(List<ChangeDownloadTaskStatus> changeDownloadTaskStatuses) {
        return null;
    }


}
