package fr.myhome.server.service.authentication;

import java.util.List;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.myhome.server.exception.NoRememberMeTokenCookie;
import fr.myhome.server.exception.TokenMatchException;
import fr.myhome.server.exception.UserNotExistException;
import fr.myhome.server.generated.model.TokenDTO;
import fr.myhome.server.model.User;
import fr.myhome.server.repository.CollectionPermissionRepository;
import fr.myhome.server.repository.CollectionRepository;
import fr.myhome.server.repository.UserRepository;
import fr.myhome.server.service.implementation.AuthenticationServiceImpl;
import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;
import fr.myhome.server.tools.AuthenticationFacade;
import fr.myhome.server.tools.CookieUtil;
import fr.myhome.server.tools.JwtTokenUtil;

public class RefreshAccessTokenTest extends AbstractMotherIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionPermissionRepository collectionPermissionRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CookieUtil cookieUtil;

    protected AuthenticationServiceImpl authenticationServiceImpl;

    @Override
    protected void initDataBeforeEach() {
        this.authenticationServiceImpl = new AuthenticationServiceImpl(jwtTokenUtil, cookieUtil, authenticationFacade, passwordEncoder, userRepository, collectionRepository, collectionPermissionRepository);
    }

    @Test
    public void testNoCookies() {
        Assertions.assertThrows(NoRememberMeTokenCookie.class, () -> {
            this.authenticationServiceImpl.refreshAccessToken(null);
        });
    }

    @Test
    public void testWithoutRefreshTokenCookie() {
        final Cookie cookie = new Cookie(this.testFactory.getRandomAlphanumericString(), this.testFactory.getRandomAlphanumericString());
        final Cookie[] cookies = new Cookie[]{cookie};

        Assertions.assertThrows(TokenMatchException.class, () -> {
            this.authenticationServiceImpl.refreshAccessToken(cookies);
        });
    }

    @Test
    public void testUserNotExist() {
        final TokenDTO tokenDTO = this.jwtTokenUtil.getRememberMeToken(this.testFactory.getRandomAlphanumericString());
        final Cookie cookie = new Cookie(CookieUtil.REMEMBER_ME_TOKEN_COOKIE_NAME, tokenDTO.getJwt());
        final Cookie[] cookies = new Cookie[]{cookie};

        Assertions.assertThrows(UserNotExistException.class, () -> {
            this.authenticationServiceImpl.refreshAccessToken(cookies);
        });
    }

    @Test
    public void testUserRefreshTokenDoesntMatch() {
        final User user = this.testFactory.getUser();
        final TokenDTO tokenDTO = this.jwtTokenUtil.getRememberMeToken(user.getUsername());
        final Cookie cookie = new Cookie(CookieUtil.REMEMBER_ME_TOKEN_COOKIE_NAME, tokenDTO.getJwt());
        final Cookie[] cookies = new Cookie[]{cookie};

        Assertions.assertThrows(TokenMatchException.class, () -> {
            this.authenticationServiceImpl.refreshAccessToken(cookies);
        });
    }

    @Test
    public void testRefreshAccessTokenOkNotRememberMe() {
        final User user = this.testFactory.getUser();
        final TokenDTO tokenDTO = this.jwtTokenUtil.getRememberMeToken(user.getUsername());
        user.setRememberMeToken(tokenDTO.getJwt());
        this.userRepository.save(user);
        final Cookie cookie = new Cookie(CookieUtil.REMEMBER_ME_TOKEN_COOKIE_NAME, tokenDTO.getJwt());
        final Cookie[] cookies = new Cookie[]{cookie};

        final HttpHeaders httpheaders = this.authenticationServiceImpl.refreshAccessToken(cookies);

        final List<String> headers = httpheaders.get(HttpHeaders.SET_COOKIE);

        Assertions.assertEquals(2, headers.size());
        headers.forEach(header -> {
            final String[] values = header.split("=");
            if(!values[0].equals(CookieUtil.REMEMBER_ME_TOKEN_COOKIE_NAME) && !values[0].equals(CookieUtil.ACCESS_TOKEN_COOKIE_NAME)){
                Assertions.fail();
            }
        });
    }

    @Test
    public void testRefreshAccessTokenOkRememberMe() {
        final User user = this.testFactory.getUser();
        final TokenDTO tokenDTO = this.jwtTokenUtil.getRememberMeToken(user.getUsername());
        user.setRememberMeToken(tokenDTO.getJwt());
        this.userRepository.save(user);
        final Cookie cookie = new Cookie(CookieUtil.REMEMBER_ME_TOKEN_COOKIE_NAME, tokenDTO.getJwt());
        final Cookie[] cookies = new Cookie[]{cookie};

        final HttpHeaders httpheaders = this.authenticationServiceImpl.refreshAccessToken(cookies);

        final List<String> headers = httpheaders.get(HttpHeaders.SET_COOKIE);

        Assertions.assertEquals(2, headers.size());
        headers.forEach(header -> {
            final String[] values = header.split("=");
            if(!values[0].equals(CookieUtil.REMEMBER_ME_TOKEN_COOKIE_NAME) && !values[0].equals(CookieUtil.ACCESS_TOKEN_COOKIE_NAME)){
                Assertions.fail();
            }
        });
    }
    
}
