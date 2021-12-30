package fr.myhome.server.security.authtokenfilter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import fr.myhome.server.security.AuthTokenFilter;
import fr.myhome.server.security.SecurityConfiguration;
import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;
import fr.myhome.server.tools.JwtTokenUtil;

public class TestShouldNotFilter extends AbstractMotherIntegrationTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private AuthTokenFilter authTokenFilter;

    @Override
    protected void initDataBeforeEach() {
        this.authTokenFilter = new AuthTokenFilter(userDetailsService, jwtTokenUtil);
    }

    @Test
    public void testShouldNotFilterOk(){
        
        Arrays.stream(SecurityConfiguration.PUBLIC_ENDPOINTS).forEach(e -> {

            final HttpServletRequest request = mock(HttpServletRequest.class);
            when(request.getRequestURI()).thenReturn(e);

            try {
                final Boolean result = this.authTokenFilter.shouldNotFilter(request);
                Assertions.assertTrue(result);
            } catch (ServletException exception) {
                Assertions.fail(exception);
            }
        });
    }

    @Test
    public void testShouldNotFilterNOk(){
    
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn(this.testFactory.getRandomAlphanumericString());

        try {
            final Boolean result = this.authTokenFilter.shouldNotFilter(request);
            Assertions.assertFalse(result);
        } catch (ServletException exception) {
            Assertions.fail(exception);
        }
    }
    
}
