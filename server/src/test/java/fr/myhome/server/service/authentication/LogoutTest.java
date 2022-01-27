package fr.myhome.server.service.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.myhome.server.repository.CollectionPermissionRepository;
import fr.myhome.server.repository.CollectionRepository;
import fr.myhome.server.repository.UserRepository;
import fr.myhome.server.service.implementation.AuthenticationServiceImpl;
import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;
import fr.myhome.server.tools.AuthenticationFacade;
import fr.myhome.server.tools.CookieUtil;
import fr.myhome.server.tools.JwtTokenUtil;

public class LogoutTest extends AbstractMotherIntegrationTest {

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
    public void LogoutTestOk() {
        this.authenticationServiceImpl.logout();
    }
    
}
