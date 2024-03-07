package pp.weiba.thirdparty.baidu.web.client.adapter;

import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.*;
import org.asynchttpclient.proxy.ProxyServer;
import pp.weiba.thirdparty.baidu.web.client.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * AsyncHttpClient 适配器
 *
 * @author weiba
 * @date 2024/3/6 15:29
 */
@Log4j2
public class AsyncHttpClientAdapter extends AbstractHttpClient<RequestBuilder, Response> {

    private final AsyncHttpClient asyncHttpClient;

    public DefaultAsyncHttpClientConfig.Builder configBuilder;

    public AsyncHttpClientAdapter() {
        super(new HttpTypeAdapter(), new StrJsonTypeReferenceProcessor());
        configBuilder = new DefaultAsyncHttpClientConfig.Builder();
        this.asyncHttpClient = buildClient(configBuilder);
    }

    public AsyncHttpClientAdapter(ITypeReferenceProcessor<String> typeReferenceProcessor) {
        super(new HttpTypeAdapter(), typeReferenceProcessor);
        configBuilder = new DefaultAsyncHttpClientConfig.Builder();
        this.asyncHttpClient = buildClient(configBuilder);
    }

    private AsyncHttpClient buildClient(DefaultAsyncHttpClientConfig.Builder configBuilder) {
        return Dsl.asyncHttpClient(configBuilder);
    }

    public Response execute(RequestBuilder request) {
        ListenableFuture<Response> responseListenableFuture = asyncHttpClient.executeRequest(request);
        try {
            return responseListenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("请求执行失败", e);
            throw new RuntimeException(e);
        }
    }

    static private class HttpTypeAdapter implements IHttpTypeAdapter<RequestBuilder, Response> {

        @Override
        public RequestBuilder adapter(HttpRequest httpRequest) {
            RequestBuilder requestBuilder = new RequestBuilder(httpRequest.getMethod().name(), httpRequest.getDisableUrlEncoding())
                    .setUrl(httpRequest.getUrl())
                    .setBody(httpRequest.getRequestBody())
                    .setRequestTimeout(httpRequest.getTimeout())
                    .setFollowRedirect(httpRequest.getFollowRedirects());

            if (httpRequest.getParams() != null && !httpRequest.getParams().isEmpty()) {
                httpRequest.getParams().forEach((item, value) -> requestBuilder.addQueryParam(item, JSONObject.toJSONString(value)));
            }

            if (httpRequest.getHeaderMap() != null && !httpRequest.getHeaderMap().isEmpty()) {
                httpRequest.getHeaderMap().forEach(requestBuilder::addHeader);
            }

            if (httpRequest.getContentType() != null) {
                requestBuilder.setHeader("Content-Type", httpRequest.getContentType());
            }

            if (httpRequest.getBuildParams() != null && !httpRequest.getBuildParams().isEmpty()) {
                for (Map.Entry<String, Object> item : httpRequest.getBuildParams().entrySet()) {
                    if (item.getKey().equals("proxyServer") && item.getValue().getClass().equals(ProxyServer.class)) {
                        requestBuilder.setProxyServer((ProxyServer) item.getValue());
                    }
                }
            }

            return requestBuilder;
        }

        @Override
        public HttpResponse adapter(Response response) {
            HttpResponse responseAdapter = new HttpResponse()
                    .setStatusCode(response.getStatusCode())
                    .setStatusText(response.getStatusText())
                    .setBody(response.getResponseBody())
                    .setContentType(response.getContentType());

            HttpHeaders headers = response.getHeaders();
            if (headers != null && !headers.isEmpty()) {
                headers.forEach((item) -> responseAdapter.addheader(item.getKey(), item.getValue()));
            }

            List<Cookie> cookies = response.getCookies();
            if (cookies != null && !cookies.isEmpty()) {
                cookies.forEach((item) -> responseAdapter.addheader(item.name(), item.value()));
            }

            return responseAdapter;
        }
    }
}
