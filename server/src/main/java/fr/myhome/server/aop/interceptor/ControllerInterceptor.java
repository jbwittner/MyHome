package fr.myhome.server.aop.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerInterceptor extends GeneralInterceptor {

    private final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Around("execution(* fr.finanting.server.controller.*.*(..))")
    public final Object logInterceptor(final ProceedingJoinPoint joinPoint) throws Throwable {
        return this.logExecutionTime(joinPoint, this.logger);
    }
}
