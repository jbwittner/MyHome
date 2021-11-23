package fr.myhome.server.service.authentication;

import java.util.List;
import java.util.Optional;

import com.github.javafaker.Name;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.myhome.server.exception.LoginException;
import fr.myhome.server.exception.UserEmailAlreadyExistException;
import fr.myhome.server.exception.UsernameAlreadyExistException;
import fr.myhome.server.generated.model.LoginParameter;
import fr.myhome.server.generated.model.UserRegistrationParameter;
import fr.myhome.server.model.User;
import fr.myhome.server.model.enumerate.Role;
import fr.myhome.server.repository.UserRepository;
import fr.myhome.server.service.implementation.AuthenticationServiceImpl;
import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;

public class LoginTest extends AbstractMotherIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    protected AuthenticationServiceImpl authenticationServiceImpl;

    @Override
    protected void initDataBeforeEach() {
        this.authenticationServiceImpl = new AuthenticationServiceImpl(authenticationManager, passwordEncoder, userRepository);
    }

    @Test
    protected void testLoginOk() {
        final User user = this.testFactory.getUser();
        final String nonEncodedPassword = this.testFactory.getRandomAlphanumericString();
        user.setPassword(passwordEncoder.encode(nonEncodedPassword));

        final LoginParameter loginParameter = new LoginParameter();
        loginParameter.setPassword(nonEncodedPassword);
        loginParameter.setUsername(user.getUsername());

        this.authenticationServiceImpl.login(loginParameter);
    }

    @Test
    protected void testLoginBadUsernameGoodPasswordNOk() {
        final User user = this.testFactory.getUser();
        final String nonEncodedPassword = this.testFactory.getRandomAlphanumericString();
        user.setPassword(passwordEncoder.encode(nonEncodedPassword));

        final LoginParameter loginParameter = new LoginParameter();
        loginParameter.setPassword(nonEncodedPassword);
        loginParameter.setUsername(this.testFactory.getRandomAlphanumericString());

        Assertions.assertThrows(LoginException.class, () -> {
            this.authenticationServiceImpl.login(loginParameter);
        });

    }

    @Test
    protected void testLoginGoodUsernameBasPasswordNOk() {
        final User user = this.testFactory.getUser();
        final String nonEncodedPassword = this.testFactory.getRandomAlphanumericString();
        user.setPassword(passwordEncoder.encode(nonEncodedPassword));

        final LoginParameter loginParameter = new LoginParameter();
        loginParameter.setPassword(this.testFactory.getRandomAlphanumericString());
        loginParameter.setUsername(user.getUsername());

        Assertions.assertThrows(LoginException.class, () -> {
            this.authenticationServiceImpl.login(loginParameter);
        });

    }
    
}
