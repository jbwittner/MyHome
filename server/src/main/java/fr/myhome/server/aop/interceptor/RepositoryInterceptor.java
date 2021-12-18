package fr.myhome.server.aop.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Interceptor for Repositories
 */
@Aspect
@Component
public class RepositoryInterceptor extends GeneralInterceptor {

    protected final Logger logger = LoggerFactory.getLogger(RepositoryInterceptor.class);

    @Around("execution(* fr.myhome.server.repository.*.*(..))")
    public Object logInterceptor(final ProceedingJoinPoint joinPoint) throws Throwable {
        return this.logExecutionTime(joinPoint, this.logger);
    }

}