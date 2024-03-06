package pp.weiba.thirdparty.baidu.web.client;

import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.*;
import org.asynchttpclient.filter.RequestFilter;
import org.asynchttpclient.filter.ResponseFilter;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author weiba
 * @date 2024/3/6 15:29
 */
@Log4j2
public abstract class AbstractAsyncHttpClient implements IHttpClient {

    private final AsyncHttpClient asyncHttpClient;

    public AbstractAsyncHttpClient() {
        this.asyncHttpClient = Dsl.asyncHttpClient();
    }

    public void addRequestFilter(RequestFilter filter) {
        List<RequestFilter> requestFilters = asyncHttpClient.getConfig().getRequestFilters();
        if (checkFiltersContains(requestFilters, filter)) {
            requestFilters.add(filter);
        }
    }

    public void addResponseFilter(ResponseFilter filter) {
        List<ResponseFilter> responseFilters = asyncHttpClient.getConfig().getResponseFilters();
        if (checkFiltersContains(responseFilters, filter)) {
            responseFilters.add(filter);
        }
    }

    private <T> boolean checkFiltersContains(List<T> filters, Object filter) {
        return filters.stream().noneMatch(responseFilter -> responseFilter.getClass().equals(filter.getClass()));
    }

    public Response syncExecuteRequest(RequestBuilder request) {
        ListenableFuture<Response> responseListenableFuture = asyncHttpClient.executeRequest(request);
        try {
            return responseListenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("请求执行失败", e);
            throw new RuntimeException(e);
        }
    }

}
