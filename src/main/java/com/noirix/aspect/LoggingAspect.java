package com.noirix.aspect;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Aspect
public class LoggingAspect {
    private static final Logger log = Logger.getLogger(LoggingAspect.class);
    private final Counter counter;

    @Pointcut("execution(* com.noirix.repository.impl.user.UserRepositoryJdbcTemplateImpl.*(..))")
    public void aroundRepositoryPointcut() {
    }

    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {


        log.info("Method " + joinPoint.getSignature().getName() + " start");
        System.out.println(joinPoint.getSignature());

        long m = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        log.info("Method " + joinPoint.getSignature().getName() + " finished");
        log.info("Method " + joinPoint.getSignature().getName() + " working time: "
                + (double)(System.currentTimeMillis()-m) + " ms");
        switch (joinPoint.getSignature().getName())
        {
            case "findAll":
                counter.setFindAll(counter.getFindAll() + 1);
                break;
            case "findById":
                counter.setFindById(counter.getFindById() + 1);
                break;
            case "create":
                counter.setCreate(counter.getCreate() + 1);
                break;
            case "update":
                counter.setUpdate(counter.getUpdate() + 1);
                break;
            case "delete":
                counter.setDelete(counter.getDelete() + 1);
                break;
            case "emailAndPhoneNumber":
                counter.setEmailAndPhoneNumber(counter.getEmailAndPhoneNumber() + 1);
                break;
            case "changedOverTime":
                counter.setChangedOverTime(counter.getChangedOverTime() + 1);
                break;
            default:
                break;
        }
        return proceed;
    }
}
