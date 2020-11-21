package io.github.jjyy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author jy
 */
public class DemoServiceProxy implements InvocationHandler {

    private final DemoService demoService;

    public DemoServiceProxy(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before Invoke: " + Arrays.toString(args));
        Object result = method.invoke(demoService, args);
        System.out.println("After Invoke");
        return result;
    }
}
