package pp.weiba.thirdparty.baidu.web.resource.client;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlPath;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.platform.commons.util.StringUtils;
import pp.weiba.framework.core.client.AbstractHttpClient;
import pp.weiba.framework.core.client.IHttpTypeAdapter;
import pp.weiba.framework.core.convert.StrJsonTypeReferenceProcessor;

import java.net.HttpCookie;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Hutools 客户端
 *
 * @author weiba
 * @date 2024/3/8 15:54
 */
@Log4j2
public class HutoolHttpClientAdapter extends AbstractHttpClient<HttpRequest, HttpResponse> {

    public static final int TIME_OUT = 5000;

    public HutoolHttpClientAdapter() {
        super(new HttpTypeAdapter(), new StrJsonTypeReferenceProcessor());
    }

    private static UrlBuilder getUrlBuilder(String url, boolean isFormUrlEncoded) {
        Assert.notBlank(url, "Url must be not blank!");
        URL urlTemp = URLUtil.url(isFormUrlEncoded ? StrUtil.replace(StrUtil.trim(url), "+", "%2B") : StrUtil.trim(url));
        Charset charset = CharsetUtil.CHARSET_UTF_8;
        return UrlBuilder.of(urlTemp.getProtocol(), urlTemp.getHost(), urlTemp.getPort(),
                UrlPath.of(urlTemp.getPath(), charset),
                UrlQuery.of(urlTemp.getQuery(), charset, false, isFormUrlEncoded), urlTemp.getRef(), charset);
    }

    private static Method tranMethod(String methodName) {
        if (StrUtil.isBlank(methodName)) return Method.GET;
        String upperCase = methodName.toUpperCase();
        switch (upperCase) {
            case "GET":
                return Method.GET;
            case "POST":
                return Method.POST;
            case "PUT":
                return Method.PUT;
            case "DELETE":
                return Method.DELETE;
            case "HEAD":
                return Method.HEAD;
            case "OPTIONS":
                return Method.OPTIONS;
            case "TRACE":
                return Method.TRACE;
            default:
                return Method.GET;
        }
    }

    @Override
    protected HttpResponse execute(HttpRequest request) {
        return request.execute();
    }

    static private class HttpTypeAdapter implements IHttpTypeAdapter<HttpRequest, HttpResponse> {

        @Override
        public HttpRequest adapter(pp.weiba.framework.core.client.HttpRequest request) {
            log.debug("execute request: {}", request);
            HttpRequest httpRequest = new HttpRequest(getUrlBuilder(request.getUrl(), !request.isDisableUrlEncoding()));
            if (StringUtils.isNotBlank(request.getRequestBody())) {
                httpRequest.body(request.getRequestBody(), request.getContentType());
            }
            if (CollUtil.isNotEmpty(request.getRequestParams())) {
                httpRequest.form(request.getRequestParams());
            }
            httpRequest.setMethod(tranMethod(request.getMethod().getType()));
            httpRequest.headerMap(request.getHeaderMap(), true);
            if (request.isFollowRedirect()) {
                httpRequest.setFollowRedirects(request.isFollowRedirect());
                httpRequest.setMaxRedirectCount(request.getMaxRedirectCount());
                httpRequest.setFollowRedirectsCookie(request.isFollowRedirectsCookie());
            }
            if (request.getTimeout() == 0) {
                request.setTimeout(TIME_OUT);
            }

            if (request.getContentType() != null) {
                httpRequest.header("Content-Type", request.getContentType());
            }

            if (request.getBuildParams() != null && !request.getBuildParams().isEmpty()) {
                for (Map.Entry<String, Object> item : request.getBuildParams().entrySet()) {
                    if (item.getKey().equals("proxyServer") && item.getValue().getClass().equals(Proxy.class)) {
                        httpRequest.setProxy((Proxy) item.getValue());
                    }
                }
            }

            if (log.isDebugEnabled()) {
                log.debug("execute adapterRequest: {}", JSONUtil.toJsonStr(httpRequest));
            }
            return httpRequest;
        }

        @Override
        public pp.weiba.framework.core.client.HttpResponse adapter(HttpResponse response) {
            pp.weiba.framework.core.client.HttpResponse responseAdapter = new pp.weiba.framework.core.client.HttpResponse()
                    .setStatusCode(response.getStatus())
                    .setBody(response.body())
                    .setContentType(response.header("Content-Type"));

            Map<String, List<String>> headers = response.headers();
            if (headers != null && !headers.isEmpty()) {
                headers.forEach((key, value) -> responseAdapter.addheader(key, value.toString()));
            }

            List<HttpCookie> cookies = response.getCookies();
            if (cookies != null && !cookies.isEmpty()) {
                cookies.forEach((item) -> responseAdapter.addCookies(item.getName(), item.getValue()));
            }

            return responseAdapter;
        }
    }
}
