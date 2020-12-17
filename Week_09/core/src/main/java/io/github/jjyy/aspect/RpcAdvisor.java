package io.github.jjyy.aspect;

import io.github.jjyy.client.RpcClient;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * @author jy
 */
public class RpcAdvisor implements PointcutAdvisor {


    private final Pointcut pointcut;
    private final Advice advice;

    public RpcAdvisor(RpcClient client) {
        Pointcut cpc = new AnnotationMatchingPointcut(Rpc.class, true);
        Pointcut mpc = AnnotationMatchingPointcut.forMethodAnnotation(Rpc.class);
        this.pointcut = new ComposablePointcut(cpc).union(mpc);
        this.advice = new RpcAdvice(client);
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public boolean isPerInstance() {
        return true;
    }
}
