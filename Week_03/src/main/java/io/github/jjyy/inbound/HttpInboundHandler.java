package io.github.jjyy.inbound;

import io.github.jjyy.filter.HttpRequestFilter;
import io.github.jjyy.filter.HttpRequestFilterChain;
import io.github.jjyy.filter.HttpRequestHeaderFilter;
import io.github.jjyy.outbound.HttpClientOutboundHandler;
import io.github.jjyy.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private final HttpRequestFilterChain filterChain = new HttpRequestFilterChain();
    private HttpOutboundHandler handler;

    public HttpInboundHandler(List<String> proxyServers) {
        this.filterChain.addFilter(new HttpRequestHeaderFilter("app", "nio"));
        this.handler = new HttpClientOutboundHandler(proxyServers);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            for (HttpRequestFilter filter : filterChain.getFilters()) {
                filter.filter(fullRequest, ctx);
            }
            handler.handle(fullRequest, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
