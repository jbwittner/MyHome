package fr.myhome.server.tools.cookieutil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpCookie;

import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;
import fr.myhome.server.tools.CookieUtil;

public class TestCreateRefreshTokenCookie extends AbstractMotherIntegrationTest {

    private CookieUtil cookieUtil;

    @Override
    protected void initDataBeforeEach() {
        this.cookieUtil = new CookieUtil();
    }

    @Test
    public void testCreateRefreshTokenCookie(){
        final String value = this.testFactory.getRandomAlphanumericString();
        final HttpCookie httpCookie = this.cookieUtil.createRefreshTokenCookie(value, 1000L);
        Assertions.assertEquals(value, httpCookie.getValue());
        Assertions.assertEquals(CookieUtil.REFRESH_TOKEN_COOKIE_NAME, httpCookie.getName());
    }
    
}