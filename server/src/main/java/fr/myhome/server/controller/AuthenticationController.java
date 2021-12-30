package fr.myhome.server.controller;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import fr.myhome.server.generated.api.AuthenticationApi;
import fr.myhome.server.generated.model.LoginParameter;
import fr.myhome.server.generated.model.UserRegistrationParameter;
import fr.myhome.server.service.AuthenticationService;

@RestController
public class AuthenticationController extends BaseRestController implements AuthenticationApi {

    private AuthenticationService authenticationService;

    private HttpServletRequest httpServletRequest;

    @Autowired
    AuthenticationController(final AuthenticationService authenticationService, final HttpServletRequest httpServletRequest){
        super();
        this.authenticationService = authenticationService;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public ResponseEntity<Void> login(final LoginParameter loginParameter) {
        final HttpHeaders responseHeaders = this.authenticationService.login(loginParameter);
        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).build();
    }

    @Override
    public ResponseEntity<Void> registration(final UserRegistrationParameter userRegistrationParameter) {
        this.authenticationService.registration(userRegistrationParameter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> connectionTest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> refreshAccessToken(){
        final Cookie[] cookies = this.httpServletRequest.getCookies();
        final HttpHeaders responseHeaders = this.authenticationService.refreshAccessToken(cookies);
        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).build();
    }
    
}
