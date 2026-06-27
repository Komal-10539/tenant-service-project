package com.example.tenant_service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class   LoggingAspect {

    private static final Logger log =
            LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut("execution(* com.example.tenant_service.controller..*(..))")
    public void controllerLayer() {}

    @Pointcut("execution(* com.example.tenant_service.service..*(..))")
    public void serviceLayer() {}

    @Pointcut("controllerLayer() || serviceLayer()")
    public void applicationLayer() {}

    @Before("applicationLayer()")
    public void logBefore(JoinPoint jp) {

        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();

        log.debug("Entering: {}.{}", className, methodName);
    }

    @AfterReturning("applicationLayer()")
    public void logAfter(JoinPoint jp) {

        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();

        log.debug("Completed: {}.{}", className, methodName);
    }

    @AfterThrowing(value = "applicationLayer()", throwing = "ex")
    public void logError(JoinPoint jp, Exception ex) {

        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();

        log.error("Exception in {}.{} → {}",
                className,
                methodName,
                ex.getMessage(),
                ex);
    }
}
