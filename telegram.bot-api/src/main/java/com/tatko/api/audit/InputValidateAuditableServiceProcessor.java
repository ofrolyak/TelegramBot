package com.tatko.api.audit;

import com.tatko.api.exceptions.TelegramBotApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class InputValidateAuditableServiceProcessor {

    /**
     * Autowired ApplicationContext bean.
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Pointcut for this AOP processor.
     */
    @Pointcut("@annotation("
            + "com.tatko.api.audit.annotations.InputValidateAnnotation)")
    public void auditPoint() {
    }

    /**
     * Process validate logic for class.
     * @param proceedingJoinPoint
     * @return Object instance.
     * @throws Throwable
     */
    @Around("auditPoint()")
    public Object doAround(final ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {

        log.debug("Audit @Around advice started: {}", proceedingJoinPoint);

        Object[] args = proceedingJoinPoint.getArgs();

        String collect = Arrays.stream(args)
                .map(o -> Objects.isNull(o) ? "null" : o.toString())
                .collect(Collectors.joining(","));

        log.debug("args: {}", collect);

        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        try {

            String currentMethodName = methodSignature.getName();
            String validationMethodName = String.join(StringUtils.EMPTY,
                    currentMethodName, "Validate");
            log.debug("Got validationMethodName: {}", validationMethodName);
            String currentClassName
                    = signature.getDeclaringType().getSimpleName();
            String validationMethodClassName = String.join(StringUtils.EMPTY,
                    currentClassName, "Validate");
            log.debug("Got validationMethodClassName: {}",
                    validationMethodClassName);

            Class<?> validationMethodClass = null;
            try {
                validationMethodClass = Class.forName(
                        String.join(StringUtils.EMPTY,
                        "com.tatko.api.validators.",
                        validationMethodClassName));
            } catch (ClassNotFoundException e) {
                log.warn("Validation Class not found: {}",
                        validationMethodClassName);
            }

            Method validationMethod = null;
            if (Objects.nonNull(validationMethodClass)) {
                validationMethod = ReflectionUtils.findMethod(
                        validationMethodClass, validationMethodName,
                        method.getParameterTypes());
            }

            if (Objects.nonNull(validationMethod)) {
                Object validatedObject
                        = applicationContext.getBean(validationMethodClass);
                ReflectionUtils.invokeMethod(
                        validationMethod, validatedObject, args);
            } else {
                log.warn("Validation Method not found: {}",
                        validationMethodName);
            }

        } catch (
                TelegramBotApiException tbae) {
            log.error("TelegramBotApiException caught:", tbae);
            throw tbae;
        } catch (
                Exception e) {
            log.error("Exception caught:", e);
            throw new TelegramBotApiException(new String[]{e.getMessage()});
        }


        log.debug("Audit @Around advice finished: {}", proceedingJoinPoint);

        return proceedingJoinPoint.proceed();

    }


}
