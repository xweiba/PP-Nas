package org.asynchttpclient.netty.handler.intercept;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.cookie.Cookie;
import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.HttpResponseStatus;
import org.asynchttpclient.Request;
import org.asynchttpclient.netty.NettyResponseFuture;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.request.NettyRequestSender;

import java.util.List;

import static org.asynchttpclient.netty.handler.intercept.DumpRedirect30xInterceptor.ADD_302_COOKIE_TAG;

/**
 * @author caleb_L
 * @date 2024/3/28 18:36
 */
@Log4j2
public class DumpInterceptors extends Interceptors {

    private final Interceptors interceptors;


    public DumpInterceptors(AsyncHttpClientConfig config, ChannelManager channelManager, NettyRequestSender requestSender, Interceptors interceptors) {
        super(config, channelManager, requestSender);
        this.interceptors = interceptors;
    }

    @Override
    public boolean exitAfterIntercept(Channel channel, NettyResponseFuture<?> future, AsyncHandler<?> handler, HttpResponse response, HttpResponseStatus status, HttpHeaders responseHeaders) throws Exception {
        boolean b = interceptors.exitAfterIntercept(channel, future, handler, response, status, responseHeaders);
        callWrite302Cookies(future.getCurrentRequest(), response);
        return b;
    }

    protected void callWrite302Cookies(Request request, HttpResponse response) {
        int statusCode = response.status().code();
        if (statusCode == 200) {
            List<Cookie> cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.name().startsWith(ADD_302_COOKIE_TAG)) {
                    log.info("write 302 cookies:{}", cookie);
                    response.headers().add("Set-Cookie", cookie.name().substring(ADD_302_COOKIE_TAG.length()) + "=" + cookie.value() + ";");
                }
            }
        }
    }
}
