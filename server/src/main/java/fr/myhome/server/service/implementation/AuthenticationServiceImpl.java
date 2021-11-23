package fr.myhome.server.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.myhome.server.dto.UserDTOBuilder;
import fr.myhome.server.exception.LoginException;
import fr.myhome.server.exception.UserEmailAlreadyExistException;
import fr.myhome.server.exception.UserNameAlreadyExistException;
import fr.myhome.server.generated.model.LoginParameter;
import fr.myhome.server.generated.model.UserDTO;
import fr.myhome.server.generated.model.UserRegistrationParameter;
import fr.myhome.server.model.User;
import fr.myhome.server.model.enumerate.Role;
import fr.myhome.server.repository.UserRepository;
import fr.myhome.server.service.AuthenticationService;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final UserDTOBuilder USER_DTO_BUILDER = new UserDTOBuilder();

    @Autowired
    public AuthenticationServiceImpl(final AuthenticationManager authenticationManager, final UserRepository userRepository, final PasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO registration(UserRegistrationParameter userRegistrationParameter) {

        if (this.userRepository.existsByEmail(userRegistrationParameter.getEmail())) {
            throw new UserEmailAlreadyExistException(userRegistrationParameter.getEmail());
        } else if (this.userRepository.existsByUsername(userRegistrationParameter.getUsername())) {
            throw new UserNameAlreadyExistException(userRegistrationParameter.getUsername());
        }

        final User user = new User();
        user.setEmail(userRegistrationParameter.getEmail());

        final String firstName = StringUtils.capitalize(userRegistrationParameter.getFirstName().toLowerCase());
        user.setFirstName(firstName);

        user.setLastName(userRegistrationParameter.getLastName().toUpperCase());
        user.setUsername(userRegistrationParameter.getUsername().toLowerCase());

        user.setPassword(this.passwordEncoder.encode(userRegistrationParameter.getPassword()));
        
        final List<Role> roles = new ArrayList<>();
        final List<User> users = this.userRepository.findAll();

        if(users.isEmpty()){
            roles.add(Role.ADMIN);
        }

        roles.add(Role.USER);

        user.setRoles(roles);

        this.userRepository.save(user);

        return USER_DTO_BUILDER.transform(user);
    }

    @Override
    public void login(LoginParameter loginParameter) {
        
        final UsernamePasswordAuthenticationToken loginCredentials =
                new UsernamePasswordAuthenticationToken(
                        loginParameter.getUsername(), loginParameter.getPassword());

        try {
            authenticationManager.authenticate(loginCredentials);
        } catch (DisabledException | LockedException | BadCredentialsException e) {
               throw new LoginException(e);
        }

    }
    
}
