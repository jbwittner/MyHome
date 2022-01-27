package fr.myhome.server.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import fr.myhome.server.exception.UserNotExistException;
import fr.myhome.server.model.User;
import fr.myhome.server.repository.UserRepository;

@Component
public class AuthenticationFacade {
    
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationFacade(final AuthenticationManager authenticationManager, final UserRepository userRepository){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return this.authenticationManager.authenticate(authentication);
    }

    public User getCurrentUser(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String userName = authentication.getName();
        final User user = userRepository.findByUsername(userName).orElseThrow(() -> new UserNotExistException(userName));
        return user;
    }

}
