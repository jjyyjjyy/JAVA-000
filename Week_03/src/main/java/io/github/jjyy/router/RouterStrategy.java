package io.github.jjyy.router;

/**
 * @author jy
 */
public enum RouterStrategy {

    RANDOM {
        @Override
        public HttpEndpointRouter getRouter() {
            return RandomHttpEndpointRouter.getInstance();
        }
    };

    public abstract HttpEndpointRouter getRouter();
}
