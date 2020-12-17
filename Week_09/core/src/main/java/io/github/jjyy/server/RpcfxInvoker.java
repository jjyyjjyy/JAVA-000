package io.github.jjyy.server;

import io.github.jjyy.api.RpcfxRequest;
import io.github.jjyy.api.RpcfxResolver;

import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver) {
        this.resolver = resolver;
    }

    public Object invoke(RpcfxRequest request) {
        String serviceClass = request.getServiceClass();
        Object service = resolver.resolve(serviceClass);
        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            return method.invoke(service, request.getParams());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
