package fr.myhome.server.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.myhome.server.exception.NoAccessTokenCookie;
import fr.myhome.server.tools.CookieUtil;
import fr.myhome.server.tools.JwtTokenUtil;

public class AuthTokenFilter extends OncePerRequestFilter {

    public static final String _BEARER = "Bearer ";
 
    private UserDetailsService UserDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    private final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    public AuthTokenFilter(final UserDetailsService userDetailsService, final JwtTokenUtil jwtTokenUtil){
        super();
        this.UserDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    private String getJwtFromCookie(final HttpServletRequest request) {
        
        final Cookie[] cookies = request.getCookies();

        if(cookies == null) {
            throw new NoAccessTokenCookie(request.getRequestURI());
        }

        final Optional<Cookie> optionalCookie = Arrays
            .stream(cookies)
            .filter(cookie -> CookieUtil.ACCESS_TOKEN_COOKIE_NAME.equals(cookie.getName()))
            .findFirst();
        
        final String returnValue = optionalCookie
            .orElseThrow(() -> new NoAccessTokenCookie(request.getRequestURI()))
            .getValue();

        return returnValue;
    }

    @Override
    public void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {

        try {
            final String jwtToken = this.getJwtFromCookie(request);

            final String username = this.jwtTokenUtil.getUserName(jwtToken);

            logger.info(String.format("AUTHENTICATION - User : %s - Endpoint: %s", username, request.getRequestURI()));
    
            final UserDetails userDetails = this.UserDetailsService.loadUserByUsername(username);
    
            final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                        
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
        
    }

    @Override
    public boolean shouldNotFilter(final HttpServletRequest request) throws ServletException {
		final String path = request.getRequestURI();

        final boolean value = Arrays.stream(SecurityConfiguration.PUBLIC_ENDPOINTS)
            .anyMatch(e -> new AntPathMatcher().match(e, path));

        return value;
	}
    
}
