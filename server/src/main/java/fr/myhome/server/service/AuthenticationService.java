package fr.myhome.server.service;

import fr.myhome.server.generated.model.LoginParameter;
import fr.myhome.server.generated.model.UserDTO;
import fr.myhome.server.generated.model.UserRegistrationParameter;

public interface AuthenticationService {
    
    UserDTO registration(final UserRegistrationParameter userRegistrationParameter);

    void login(final LoginParameter loginParameter);

}
