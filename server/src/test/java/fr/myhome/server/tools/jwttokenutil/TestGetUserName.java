package fr.myhome.server.tools.jwttokenutil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.myhome.server.generated.model.TokenDTO;
import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;
import fr.myhome.server.tools.JwtTokenUtil;

public class TestGetUserName extends AbstractMotherIntegrationTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String token;

    private String username;

    @Override
    protected void initDataBeforeEach() {
        this.username = this.testFactory.getRandomAlphanumericString();
        final TokenDTO tokenDTO = this.jwtTokenUtil.getAccessToken(this.username);
        this.token = tokenDTO.getJwt();
    }

    @Test
    public void testGetUserName(){
        final String result = this.jwtTokenUtil.getUserName(this.token);
        Assertions.assertEquals(this.username, result);
    }
    
}
