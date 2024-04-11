package pp.weiba.service.download.client;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.net.client.model.Method;
import pp.weiba.service.download.client.model.*;
import pp.weiba.utils.ArrayUtils;
import pp.weiba.utils.JSONUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Aria2 Rpc Api 客户端
 *
 * @author weiba
 * @date 2024/4/11 13:48
 */
@Log4j2
public class Aria2RpcApiClient extends AbstractApiHttpClient {

    private final String jsonRpcUrl;

    private final String token;

    public Aria2RpcApiClient(String jsonRpcUrl, String token, IHttpClient httpClient) {
        super(httpClient);
        this.jsonRpcUrl = jsonRpcUrl;
        this.token = token;
    }


    public Aria2JsonRpcResponse<Aria2GlobalDownloadStatusResponse> getGlobalDownloadStatus() {
        return this.executePostHttp(buildRequestParams(Aria2Method.GetGlobalStat), new TypeReference<Aria2JsonRpcResponse<Aria2GlobalDownloadStatusResponse>>() {
        });
    }

    public Aria2JsonRpcResponse<String> add(String url, String path) {
        return batchCreateByOption(Collections.singletonList(url), Aria2TaskOption.builder().dir(path).build());
    }

    public Aria2JsonRpcResponse<String> createByOption(String url, Aria2TaskOption aria2TaskOption) {
        return batchCreateByOption(Collections.singletonList(url), aria2TaskOption);
    }

    private Aria2JsonRpcResponse<String> batchCreateByOption(List<String> peerUrls, Aria2TaskOption aria2TaskOption) {
        return this.executePostHttp(buildRequestParams(Aria2Method.AddUri, peerUrls, aria2TaskOption), new TypeReference<Aria2JsonRpcResponse<String>>() {
        });
    }

    public Aria2JsonRpcResponse<String> forcePause(String gId) {
        return this.executePostHttp(buildRequestParams(Aria2Method.ForcePause, gId), new TypeReference<Aria2JsonRpcResponse<String>>() {
        });
    }

    public Aria2JsonRpcResponse<String> unPause(String gId) {
        return this.executePostHttp(buildRequestParams(Aria2Method.UnPause, gId), new TypeReference<Aria2JsonRpcResponse<String>>() {
        });
    }

    public Aria2JsonRpcResponse<String> forceRemove(String gId) {
        return this.executePostHttp(buildRequestParams(Aria2Method.ForceRemove, gId), new TypeReference<Aria2JsonRpcResponse<String>>() {
        });
    }

    public Aria2JsonRpcResponse<String> removeDownloadResult(String gId) {
        return this.executePostHttp(buildRequestParams(Aria2Method.RemoveDownloadResult, gId), new TypeReference<Aria2JsonRpcResponse<String>>() {
        });
    }

    public Aria2JsonRpcResponse<String> purgeDownloadAllResult() {
        return this.executePostHttp(buildRequestParams(Aria2Method.PurgeDownloadResult), new TypeReference<Aria2JsonRpcResponse<String>>() {
        });
    }

    public Aria2JsonRpcResponse<List<Aria2QueryStatusResponse>> tellActive() {
        return this.executePostHttp(buildRequestParamsOrAddDefaultParamsToStr(Aria2Method.TellActive, true), new TypeReference<Aria2JsonRpcResponse<List<Aria2QueryStatusResponse>>>() {
        });
    }

    public Aria2JsonRpcResponse<List<Aria2QueryStatusResponse>> tellWaiting() {
        return this.tellWaitingByPage(0, 1000);
    }

    /*
     * For example, imagine three downloads "A","B" and "C" are waiting in this order.
     * aria2.tellWaiting(0, 1) returns ["A"].
     * aria2.tellWaiting(1, 2) returns ["B", "C"].
     * aria2.tellWaiting(-1, 2) returns ["C", "B"].  -1 倒叙
     * */
    public Aria2JsonRpcResponse<List<Aria2QueryStatusResponse>> tellWaitingByPage(int offset, int num) {
        return this.executePostHttp(buildRequestParamsOrAddDefaultParamsToStr(Aria2Method.TellWaiting, true, offset, num), new TypeReference<Aria2JsonRpcResponse<List<Aria2QueryStatusResponse>>>() {
        });
    }

    public Aria2JsonRpcResponse<List<Aria2QueryStatusResponse>> tellStopped() {
        return this.tellStoppedByPage(-1, 1000);
    }

    public Aria2JsonRpcResponse<List<Aria2QueryStatusResponse>> tellStoppedByPage(int offset, int num) {
        return this.executePostHttp(buildRequestParamsOrAddDefaultParamsToStr(Aria2Method.TellStopped, true, offset, num), new TypeReference<Aria2JsonRpcResponse<List<Aria2QueryStatusResponse>>>() {
        });
    }

    public Aria2JsonRpcResponse<Aria2TaskDetailResponse> get(String gId) {
        return this.executePostHttp(buildRequestParams(Aria2Method.TellStatus, gId), new TypeReference<Aria2JsonRpcResponse<Aria2TaskDetailResponse>>() {
        });
    }

    public Aria2JsonRpcResponse<Aria2TaskOption> getOption(String gId) {
        return this.executePostHttp(buildRequestParams(Aria2Method.GetOption, gId), new TypeReference<Aria2JsonRpcResponse<Aria2TaskOption>>() {
        });
    }

    public Aria2JsonRpcResponse<String> changeOption(String gId, Aria2TaskOption aria2TaskOption) {
        return this.executePostHttp(buildRequestParams(Aria2Method.ChangeOption, gId, aria2TaskOption), new TypeReference<Aria2JsonRpcResponse<String>>() {
        });
    }

    public Aria2JsonRpcResponse<Aria2GlobalOption> getGlobalOption() {
        return this.executePostHttp(buildRequestParams(Aria2Method.GetGlobalOption), new TypeReference<Aria2JsonRpcResponse<Aria2GlobalOption>>() {
        });
    }

    public Aria2JsonRpcResponse<String> changeGlobalOption(Aria2GlobalOption globalOption) {
        return this.executePostHttp(buildRequestParams(Aria2Method.ChangeGlobalOption, globalOption), new TypeReference<Aria2JsonRpcResponse<String>>() {
        });
    }

    private <T> T executePostHttp(String requestBody, TypeReference<T> responseSuccessType) {
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, jsonRpcUrl).setRequestBody(requestBody);
        return execute(httpRequest, responseSuccessType);
    }

    private String buildRequestParams(Aria2Method method, Object... params) {
        return buildRequestParamsOrAddDefaultParamsToStr(method, false, params);
    }

    private Aria2JsonRpcRequest buildRequestParamsOrAddDefaultParams(Aria2Method method, boolean addDefaultParams, Object... params) {
        Aria2JsonRpcRequest request = Aria2JsonRpcRequest.builder().method(method).build();
        request.initParams(token).addParams(ArrayUtils.asListAndFilterNull(params)).addDefaultParams(addDefaultParams);
        return request;
    }

    private String buildRequestParamsOrAddDefaultParamsToStr(Aria2Method method, boolean addDefaultParams, Object... params) {
        Aria2JsonRpcRequest request = buildRequestParamsOrAddDefaultParams(method, addDefaultParams, params);
        return JSONUtils.toJsonStr(request, "Aria2 Params");
    }


}
