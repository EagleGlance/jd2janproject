package com.noirix.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CallsCountAspect {
    private static Long callsCounterInRepository = 0L;
    private static Long callsCounterInService = 0L;
    @Pointcut("execution(public * com.noirix.repository.CarRepositoryImpl.findOne(..))")
    public void callRepositoryPublic() { }

    @Pointcut("execution(public * com.noirix.service.CarServiceImpl.findOne(..))")
    public void callServicePublic() { }

    @After("callRepositoryPublic()")
    public void callsCountAroundRepositoryMethods(JoinPoint joinPoint) {
        System.out.printf("Method %s has been called in repository %d times\n", joinPoint.getSignature().getName(),
                ++callsCounterInRepository);
    }

    @After("callServicePublic()")
    public void callsCountAroundServiceMethods(JoinPoint joinPoint) {
        System.out.printf("Method %s has been called in service %d times\n", joinPoint.getSignature().getName(),
                ++callsCounterInService);
    }
}
