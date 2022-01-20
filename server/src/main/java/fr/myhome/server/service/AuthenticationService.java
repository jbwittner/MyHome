package fr.myhome.server.service;

import javax.servlet.http.Cookie;

import org.springframework.http.HttpHeaders;

import fr.myhome.server.generated.model.LoginParameter;
import fr.myhome.server.generated.model.UserDTO;
import fr.myhome.server.generated.model.UserRegistrationParameter;

public interface AuthenticationService {
    
    UserDTO registration(final UserRegistrationParameter userRegistrationParameter);

    HttpHeaders login(final LoginParameter loginParameter);

    HttpHeaders logout();

    HttpHeaders refreshAccessToken(Cookie[] cookies);

}
