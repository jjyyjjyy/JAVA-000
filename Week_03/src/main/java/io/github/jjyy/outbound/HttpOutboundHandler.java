package io.github.jjyy.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author jy
 */
public interface HttpOutboundHandler {

    void handle(FullHttpRequest request, ChannelHandlerContext ctx);
}
