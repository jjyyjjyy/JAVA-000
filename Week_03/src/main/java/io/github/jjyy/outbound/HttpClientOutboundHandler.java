package io.github.jjyy.outbound;

import io.github.jjyy.router.RouterStrategy;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author jy
 */
public class HttpClientOutboundHandler implements HttpOutboundHandler {

    private static final Set<String> DISALLOWED_HEADERS_SET = Set.of("Connection", "content-length", "Expect", "Host", "Upgrade");

    private final HttpClient client;

    private final List<String> proxyServers;

    private RouterStrategy routerStrategy = RouterStrategy.RANDOM;

    public HttpClientOutboundHandler(List<String> proxyServers) {
        this.proxyServers = proxyServers;
        this.client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5L))
            .build();
    }

    public HttpClientOutboundHandler(List<String> proxyServers, RouterStrategy routerStrategy) {
        this(proxyServers);
        this.routerStrategy = routerStrategy;
    }

    @Override
    public void handle(FullHttpRequest request, ChannelHandlerContext ctx) {
        String downStream = this.routerStrategy.getRouter().route(proxyServers);
        if (downStream == null) {
            throw new RuntimeException("None downstream proxy server found!");
        }
        try {
            HttpResponse<String> downstreamResponse = client.send(buildRequest(downStream + request.uri(), request), HttpResponse.BodyHandlers.ofString());
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(downstreamResponse.body().getBytes()));
            downstreamResponse.headers().map().forEach((k, v) -> response.headers().add(k, v));
            ctx.write(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ctx.flush();
            ctx.close();
        }
    }

    private HttpRequest buildRequest(String url, FullHttpRequest originRequest) {
        // url
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create(url));
        // header
        originRequest.headers().entries().stream().filter(entry -> !DISALLOWED_HEADERS_SET.contains(entry.getKey())).forEach(entry -> builder.header(entry.getKey(), entry.getValue()));
        // body (empty)
        return builder.build();
    }
}
