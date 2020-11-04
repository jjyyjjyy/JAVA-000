package io.github.jjyy.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author jy
 */
public class HttpRequestHeaderFilter implements HttpRequestFilter {

    private final String key;

    private final String value;

    public HttpRequestHeaderFilter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().add(key, value);
    }
}
