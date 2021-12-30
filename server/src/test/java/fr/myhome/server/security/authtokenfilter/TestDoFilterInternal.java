package fr.myhome.server.security.authtokenfilter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import fr.myhome.server.generated.model.TokenDTO;
import fr.myhome.server.model.User;
import fr.myhome.server.security.AuthTokenFilter;
import fr.myhome.server.security.UserDetailsImpl;
import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;
import fr.myhome.server.tools.CookieUtil;
import fr.myhome.server.tools.JwtTokenUtil;

public class TestDoFilterInternal extends AbstractMotherIntegrationTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private AuthTokenFilter authTokenFilter;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @Override
    protected void initDataBeforeEach() {
        this.authTokenFilter = new AuthTokenFilter(userDetailsService, jwtTokenUtil);
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.filterChain = mock(FilterChain.class);
    }

    @Test
    public void testShouldNotFilterOk(){
        
    }

    @Test
    public void testNullCookies() {

        when(request.getCookies()).thenReturn(null);

        try {
            this.authTokenFilter.doFilterInternal(request, response, filterChain);
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Assertions.assertNull(authentication);
        } catch (ServletException | IOException e) {
            Assertions.fail(e);
        }

    }

    @Test
    public void testNoAccessTokenCookies() {

        final Cookie cookie = new Cookie(this.testFactory.getRandomAlphanumericString(), this.testFactory.getRandomAlphanumericString());
        final Cookie[] cookies = new Cookie[]{cookie};
        when(request.getCookies()).thenReturn(cookies);

        try {
            this.authTokenFilter.doFilterInternal(request, response, filterChain);
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Assertions.assertNull(authentication);
        } catch (ServletException | IOException e) {
            Assertions.fail(e);
        }

    }

    @Test
    public void testBadJWTAccessToken() {

        final Cookie cookie = new Cookie(CookieUtil.ACCESS_TOKEN_COOKIE_NAME, this.testFactory.getRandomAlphanumericString());
        final Cookie[] cookies = new Cookie[]{cookie};
        when(request.getCookies()).thenReturn(cookies);

        try {
            this.authTokenFilter.doFilterInternal(request, response, filterChain);
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Assertions.assertNull(authentication);
        } catch (ServletException | IOException e) {
            Assertions.fail(e);
        }

    }

    @Test
    public void testUserNotExist() {

        final String userName = this.testFactory.getRandomAlphanumericString();

        final TokenDTO tokenDTO = jwtTokenUtil.getAccessToken(userName);

        final Cookie cookie = new Cookie(CookieUtil.ACCESS_TOKEN_COOKIE_NAME, tokenDTO.getJwt());
        final Cookie[] cookies = new Cookie[]{cookie};
        when(request.getCookies()).thenReturn(cookies);

        try {
            this.authTokenFilter.doFilterInternal(request, response, filterChain);
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Assertions.assertNull(authentication);
        } catch (ServletException | IOException e) {
            Assertions.fail(e);
        }

    }

    @Test
    public void testUserAuthentication() {

        final User user = this.testFactory.getUser();

        final String userName = user.getUsername();

        final TokenDTO tokenDTO = jwtTokenUtil.getAccessToken(userName);

        final Cookie cookie = new Cookie(CookieUtil.ACCESS_TOKEN_COOKIE_NAME, tokenDTO.getJwt());
        final Cookie[] cookies = new Cookie[]{cookie};
        when(request.getCookies()).thenReturn(cookies);

        try {
            this.authTokenFilter.doFilterInternal(request, response, filterChain);
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Assertions.assertNotNull(authentication);
            Assertions.assertTrue(authentication.isAuthenticated());
            final User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
            Assertions.assertEquals(user.getUsername(), currentUser.getUsername());
        } catch (ServletException | IOException e) {
            Assertions.fail(e);
        }

    }
    
}
