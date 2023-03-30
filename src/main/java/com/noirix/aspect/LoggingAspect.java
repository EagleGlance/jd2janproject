package com.noirix.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = Logger.getLogger(LoggingAspect.class);

//    @Before("aroundRepositoryPointcut()")
//    public void logBefore(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " start");
//    }
//
//    @AfterReturning(pointcut = "aroundRepositoryPointcut()")
//    public void doAccessCheck(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " finished");
//    }

    @Pointcut("execution(* com.noirix.repository.*.*.findAll(..))")
    public void aroundRepositoryPointcut() {
    }

    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method " + joinPoint.getSignature().getName() + " start");
//        System.out.println(joinPoint.getSignature());
//        System.out.println(joinPoint.getKind());
//        System.out.println(joinPoint.getTarget());
//        for (Object arg : joinPoint.getArgs()) {
//            System.out.println(arg);
//        }

        Object proceed = joinPoint.proceed();
        log.info("Method " + joinPoint.getSignature().getName() + " finished");
        return proceed;
    }

}
