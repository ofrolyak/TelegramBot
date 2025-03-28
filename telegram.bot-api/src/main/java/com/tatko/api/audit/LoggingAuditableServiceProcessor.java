package com.tatko.api.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LoggingAuditableServiceProcessor {

    /**
     * Log event before starting method execution.
     * @param joinPoint
     */
    @Before("execution(* com.tatko.api..*(..))")
    public void beforeAround(final JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        String methodName = methodSignature.getName();
        String className = signature.getDeclaringType().getSimpleName();

        Object[] args = joinPoint.getArgs();

        String collect = Arrays.stream(args)
                .map(o -> Objects.isNull(o) ? "null" : o.toString())
                .collect(Collectors.joining(","));

        log.debug("Start executing method: " + className + "." + methodName
                + " => " + collect);
    }

    /**
     * Log event after finishing method execution.
     * @param joinPoint
     */
    @After("execution(* com.tatko.api..*(..))")
    public void afterAround(final JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        String methodName = methodSignature.getName();
        String className = signature.getDeclaringType().getSimpleName();

        log.debug("Finish executing method: " + className + "." + methodName);
    }

}
