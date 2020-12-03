package io.github.jjyy.routing;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author jy
 */
@Aspect
public class DataSourceRouteAspect {

    @Pointcut("@annotation(io.github.jjyy.routing.DataSourceRoute)")
    public void dataSourceRoute() {
    }

    @Around("dataSourceRoute()")
    public Object setupDataSourceRoute(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataSourceRoute route = signature.getMethod().getAnnotation(DataSourceRoute.class);
        if (route != null) {
            // 设置当前线程的数据源
            DataSourceHolder.set(route.value());
        }
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            DataSourceHolder.clear();
        }

    }
}
