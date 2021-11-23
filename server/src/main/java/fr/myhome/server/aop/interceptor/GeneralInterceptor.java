package fr.myhome.server.aop.interceptor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

public class GeneralInterceptor {

    protected final ValidatorFactory validatorFactory;
    protected final Validator validator;

    public GeneralInterceptor() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public Object logExecutionTime(final ProceedingJoinPoint joinPoint, final Logger logger) throws Throwable {
        final long start = System.currentTimeMillis();
        final List<Object> list = Arrays.asList(joinPoint.getArgs());
        final Iterator<Object> iterator =  list.iterator();

        String method = joinPoint.getSignature().getDeclaringTypeName();
        method += "." + joinPoint.getSignature().getName();

        logger.info("ENTERING - " + method);        

        Object object;

        if(logger.isDebugEnabled() == true){
            while(iterator.hasNext()){
                object = iterator.next();
                logger.debug("Args list :: " + method + " :: " + object.toString());
            }
        }
        
        final Object proceed = joinPoint.proceed();

        final long executionTime = System.currentTimeMillis() - start;

        logger.info("EXITING - " + method + " executed in " + executionTime + "ms");

        return proceed;
    }

}