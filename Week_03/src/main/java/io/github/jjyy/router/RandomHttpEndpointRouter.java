package io.github.jjyy.router;

import java.util.List;

/**
 * @author jy
 */
public class RandomHttpEndpointRouter implements HttpEndpointRouter {

    private static final HttpEndpointRouter INSTANCE = new RandomHttpEndpointRouter();

    public static HttpEndpointRouter getInstance() {
        return INSTANCE;
    }

    @Override
    public String route(List<String> endpoints) {
        int size = endpoints.size();
        return endpoints.get((int) (Math.random() * size));
    }
}
