package org.asynchttpclient.netty.handler.intercept;

import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.Request;
import org.asynchttpclient.netty.NettyResponseFuture;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.request.NettyRequestSender;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.uri.Uri;

/**
 * 200 Respeonse
 *
 * @author caleb_L
 * @date 2024/3/28 18:31
 */
@Log4j2
public class DumpConnectSuccessInterceptor extends ConnectSuccessInterceptor {
    private final ChannelManager channelManager;
    private final NettyRequestSender requestSender;

    public DumpConnectSuccessInterceptor(ChannelManager channelManager, NettyRequestSender requestSender) {
        super(channelManager, requestSender);
        this.channelManager = channelManager;
        this.requestSender = requestSender;
    }

    public boolean exitAfterHandlingConnect(Channel channel, NettyResponseFuture<?> future, Request request, ProxyServer proxyServer) {
        if (future.isKeepAlive()) {
            future.attachChannel(channel, true);
        }

        Uri requestUri = request.getUri();
        log.debug("Connecting to proxy {} for scheme {}", proxyServer, requestUri.getScheme());
        Future<Channel> whenHandshaked = this.channelManager.updatePipelineForHttpTunneling(channel.pipeline(), requestUri);
        future.setReuseChannel(true);
        future.setConnectAllowed(false);
        Request targetRequest = future.getTargetRequest().toBuilder().build();
        if (whenHandshaked == null) {
            this.requestSender.drainChannelAndExecuteNextRequest(channel, future, targetRequest);
        } else {
            this.requestSender.drainChannelAndExecuteNextRequest(channel, future, targetRequest, whenHandshaked);
        }

        return true;
    }
}
