package fr.myhome.server.aop.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ControllerInterceptor extends GeneralInterceptor {

    private final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Around("execution(* fr.myhome.server.controller.*.*(..))")
    public final Object logInterceptor(final ProceedingJoinPoint joinPoint) throws Throwable {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        this.logger.info(String.format("ENDPOINT - %s",request.getRequestURI()));
        return this.logExecutionTime(joinPoint, this.logger);
    }
}
