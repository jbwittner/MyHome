package fr.myhome.server.tools;

import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    
    public static String ACCESS_TOKEN_COOKIE_NAME = "ACCESS_TOKEN_COOKIE";
    public static String REFRESH_TOKEN_COOKIE_NAME = "REFRESH_TOKEN_COOKIE";

    private HttpCookie creacteCookie(final String name, final String token, final Long duration) {
        return ResponseCookie.from(name, token)
                .sameSite("Strict")
                .maxAge(duration)
                .httpOnly(true)
                .path("/")
                .build();
    }

    public HttpCookie createAccessTokenCookie(final String token, final Long duration) {
        return this.creacteCookie(ACCESS_TOKEN_COOKIE_NAME, token, duration);
    }

    public HttpCookie createRefreshTokenCookie(final String token, final Long duration) {
        return this.creacteCookie(REFRESH_TOKEN_COOKIE_NAME, token, duration);
    }
}