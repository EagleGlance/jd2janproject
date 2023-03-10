package com.noirix.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BenchmarkAspect {

    @Pointcut("execution(public * com.noirix.repository.*.*(..))")
    public void callRepositoryPublic() { }

    @Pointcut("execution(public * com.noirix.service.*.*(..))")
    public void callServicePublic() { }

    @Around("callRepositoryPublic()")
    public Object benchmarkAroundRepositoryMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        Long timeStart = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        Long timeStop = System.currentTimeMillis();
        System.out.printf("Method %s in repository working time: %d ms\n", joinPoint.getSignature().getName(), timeStop - timeStart);
        return proceed;
    }

    @Around("callServicePublic()")
    public Object benchmarkAroundServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        Long timeStart = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        Long timeStop = System.currentTimeMillis();
        System.out.printf("Method %s in service working time: %d ms\n", joinPoint.getSignature().getName(), timeStop - timeStart);
        return proceed;
    }
}
