package io.github.jjyy.client;

/**
 * @author jy
 */
public interface RpcClient {

    Object exchange(String url, Object body);

    RpcEncoder getEncoder();
}
