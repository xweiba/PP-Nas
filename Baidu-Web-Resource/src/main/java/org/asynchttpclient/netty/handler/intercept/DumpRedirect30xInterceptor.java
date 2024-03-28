package org.asynchttpclient.netty.handler.intercept;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.*;
import org.asynchttpclient.cookie.CookieStore;
import org.asynchttpclient.netty.NettyResponseFuture;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.handler.HttpHandler;
import org.asynchttpclient.netty.request.NettyRequestSender;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.HttpConstants;
import org.asynchttpclient.util.HttpUtils;
import org.asynchttpclient.util.MiscUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * org.asynchttpclient.netty.handler.intercept.Redirect30xInterceptor
 * 重写默认的30x重定向拦截器， 解决超过最大重定向次数直接抛出异常的问题
 *
 * @author weiba
 * @date 2024/3/28 17:04
 */
@Log4j2
public class DumpRedirect30xInterceptor extends Redirect30xInterceptor {

    public static final Set<Integer> REDIRECT_STATUSES = new HashSet();
    private static final Logger LOGGER = LoggerFactory.getLogger(Redirect30xInterceptor.class);

    static {
        REDIRECT_STATUSES.add(HttpConstants.ResponseStatusCodes.MOVED_PERMANENTLY_301);
        REDIRECT_STATUSES.add(HttpConstants.ResponseStatusCodes.FOUND_302);
        REDIRECT_STATUSES.add(HttpConstants.ResponseStatusCodes.SEE_OTHER_303);
        REDIRECT_STATUSES.add(HttpConstants.ResponseStatusCodes.TEMPORARY_REDIRECT_307);
        REDIRECT_STATUSES.add(HttpConstants.ResponseStatusCodes.PERMANENT_REDIRECT_308);
    }

    private final ChannelManager channelManager;
    private final AsyncHttpClientConfig config;
    private final NettyRequestSender requestSender;

    public DumpRedirect30xInterceptor(ChannelManager channelManager, AsyncHttpClientConfig config, NettyRequestSender requestSender) {
        super(channelManager, config, requestSender);
        this.channelManager = channelManager;
        this.config = config;
        this.requestSender = requestSender;
    }

    public static void resetRedirect30xInterceptor(AsyncHttpClient client) {
        AsyncHttpClientConfig config = (AsyncHttpClientConfig) ReflectUtil.getFieldValue(client, "config");
        NettyRequestSender requestSender = (NettyRequestSender) ReflectUtil.getFieldValue(client, "requestSender");
        ChannelManager channelManager = (ChannelManager) ReflectUtil.getFieldValue(client, "channelManager");

        Bootstrap httpBootstrap = (Bootstrap) ReflectUtil.getFieldValue(channelManager, "httpBootstrap");
        Object handler = ReflectUtil.getFieldValue(httpBootstrap, "handler");
        HttpHandler httpHandler = (HttpHandler) ReflectUtil.getFieldValue(handler, "val$httpHandler");
        Interceptors interceptors = (Interceptors) ReflectUtil.getFieldValue(httpHandler, "interceptors");

        ReflectUtil.setFieldValue(httpHandler, "interceptors", new DumpInterceptors(config, channelManager, requestSender, interceptors));
        ReflectUtil.setFieldValue(interceptors, "redirect30xInterceptor", new DumpRedirect30xInterceptor(channelManager, config, requestSender));
        ReflectUtil.setFieldValue(interceptors, "connectSuccessInterceptor", new DumpConnectSuccessInterceptor(channelManager, requestSender));
    }

    public boolean exitAfterHandlingRedirect(Channel channel, NettyResponseFuture<?> future, HttpResponse response, Request request, int statusCode, Realm realm) throws Exception {
        if (HttpUtils.followRedirect(this.config, request)) {
            if (future.incrementAndGetCurrentRedirectCount() >= this.config.getMaxRedirects()) {
                // 不抛异常，直接返回false
                return false;
            } else {
                future.setInAuth(false);
                future.setInProxyAuth(false);
                String originalMethod = request.getMethod();
                boolean switchToGet = !originalMethod.equals(HttpConstants.Methods.GET) && !originalMethod.equals(HttpConstants.Methods.OPTIONS) && !originalMethod.equals(HttpConstants.Methods.HEAD) && (statusCode == HttpConstants.ResponseStatusCodes.MOVED_PERMANENTLY_301 || statusCode == HttpConstants.ResponseStatusCodes.SEE_OTHER_303 || statusCode == HttpConstants.ResponseStatusCodes.FOUND_302 && !this.config.isStrict302Handling());
                boolean keepBody = statusCode == HttpConstants.ResponseStatusCodes.TEMPORARY_REDIRECT_307 || statusCode == HttpConstants.ResponseStatusCodes.PERMANENT_REDIRECT_308 || statusCode == HttpConstants.ResponseStatusCodes.FOUND_302 && this.config.isStrict302Handling();
                RequestBuilder requestBuilder = (new RequestBuilder(switchToGet ? HttpConstants.Methods.GET : originalMethod)).setChannelPoolPartitioning(request.getChannelPoolPartitioning()).setFollowRedirect(true).setLocalAddress(request.getLocalAddress()).setNameResolver(request.getNameResolver()).setProxyServer(request.getProxyServer()).setRealm(request.getRealm()).setRequestTimeout(request.getRequestTimeout());
                if (keepBody) {
                    requestBuilder.setCharset(request.getCharset());
                    if (MiscUtils.isNonEmpty(request.getFormParams())) {
                        requestBuilder.setFormParams(request.getFormParams());
                    } else if (request.getStringData() != null) {
                        requestBuilder.setBody(request.getStringData());
                    } else if (request.getByteData() != null) {
                        requestBuilder.setBody(request.getByteData());
                    } else if (request.getByteBufferData() != null) {
                        requestBuilder.setBody(request.getByteBufferData());
                    } else if (request.getBodyGenerator() != null) {
                        requestBuilder.setBody(request.getBodyGenerator());
                    } else if (MiscUtils.isNonEmpty(request.getBodyParts())) {
                        requestBuilder.setBodyParts(request.getBodyParts());
                    }
                }

                requestBuilder.setHeaders(this.propagatedHeaders(request, realm, keepBody));
                boolean initialConnectionKeepAlive = future.isKeepAlive();
                Object initialPartitionKey = future.getPartitionKey();
                HttpHeaders responseHeaders = response.headers();
                String location = responseHeaders.get(HttpHeaderNames.LOCATION);
                Uri newUri = Uri.create(future.getUri(), location);
                LOGGER.debug("Redirecting to {}", newUri);
                CookieStore cookieStore = this.config.getCookieStore();
                if (cookieStore != null) {
                    List<Cookie> cookies = cookieStore.get(newUri);
                    if (!cookies.isEmpty()) {
                        Iterator var18 = cookies.iterator();

                        while (var18.hasNext()) {
                            Cookie cookie = (Cookie) var18.next();
                            requestBuilder.addOrReplaceCookie(cookie);
                        }
                    }
                }

                requestBuilder.setCookies(propagatedCookies(request.getCookies(), response.headers()));

                boolean sameBase = request.getUri().isSameBase(newUri);
                if (sameBase) {
                    requestBuilder.setVirtualHost(request.getVirtualHost());
                }

                Request nextRequest = requestBuilder.setUri(newUri).build();
                future.setTargetRequest(nextRequest);
                LOGGER.debug("Sending redirect to {}", newUri);
                if (future.isKeepAlive() && !HttpUtil.isTransferEncodingChunked(response)) {
                    if (sameBase) {
                        future.setReuseChannel(true);
                        this.requestSender.drainChannelAndExecuteNextRequest(channel, future, nextRequest);
                    } else {
                        this.channelManager.drainChannelAndOffer(channel, future, initialConnectionKeepAlive, initialPartitionKey);
                        this.requestSender.sendNextRequest(nextRequest, future);
                    }
                } else {
                    this.channelManager.closeChannel(channel);
                    this.requestSender.sendNextRequest(nextRequest, future);
                }

                return true;
            }
        } else {
            return false;
        }
    }

    private Collection<Cookie> propagatedCookies(List<Cookie> cookies, HttpHeaders responseHeaders) {

        for (Map.Entry<String, String> responseHeader : responseHeaders) {
            if (responseHeader.getKey().equals("Set-Cookie")) {
                String setCookieStr = responseHeader.getValue();
                if (StrUtil.isNotBlank(setCookieStr)) {
                    String[] itemCookie = setCookieStr.split(";")[0].split("=");
                    String name = itemCookie[0];
                    String value = itemCookie[1];
                    if (value.equals("deleted")) {
                        cookies.removeIf(cookie -> cookie.name().equals(name));
                    } else {
                        if (cookies.stream().anyMatch(cookie -> cookie.name().equals(name))) {
                            cookies.removeIf(cookie -> cookie.name().equals(name));
                            cookies.removeIf(cookie -> cookie.name().equals(name + "_BFESS"));
                        }
                        cookies.add(new DefaultCookie(name, value));
                    }
                }
            }
        }


        return cookies;
    }

    private HttpHeaders propagatedHeaders(Request request, Realm realm, boolean keepBody) {
        HttpHeaders headers = request.getHeaders().remove(HttpHeaderNames.HOST).remove(HttpHeaderNames.CONTENT_LENGTH);
        if (!keepBody) {
            headers.remove(HttpHeaderNames.CONTENT_TYPE);
        }

        if (realm != null && realm.getScheme() == Realm.AuthScheme.NTLM) {
            headers.remove(HttpHeaderNames.AUTHORIZATION).remove(HttpHeaderNames.PROXY_AUTHORIZATION);
        }

        return headers;
    }

}
