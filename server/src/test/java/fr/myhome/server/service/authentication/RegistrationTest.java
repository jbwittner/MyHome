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

import fr.myhome.server.exception.UserEmailAlreadyExistException;
import fr.myhome.server.exception.UsernameAlreadyExistException;
import fr.myhome.server.generated.model.UserDTO;
import fr.myhome.server.generated.model.UserRegistrationParameter;
import fr.myhome.server.generated.model.UserRoleEnum;
import fr.myhome.server.model.User;
import fr.myhome.server.model.enumerate.Role;
import fr.myhome.server.repository.UserRepository;
import fr.myhome.server.service.implementation.AuthenticationServiceImpl;
import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;

public class RegistrationTest extends AbstractMotherIntegrationTest {

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
    protected void testRegistrationFirstUserOk(){
        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();
        
        final Name name = this.testFactory.name();
        
        final String username = name.username();
        final String nonEncodedPassword = this.testFactory.getRandomAlphanumericString();
        
        userRegistrationParameter.setUsername(username);
        userRegistrationParameter.setFirstName(name.firstName().toUpperCase());
        userRegistrationParameter.setLastName(name.lastName());
        userRegistrationParameter.setPassword(nonEncodedPassword);
        userRegistrationParameter.setEmail(this.testFactory.internet().emailAddress());

        final UserDTO userDto = this.authenticationServiceImpl.registration(userRegistrationParameter);

        final List<User> allUsers = this.userRepository.findAll();

        Assertions.assertEquals(1, allUsers.size());

        final Optional<User> optionalUser = this.userRepository.findByUsername(username);

        Assertions.assertTrue(optionalUser.isPresent());

        final User user = optionalUser.get();

        final Boolean passwordMatch = passwordEncoder.matches(userRegistrationParameter.getPassword(), user.getPassword());

        Assertions.assertEquals(userRegistrationParameter.getEmail(), user.getEmail());

        final String CapitalizedFirstName = StringUtils.capitalize(userRegistrationParameter.getFirstName().toLowerCase());
        Assertions.assertEquals(CapitalizedFirstName, user.getFirstName());

        Assertions.assertEquals(userRegistrationParameter.getLastName().toUpperCase(), user.getLastName());
        Assertions.assertEquals(userRegistrationParameter.getUsername(), user.getUsername());
        Assertions.assertTrue(passwordMatch);

        Assertions.assertTrue(user.getRoles().contains(Role.ADMIN));
        Assertions.assertTrue(user.getRoles().contains(Role.USER));

        Assertions.assertEquals(user.getEmail(), userDto.getEmail());
        Assertions.assertEquals(user.getLastName(), userDto.getLastName());
        Assertions.assertEquals(user.getFirstName(), userDto.getFirstName());
        Assertions.assertEquals(user.getUsername(), userDto.getUsername());
        Assertions.assertEquals(user.getRoles().size(), userDto.getRoles().size());

        Assertions.assertTrue(userDto.getRoles().contains(UserRoleEnum.ADMIN));
        Assertions.assertTrue(userDto.getRoles().contains(UserRoleEnum.USER));

    }

    @Test
    protected void testRegistrationNthUserOk(){
        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();
        
        this.testFactory.getUser();
        this.testFactory.getUser();
        this.testFactory.getUser();

        final Name name = this.testFactory.name();
        
        final String username = name.username();
        final String nonEncodedPassword = this.testFactory.getRandomAlphanumericString();
        
        userRegistrationParameter.setUsername(username);
        userRegistrationParameter.setFirstName(name.firstName().toUpperCase());
        userRegistrationParameter.setLastName(name.lastName());
        userRegistrationParameter.setPassword(nonEncodedPassword);
        userRegistrationParameter.setEmail(this.testFactory.internet().emailAddress());

        final UserDTO userDto = this.authenticationServiceImpl.registration(userRegistrationParameter);

        final List<User> allUsers = this.userRepository.findAll();

        Assertions.assertEquals(4, allUsers.size());

        final Optional<User> optionalUser = this.userRepository.findByUsername(username);

        Assertions.assertTrue(optionalUser.isPresent());

        final User user = optionalUser.get();

        final Boolean passwordMatch = passwordEncoder.matches(userRegistrationParameter.getPassword(), user.getPassword());

        Assertions.assertEquals(userRegistrationParameter.getEmail(), user.getEmail());

        final String CapitalizedFirstName = StringUtils.capitalize(userRegistrationParameter.getFirstName().toLowerCase());
        Assertions.assertEquals(CapitalizedFirstName, user.getFirstName());

        Assertions.assertEquals(userRegistrationParameter.getLastName().toUpperCase(), user.getLastName());
        Assertions.assertEquals(userRegistrationParameter.getUsername(), user.getUsername());
        Assertions.assertTrue(passwordMatch);

        Assertions.assertFalse(user.getRoles().contains(Role.ADMIN));
        Assertions.assertTrue(user.getRoles().contains(Role.USER));

        Assertions.assertEquals(user.getEmail(), userDto.getEmail());
        Assertions.assertEquals(user.getLastName(), userDto.getLastName());
        Assertions.assertEquals(user.getFirstName(), userDto.getFirstName());
        Assertions.assertEquals(user.getUsername(), userDto.getUsername());
        Assertions.assertEquals(user.getRoles().size(), userDto.getRoles().size());

        Assertions.assertFalse(userDto.getRoles().contains(UserRoleEnum.ADMIN));
        Assertions.assertTrue(userDto.getRoles().contains(UserRoleEnum.USER));
        
    }

    @Test
    protected void testUsernameAlreadyUsedNok(){
        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        final User user = this.testFactory.getUser();

        final Name name = this.testFactory.name();
        
        final String nonEncodedPassword = this.testFactory.getRandomAlphanumericString();
        
        userRegistrationParameter.setUsername(user.getUsername());
        userRegistrationParameter.setFirstName(name.firstName().toUpperCase());
        userRegistrationParameter.setLastName(name.lastName());
        userRegistrationParameter.setPassword(nonEncodedPassword);
        userRegistrationParameter.setEmail(this.testFactory.internet().emailAddress());

        Assertions.assertThrows(UsernameAlreadyExistException.class, () -> {
            this.authenticationServiceImpl.registration(userRegistrationParameter);
        });

    }

    @Test
    protected void testEmailAlreadyUsedNok(){
        final UserRegistrationParameter userRegistrationParameter = new UserRegistrationParameter();

        final User user = this.testFactory.getUser();

        final Name name = this.testFactory.name();
        
        final String nonEncodedPassword = this.testFactory.getRandomAlphanumericString();
        
        userRegistrationParameter.setUsername(name.username());
        userRegistrationParameter.setFirstName(name.firstName().toUpperCase());
        userRegistrationParameter.setLastName(name.lastName());
        userRegistrationParameter.setPassword(nonEncodedPassword);
        userRegistrationParameter.setEmail(user.getEmail());

        Assertions.assertThrows(UserEmailAlreadyExistException.class, () -> {
            this.authenticationServiceImpl.registration(userRegistrationParameter);
        });

    }
    
}
