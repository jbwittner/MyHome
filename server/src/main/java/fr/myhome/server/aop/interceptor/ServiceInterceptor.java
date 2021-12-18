package fr.myhome.server.aop.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceInterceptor extends GeneralInterceptor {

    protected final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);

    @Around("execution(* fr.myhome.server.service.*.*(..))")
    public Object logInterceptor(final ProceedingJoinPoint joinPoint) throws Throwable {
        return this.logExecutionTime(joinPoint, this.logger);
    }

}