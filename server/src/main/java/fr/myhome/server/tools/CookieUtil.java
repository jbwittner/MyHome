package fr.myhome.server.tools;

import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    
    public static String ACCESS_TOKEN_COOKIE_NAME = "ACCESS_TOKEN_COOKIE";
    public static String REMEMBER_ME_TOKEN_COOKIE_NAME = "REMEMBER_ME_TOKEN_COOKIE";

    private HttpCookie creacteCookie(final String name, final String token, final Long duration) {
        return ResponseCookie.from(name, token)
                .sameSite("Strict")
                .maxAge(duration)
                .httpOnly(true)
                .path("/")
                .build();
    }

    public HttpCookie createAccessTokenCookie(final String token) {
        return this.creacteCookie(ACCESS_TOKEN_COOKIE_NAME, token, -1L);
    }

    public HttpCookie createRememberMeTokenCookie(final String token, final Long duration) {
        return this.creacteCookie(REMEMBER_ME_TOKEN_COOKIE_NAME, token, duration);
    }

    public HttpCookie deleteCookie(final String name){
        //
        // To delete a cookie, we need to create a cookie that have the same
        // name with the cookie that we want to delete. We also need to set
        // the max age of the cookie to 0 and then add it to the Servlet's
        // response method.
        //
        return this.creacteCookie(name, "", 0L);
    }
}