package pp.weiba.thirdparty.baidu.web.resource.client.ahc;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.*;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.multipart.FilePart;
import org.asynchttpclient.request.body.multipart.InputStreamPart;
import org.asynchttpclient.request.body.multipart.Part;
import pp.weiba.framework.core.client.*;
import pp.weiba.framework.core.convert.ITypeReferenceProcessor;
import pp.weiba.framework.core.convert.StrJsonTypeReferenceProcessor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
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

    public Response doExecute(RequestBuilder request) {
        ListenableFuture<Response> responseListenableFuture = asyncHttpClient.executeRequest(request);
        try {
            return responseListenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("请求执行失败", e);
            throw new RuntimeException(e);
        }
    }

    private static void multipartFormData(UploadFileChunk chunk, RequestBuilder requestBuilder, File file) {
        if (chunk == null) {
            requestBuilder.addBodyPart(new FilePart(file.getName(), file));
        } else {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                byte[] buffer = new byte[(int) chunk.getLength()];
                randomAccessFile.seek(chunk.getStart());
                randomAccessFile.readFully(buffer);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
                requestBuilder.addBodyPart(new InputStreamPart("file", byteArrayInputStream, file.getName(), chunk.getLength()));
            } catch (IOException e) {
                log.error("文件上传自定义零拷贝异常：{}", ExceptionUtil.getMessage(e));
                throw new RuntimeException("文件上传自定义零拷贝异常！", e);
            }
        }
    }

    private static void customizeMultipartFormData(File file, UploadFileChunk chunk, RequestBuilder requestBuilder, HttpHeaders headers) {
        List<Part> parts = new ArrayList<>();
        if (chunk == null) {
            chunk = new UploadFileChunk(0, file.getAbsoluteFile().length(), 0);
        }
        FilePart filePart = new FileChunkPart(file.getName(), file, chunk);
        parts.add(filePart);
        requestBuilder.resetFormParams();
        requestBuilder.resetNonMultipartData();
        MultipartFormData multipartFormData = CustomizeMultipartUtils.buildMultipartFormData(headers);
        headers.add("Content-Type", multipartFormData.getContentType());
        headers.add("Connection", "keep-alive");
        requestBuilder.setBody(new FileMultipartChunkBodyGenerator(parts, multipartFormData));
    }

    static private class HttpTypeAdapter implements IHttpTypeAdapter<RequestBuilder, Response> {

        @Override
        public RequestBuilder adapter(HttpRequest request) {
            HttpHeaders headers = new DefaultHttpHeaders();
            RequestBuilder requestBuilder = new RequestBuilder(request.getMethod().name(), request.isDisableUrlEncoding())
                    .setUrl(request.getUrl())
                    .setBody(request.getRequestBody())
                    .setHeaders(headers)
                    .setRequestTimeout(request.getTimeout())
                    .setFollowRedirect(request.isFollowRedirect());

            if (request.getRequestParams() != null && !request.getRequestParams().isEmpty()) {
                request.getRequestParams().forEach((item, value) -> requestBuilder.addFormParam(item, value instanceof String ? (String) value : JSONObject.toJSONString(value)));
            }

            if (request.getHeaderMap() != null && !request.getHeaderMap().isEmpty()) {
                // 必须将Cookie单独设置，否则可能失效
                buildCookies(request.getHeaderMap(), requestBuilder);
                request.getHeaderMap().forEach(headers::add);
            }

            if (request.getContentType() != null) {
                headers.add("Content-Type", request.getContentType());
            }

            if (request.getBuildParams() != null && !request.getBuildParams().isEmpty()) {
                for (Map.Entry<String, Object> item : request.getBuildParams().entrySet()) {
                    if (item.getKey().equals("proxyServer") && item.getValue().getClass().equals(ProxyServer.class)) {
                        requestBuilder.setProxyServer((ProxyServer) item.getValue());
                    }
                }
            }

            if (request.getUploadFile() != null && request.getUploadFile().getFile() != null) {
                File file = request.getUploadFile().getFile();
                UploadFileChunk chunk = request.getUploadFile().getChunk();
                customizeMultipartFormData(file, chunk, requestBuilder, headers);
                //                multipartFormData(chunk, requestBuilder, file);
            }

            if (log.isDebugEnabled()) {
                log.debug("execute adapterRequest: {}", requestBuilder.build().toString());
            }
            return requestBuilder;
        }

        private Object buildUploadFile(UploadFile uploadFile) {
            return null;
        }

        private void buildCookies(Map<String, String> headerMap, RequestBuilder requestBuilder) {
            String cookie = headerMap.get("Cookie");
            if (StrUtil.isNotBlank(cookie)) {
                cookie = cookie.trim();
                while (true) {
                    int i = cookie.indexOf(";");
                    if (i <= 0) break;
                    String cookieTemp = cookie.substring(0, i);
                    int j = cookieTemp.indexOf("=");
                    if (j > 0) {
                        String key = cookieTemp.substring(0, j);
                        String value = cookieTemp.substring(j + 1);
                        requestBuilder.addCookie(new DefaultCookie(key, value));
                    }
                    cookie = cookie.substring(i + 1);
                }
                headerMap.remove("Cookie");
            }
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
                cookies.forEach((item) -> responseAdapter.addCookies(item.name(), item.value()));
            }

            return responseAdapter;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        asyncHttpClient.close();
    }
}
