package fr.myhome.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import fr.myhome.server.generated.api.AuthenticationApi;
import fr.myhome.server.generated.model.LoginParameter;
import fr.myhome.server.generated.model.UserRegistrationParameter;
import fr.myhome.server.service.AuthenticationService;

@RestController
public class AuthenticationController implements AuthenticationApi {

    private AuthenticationService authenticationService;

    @Autowired
    AuthenticationController(final AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<Void> login(final LoginParameter loginParameter) {
        this.authenticationService.login(loginParameter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> registration(final UserRegistrationParameter userRegistrationParameter) {
        this.authenticationService.registration(userRegistrationParameter);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
