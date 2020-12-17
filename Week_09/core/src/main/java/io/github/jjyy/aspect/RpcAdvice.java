package io.github.jjyy.aspect;

import com.alibaba.fastjson.JSON;
import io.github.jjyy.api.RpcfxRequest;
import io.github.jjyy.client.RpcClient;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author jy
 */
public class RpcAdvice implements MethodInterceptor {

    private final RpcClient client;

    public RpcAdvice(RpcClient client) {
        this.client = client;
    }

    @Override
    public Object invoke(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        StringBuilder url = getRequestUrl(method);

        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(method.getDeclaringClass().getName());
        request.setMethod(method.getName());
        request.setParams(invocation.getArguments());

        return JSON.parseObject(client.exchange(url.toString(), request).toString(), method.getReturnType());
    }

    private StringBuilder getRequestUrl(Method method) {
        StringBuilder url = new StringBuilder();
        // @Rpc on class
        Rpc classRpc = method.getDeclaringClass().getAnnotation(Rpc.class);
        if (classRpc != null && StringUtils.hasText(classRpc.value())) {
            url.append(classRpc.value());
        }
        // @Rpc on method
        Rpc rpc = method.getAnnotation(Rpc.class);
        if (rpc != null) {
            url.append(rpc.value());
        }
        return url;
    }
}
