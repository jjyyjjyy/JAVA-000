package io.github.jjyy.client;

import com.alibaba.fastjson.parser.ParserConfig;
import io.github.jjyy.aspect.RpcAdvisor;
import org.springframework.aop.framework.ProxyFactory;

public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.github.jjyy");
    }

    public static <T> T create(final Class<T> serviceClass) {
        RpcRestClient client = new RpcRestClient();
        client.setEncoder(new RpcJSONEncoder());
        RpcAdvisor advisor = new RpcAdvisor(client);

        ProxyFactory proxyFactory = new ProxyFactory(new Class[]{serviceClass});
        proxyFactory.addAdvisor(advisor);

        return (T) proxyFactory.getProxy();

    }
}
