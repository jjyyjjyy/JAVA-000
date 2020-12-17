package io.github.jjyy.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author jy
 */
public class RpcRestClient implements RpcClient {

    private RpcEncoder encoder;

    @Override
    public Object exchange(String url, Object body) {
        try {
            return HttpClient.newHttpClient()
                .send(HttpRequest.newBuilder().uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(getEncoder().encode(body).toString())).build(), HttpResponse.BodyHandlers.ofString())
                .body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RpcEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(RpcEncoder encoder) {
        this.encoder = encoder;
    }
}
