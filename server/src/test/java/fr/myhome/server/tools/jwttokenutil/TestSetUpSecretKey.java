package fr.myhome.server.tools.jwttokenutil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;
import fr.myhome.server.tools.JwtTokenUtil;
import io.jsonwebtoken.security.WeakKeyException;

public class TestSetUpSecretKey extends AbstractMotherIntegrationTest {

    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void initDataBeforeEach() {
        this.jwtTokenUtil = new JwtTokenUtil();
        ReflectionTestUtils.setField(this.jwtTokenUtil, "audience", this.testFactory.getRandomAlphanumericString());
        ReflectionTestUtils.setField(this.jwtTokenUtil, "accessTokenExpirationSec", this.testFactory.getRandomInteger());
        ReflectionTestUtils.setField(this.jwtTokenUtil, "rememberMeTokenExpirationSec", this.testFactory.getRandomInteger());
    }

    @Test
    public void testParseJWT(){
        ReflectionTestUtils.setField(this.jwtTokenUtil, "secret", this.testFactory.getRandomAlphanumericString(32));
        Assertions.assertDoesNotThrow(() -> this.jwtTokenUtil.setUpSecretKey());
    }

    @Test
    public void testSetUpSecretKeyWeakKeyException() {
        ReflectionTestUtils.setField(this.jwtTokenUtil, "secret", this.testFactory.getRandomAlphanumericString(31));
        Assertions.assertThrows(WeakKeyException.class, () -> this.jwtTokenUtil.setUpSecretKey());
    }

}
