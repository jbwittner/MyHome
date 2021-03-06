package fr.myhome.server.service.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.myhome.server.dto.UserDTOBuilder;
import fr.myhome.server.exception.LoginException;
import fr.myhome.server.exception.NoRememberMeTokenCookie;
import fr.myhome.server.exception.TokenMatchException;
import fr.myhome.server.exception.UserEmailAlreadyExistException;
import fr.myhome.server.exception.UserNotExistException;
import fr.myhome.server.exception.UsernameAlreadyExistException;
import fr.myhome.server.generated.model.LoginParameter;
import fr.myhome.server.generated.model.TokenDTO;
import fr.myhome.server.generated.model.UserDTO;
import fr.myhome.server.generated.model.UserRegistrationParameter;
import fr.myhome.server.model.User;
import fr.myhome.server.model.enumerate.Role;
import fr.myhome.server.repository.UserRepository;
import fr.myhome.server.service.AuthenticationService;
import fr.myhome.server.tools.CookieUtil;
import fr.myhome.server.tools.JwtTokenUtil;

@Service
@Transactional
public class AuthenticationServiceImpl extends MotherServiceImpl implements AuthenticationService {

    private final JwtTokenUtil jwtTokenUtil;
    private final CookieUtil cookieUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final UserDTOBuilder USER_DTO_BUILDER = new UserDTOBuilder();

    @Autowired
    public AuthenticationServiceImpl(final JwtTokenUtil jwtTokenUtil, final CookieUtil cookieUtil,
     final AuthenticationManager authenticationManager, final PasswordEncoder passwordEncoder, final UserRepository userRepository){
        super();
        this.jwtTokenUtil = jwtTokenUtil;
        this.cookieUtil = cookieUtil;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO registration(final UserRegistrationParameter userRegistrationParameter) {

        if (this.userRepository.existsByEmail(userRegistrationParameter.getEmail())) {
            throw new UserEmailAlreadyExistException(userRegistrationParameter.getEmail());
        } else if (this.userRepository.existsByUsername(userRegistrationParameter.getUsername())) {
            throw new UsernameAlreadyExistException(userRegistrationParameter.getUsername());
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

        this.logger.info("Regisatrion of : {}", user.getUsername());

        return USER_DTO_BUILDER.transform(user);
    }

    @Override
    public HttpHeaders login(final LoginParameter loginParameter) {
        
        final UsernamePasswordAuthenticationToken loginCredentials =
                new UsernamePasswordAuthenticationToken(
                        loginParameter.getUsername(), loginParameter.getPassword());

        try {
            authenticationManager.authenticate(loginCredentials);
        } catch (DisabledException | LockedException | BadCredentialsException e) {
               throw new LoginException(e);
        }

        final TokenDTO accessTokenDTO = this.jwtTokenUtil.getAccessToken(loginParameter.getUsername());

        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, this.cookieUtil.createAccessTokenCookie(accessTokenDTO.getJwt()).toString());

        final User user = this.userRepository.findByUsername(loginParameter.getUsername()).get();

        if(loginParameter.getRememberMe() == true){
            final TokenDTO rememberMeTokenDTO = this.jwtTokenUtil.getRememberMeToken(loginParameter.getUsername());
            responseHeaders.add(HttpHeaders.SET_COOKIE, this.cookieUtil.createRememberMeTokenCookie(rememberMeTokenDTO.getJwt(), rememberMeTokenDTO.getDuration()).toString());
            user.setRememberMeToken(rememberMeTokenDTO.getJwt());

        }

        this.userRepository.save(user);

        return responseHeaders;
    }

    @Override
    public HttpHeaders refreshAccessToken(final Cookie[] cookies){

        if(cookies == null) {
            throw new NoRememberMeTokenCookie();
        }

        final Cookie rememberMeTokenCookie = Arrays.stream(cookies)
                                            .filter(cookie -> CookieUtil.REMEMBER_ME_TOKEN_COOKIE_NAME.equals(cookie.getName()))
                                            .findFirst()
                                            .orElseThrow(() -> new TokenMatchException());

        final String rememberMeToken = rememberMeTokenCookie.getValue();

        final String userName = this.jwtTokenUtil.getUserName(rememberMeToken);

        final User user = this.userRepository.findByUsername(userName)
            .orElseThrow(() -> new UserNotExistException(userName));

        user.isRememberMeTokenMath(rememberMeToken);

        final TokenDTO rememberMeTokenDTO = this.jwtTokenUtil.getRememberMeToken(userName);
        final TokenDTO accessTokenDTO = this.jwtTokenUtil.getAccessToken(userName);
        
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, this.cookieUtil.createAccessTokenCookie(accessTokenDTO.getJwt()).toString());
        responseHeaders.add(HttpHeaders.SET_COOKIE, this.cookieUtil.createRememberMeTokenCookie(rememberMeTokenDTO.getJwt(), rememberMeTokenDTO.getDuration()).toString());

        user.setRememberMeToken(rememberMeTokenDTO.getJwt());

        this.userRepository.save(user);

        return responseHeaders;

    }

    @Override
    public HttpHeaders logout() {
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, this.cookieUtil.deleteCookie(CookieUtil.ACCESS_TOKEN_COOKIE_NAME).toString());
        responseHeaders.add(HttpHeaders.SET_COOKIE, this.cookieUtil.deleteCookie(CookieUtil.REMEMBER_ME_TOKEN_COOKIE_NAME).toString());
        return responseHeaders;
    }

    
    
}
