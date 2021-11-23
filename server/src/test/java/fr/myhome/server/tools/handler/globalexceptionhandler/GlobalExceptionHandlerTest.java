package fr.myhome.server.tools.handler.globalexceptionhandler;

import static org.mockito.Mockito.mock;

import java.lang.reflect.UndeclaredThrowableException;
import java.security.Principal;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import fr.myhome.server.exception.FunctionalException;
import fr.myhome.server.generated.model.ExceptionDTO;
import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;
import fr.myhome.server.tools.handler.GlobalExceptionHandler;


public class GlobalExceptionHandlerTest extends AbstractMotherIntegrationTest {

    @Override
    protected void initDataBeforeEach() {}

    @Test
    public void testExceptionFail() {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        final FakeWebRequest fakeWebRequest = new FakeWebRequest();

        final Exception exception = new Exception();

        Assertions.assertThrows(Exception.class,
            () -> globalExceptionHandler.globuleExceptionHandler(exception, fakeWebRequest));
        
    }

    @Test
    public void testUndeclaredThrowableExceptionWithExceptionFail() {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        final FakeWebRequest fakeWebRequest = new FakeWebRequest();

        final Exception exception = new Exception();
        final UndeclaredThrowableException undeclaredThrowableException = new UndeclaredThrowableException(exception);

        Assertions.assertThrows(Exception.class,
            () -> globalExceptionHandler.globuleExceptionHandler(undeclaredThrowableException, fakeWebRequest));
        
    }

    @Test
    public void testMethodArgumentNotValidException() throws Exception {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        final FakeWebRequest fakeWebRequest = new FakeWebRequest();

        final MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        Mockito.when(methodArgumentNotValidException.getMessage()).thenReturn(this.testFactory.getRandomAlphanumericString());

        final Date dateBefore = new Date();

        Thread.sleep(1000);

        final ResponseEntity<?> test = globalExceptionHandler.globuleExceptionHandler(methodArgumentNotValidException, fakeWebRequest);

        Thread.sleep(1000);

        final Date dateAfter = new Date();

        final ExceptionDTO errorDetails = (ExceptionDTO) test.getBody();

        Assertions.assertNotNull(errorDetails);
        final Date dateException = errorDetails.getTimestamp();

        Assertions.assertEquals(MethodArgumentNotValidException.class.getSimpleName(), errorDetails.getException());
        Assertions.assertEquals(methodArgumentNotValidException.getMessage(), errorDetails.getMessage());
        Assertions.assertEquals(fakeWebRequest.getDescription(false), errorDetails.getDetails());
        Assertions.assertTrue(dateBefore.before(dateException));
        Assertions.assertTrue(dateAfter.after(dateException));
        
    }

    @Test
    public void testUndeclaredThrowableExceptionWithMethodArgumentNotValidException() throws Exception {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        final FakeWebRequest fakeWebRequest = new FakeWebRequest();

        final MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        Mockito.when(methodArgumentNotValidException.getMessage()).thenReturn(this.testFactory.getRandomAlphanumericString());
        final UndeclaredThrowableException undeclaredThrowableException = new UndeclaredThrowableException(methodArgumentNotValidException);

        final Date dateBefore = new Date();

        Thread.sleep(1000);

        final ResponseEntity<?> test = globalExceptionHandler.globuleExceptionHandler(undeclaredThrowableException, fakeWebRequest);

        Thread.sleep(1000);

        final Date dateAfter = new Date();

        final ExceptionDTO errorDetails = (ExceptionDTO) test.getBody();

        Assertions.assertNotNull(errorDetails);
        final Date dateException = errorDetails.getTimestamp();

        Assertions.assertEquals(MethodArgumentNotValidException.class.getSimpleName(), errorDetails.getException());
        Assertions.assertEquals(methodArgumentNotValidException.getMessage(), errorDetails.getMessage());
        Assertions.assertEquals(fakeWebRequest.getDescription(false), errorDetails.getDetails());
        Assertions.assertTrue(dateBefore.before(dateException));
        Assertions.assertTrue(dateAfter.after(dateException));
        
    }

    @Test
    public void testUndeclaredThrowableExceptionWithFunctionalExceptionOk() throws Exception {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        final FakeWebRequest fakeWebRequest = new FakeWebRequest();

        final MockFunctionalException exception = new MockFunctionalException();
        final UndeclaredThrowableException undeclaredThrowableException = new UndeclaredThrowableException(exception);

        final Date dateBefore = new Date();

        Thread.sleep(1000);

        final ResponseEntity<?> test = globalExceptionHandler.globuleExceptionHandler(undeclaredThrowableException, fakeWebRequest);

        Thread.sleep(1000);

        final Date dateAfter = new Date();

        final ExceptionDTO errorDetails = (ExceptionDTO) test.getBody();

        Assertions.assertNotNull(errorDetails);
        final Date dateException = errorDetails.getTimestamp();

        Assertions.assertEquals(MockFunctionalException.class.getSimpleName(), errorDetails.getException());
        Assertions.assertEquals(exception.getMessage(), errorDetails.getMessage());
        Assertions.assertEquals(fakeWebRequest.getDescription(false), errorDetails.getDetails());
        Assertions.assertTrue(dateBefore.before(dateException));
        Assertions.assertTrue(dateAfter.after(dateException));
        
    }

    @Test
    public void testFunctionalExceptionOk() throws Exception {
        final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        final FakeWebRequest fakeWebRequest = new FakeWebRequest();

        final MockFunctionalException exception = new MockFunctionalException();

        final Date dateBefore = new Date();

        Thread.sleep(1000);

        final ResponseEntity<?> test = globalExceptionHandler.globuleExceptionHandler(exception, fakeWebRequest);

        Thread.sleep(1000);

        final Date dateAfter = new Date();

        final ExceptionDTO errorDetails = (ExceptionDTO) test.getBody();

        Assertions.assertNotNull(errorDetails);
        final Date dateException = errorDetails.getTimestamp();

        Assertions.assertEquals(MockFunctionalException.class.getSimpleName(), errorDetails.getException());
        Assertions.assertEquals(exception.getMessage(), errorDetails.getMessage());
        Assertions.assertEquals(fakeWebRequest.getDescription(false), errorDetails.getDetails());
        Assertions.assertTrue(dateBefore.before(dateException), "dateBefore");
        Assertions.assertTrue(dateAfter.after(dateException), "dateAfter");
        
    }

    /**
     * Custom FunctionalException
     */
    static class MockFunctionalException extends FunctionalException {

        private static final long serialVersionUID = 1L;

        /**
         * Constructor
         */
        public MockFunctionalException() {
            super("CustomMessage");
        }

    }

    /**
     * Fake WebRequest
     */
    static class FakeWebRequest implements WebRequest {

        @Override
        public Object getAttribute(final String name, final int scope) {
            return null;
        }
    
        @Override
        public void setAttribute(final String name, final Object value, final int scope) {
    
        }
    
        @Override
        public void removeAttribute(final String name, final int scope) {
    
        }
    
        @Override
        public String[] getAttributeNames(final int scope) {
            return new String[0];
        }
    
        @Override
        public void registerDestructionCallback(final String name, final Runnable callback, final int scope) {
    
        }
    
        @Override
        public Object resolveReference(final String key) {
            return null;
        }
    
        @Override
        public String getSessionId() {
            return null;
        }
    
        @Override
        public Object getSessionMutex() {
            return null;
        }
    
        @Override
        public String getHeader(final String headerName) {
            return null;
        }
    
        @Override
        public String[] getHeaderValues(final String headerName) {
            return new String[0];
        }
    
        @Override
        public Iterator<String> getHeaderNames() {
            return null;
        }
    
        @Override
        public String getParameter(final String paramName) {
            return null;
        }
    
        @Override
        public String[] getParameterValues(final String paramName) {
            return new String[0];
        }
    
        @Override
        public Iterator<String> getParameterNames() {
            return null;
        }
    
        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }
    
        @Override
        public Locale getLocale() {
            return null;
        }
    
        @Override
        public String getContextPath() {
            return null;
        }
    
        @Override
        public String getRemoteUser() {
            return null;
        }
    
        @Override
        public Principal getUserPrincipal() {
            return null;
        }
    
        @Override
        public boolean isUserInRole(final String role) {
            return false;
        }
    
        @Override
        public boolean isSecure() {
            return false;
        }
    
        @Override
        public boolean checkNotModified(final long lastModifiedTimestamp) {
            return false;
        }
    
        @Override
        public boolean checkNotModified(final String etag) {
            return false;
        }
    
        @Override
        public boolean checkNotModified(final String etag, final long lastModifiedTimestamp) {
            return false;
        }
    
        @Override
        public String getDescription(final boolean includeClientInfo) {
            return "CustomDescription";
        }
        
    }
    

}