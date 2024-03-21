package pp.weiba.thirdparty.baidu.web.resource.client;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.*;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.FilePart;
import pp.weiba.framework.core.client.*;
import pp.weiba.framework.core.convert.ITypeReferenceProcessor;
import pp.weiba.framework.core.convert.StrJsonTypeReferenceProcessor;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static java.nio.charset.StandardCharsets.UTF_8;

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

    static private class HttpTypeAdapter implements IHttpTypeAdapter<RequestBuilder, Response> {

        @Override
        public RequestBuilder adapter(HttpRequest request) {
            RequestBuilder requestBuilder = new RequestBuilder(request.getMethod().name(), request.isDisableUrlEncoding())
                    .setUrl(request.getUrl())
                    .setBody(request.getRequestBody())
                    .setRequestTimeout(request.getTimeout())
                    .setFollowRedirect(request.isFollowRedirect());

            if (request.getRequestParams() != null && !request.getRequestParams().isEmpty()) {
                request.getRequestParams().forEach((item, value) -> requestBuilder.addFormParam(item, value instanceof String ? (String) value : JSONObject.toJSONString(value)));
            }

            if (request.getHeaderMap() != null && !request.getHeaderMap().isEmpty()) {
                // 必须将Cookie单独设置，否则可能失效
                buildCookies(request.getHeaderMap(), requestBuilder);
                request.getHeaderMap().forEach(requestBuilder::addHeader);
            }

            if (request.getContentType() != null) {
                requestBuilder.setHeader("Content-Type", request.getContentType());
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
                if (chunk == null) {
                    requestBuilder.addBodyPart(new FilePart("file", file, "application/octet-stream", UTF_8));
                } else {
                    // 自定义零拷贝
                    try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
                        FileChannel channel = randomAccessFile.getChannel();
                        BodyGenerator bodyGenerator = new BodyGenerator() {
                            @Override
                            public Body createBody() {
                                return new FileBody(channel, chunk.getStart(), chunk.getLength());
                            }
                        };
                        requestBuilder.setBody(bodyGenerator);
                    } catch (IOException e) {
                        log.error("文件上传自定义零拷贝异常：{}", ExceptionUtil.getMessage(e));
                        throw new RuntimeException("文件上传自定义零拷贝异常！", e);
                    }
                }
                requestBuilder.addHeader("contentType", "application/octet-stream");
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

    private static class FileBody implements Body {

        private final FileChannel fileChannel;
        private final long position;
        private final long length;
        private long transferred; // 追踪已传输的字节

        public FileBody(FileChannel fileChannel, long position, long length) {
            this.fileChannel = fileChannel;
            this.position = position;
            this.length = length;
            this.transferred = 0; // 初始化时未传输任何字节
        }

        @Override
        public long getContentLength() {
            return length;
        }

        @Override
        public BodyState transferTo(ByteBuf byteBuf) throws IOException {
            long remaining = length - transferred; // 剩余需要传输的字节
            long toTransfer = Math.min(remaining, byteBuf.writableBytes()); // 实际传输的字节不能超过byteBuf的可写空间
            int i = byteBuf.writeBytes(fileChannel, position + transferred, (int) toTransfer);
            transferred += i; // 更新已传输的字节总数
            return transferred == length ? BodyState.STOP : BodyState.CONTINUE; // 如果全部传输完成，则停止
        }

        // 覆盖了 close() 方法，但不做任何操作，因为FileChannel的关闭应由创建它的代码来管理
        @Override
        public void close() throws IOException {
            // FileChannel 的关闭应由外部代码控制，这里不关闭
        }
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        asyncHttpClient.close();
    }
}
