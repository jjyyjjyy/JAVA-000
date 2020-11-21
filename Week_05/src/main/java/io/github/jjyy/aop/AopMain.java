package io.github.jjyy.aop;

import java.lang.reflect.Proxy;

/**
 * @author jy
 */
public class AopMain {

    public static void main(String[] args) {
        DemoService demoService = (DemoService) Proxy.newProxyInstance(DemoService.class.getClassLoader(), new Class[]{DemoService.class}, new DemoServiceProxy(System.out::println));
        demoService.handle("Demo AOP");
    }
}
