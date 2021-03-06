package fr.myhome.server.tools.handler;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import fr.myhome.server.exception.FunctionalException;
import fr.myhome.server.generated.model.ExceptionDTO;

/**
 * Class used to manage the exception and transfer the
 * information with the answer of the REST request
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    protected final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Method used to manage the exception and transfer
     * the information with the answer of the REST request
     */
    @ExceptionHandler({FunctionalException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<?> globaleExceptionHandler(final Exception ex, final WebRequest request) throws Exception {

        final ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setDetails(request.getDescription(false));
        exceptionDTO.setTimestamp(new Date());

        if(ex instanceof FunctionalException){
            
            this.logger.warn("HANDLING - FunctionalException : " + ex.getClass().getSimpleName() + " / message : " + ex.getMessage());

            exceptionDTO.setException(ex.getClass().getSimpleName());
            exceptionDTO.setMessage(ex.getMessage());

        } else if (ex instanceof MethodArgumentNotValidException){

            this.logger.warn("HANDLING - MethodArgumentNotValidException : " + ex.getClass().getSimpleName() + " / message : " + ex.getMessage());

            exceptionDTO.setException(ex.getClass().getSimpleName());
            exceptionDTO.setMessage(ex.getMessage());

        } else if(ex instanceof UndeclaredThrowableException) {

            final UndeclaredThrowableException undeclaredThrowableException = (UndeclaredThrowableException) ex;
            
            final Throwable throwable = undeclaredThrowableException.getUndeclaredThrowable();

            if(throwable instanceof FunctionalException){
                this.logger.warn("HANDLING - UndeclaredThrowableException / FunctionalException : " + throwable.getClass().getSimpleName() + " / message : " + throwable.getMessage());

                exceptionDTO.setException(throwable.getClass().getSimpleName());
                exceptionDTO.setMessage(throwable.getMessage());

            } else if(throwable instanceof MethodArgumentNotValidException){
                this.logger.warn("HANDLING - UndeclaredThrowableException / MethodArgumentNotValidException : " + throwable.getClass().getSimpleName() + " / message : " + throwable.getMessage());

                exceptionDTO.setException(throwable.getClass().getSimpleName());
                exceptionDTO.setMessage(throwable.getMessage());

            } else {
                this.logger.error("HANDLING - UnknownException : " + ex.getClass().getSimpleName() + " / message : " + ex.getMessage());
                throw ex;
            }
            
        } else {
            throw ex;
        }

        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
        
    }
}