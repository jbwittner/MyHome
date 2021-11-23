package fr.myhome.server.service.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import fr.myhome.server.exception.LoginException;
import fr.myhome.server.generated.model.LoginParameter;
import fr.myhome.server.generated.model.UserDTO;
import fr.myhome.server.generated.model.UserRegistrationParameter;
import fr.myhome.server.service.AuthenticationService;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(final AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDTO registration(UserRegistrationParameter userRegistrationParameter) {
        return null;
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
