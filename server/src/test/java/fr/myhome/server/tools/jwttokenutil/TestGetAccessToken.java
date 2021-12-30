package fr.myhome.server.tools.jwttokenutil;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import fr.myhome.server.generated.model.TokenDTO;
import fr.myhome.server.generated.model.TokenTypeEnum;
import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;
import fr.myhome.server.tools.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TestGetAccessToken extends AbstractMotherIntegrationTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${application.jwt.secret:MySuperSecretPassPhraseForApplication}")
    private String secret;
 
    @Value("${application.jwt.audience:MyHomeDev}")
    private String audience;
 
    @Value("${application.jwt.refreshTokenExpirationSec:2592000}") //30 days
    private long refreshtokenExpirationSec;

    @Override
    protected void initDataBeforeEach() {}

    @Test
    public void testGetRefreshToken(){
        final String userName = this.testFactory.getRandomAlphanumericString();
        final TokenDTO tokenDTO = this.jwtTokenUtil.getRefreshToken(userName);

        final String jwt = tokenDTO.getJwt();

        Assertions.assertEquals(TokenTypeEnum.REFRESH_TOKEN, tokenDTO.getType());
        Assertions.assertEquals(this.refreshtokenExpirationSec, tokenDTO.getDuration());

        final byte[] bytes = this.secret.getBytes(StandardCharsets.UTF_8);
        final SecretKey secretKey = Keys.hmacShaKeyFor(bytes);

        final Jws<Claims> claims =
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt);
        
        final Claims body = claims.getBody();

        final long duration = body.getExpiration().getTime() - body.getIssuedAt().getTime();
        final long rest = Math.abs(duration - this.refreshtokenExpirationSec*1000);

        Assertions.assertEquals(7, body.size());
        Assertions.assertEquals(this.audience, body.getAudience());
        Assertions.assertEquals(JwtTokenUtil.JWT_ISSUER + "_" + this.audience, body.getIssuer());
        Assertions.assertEquals(TokenTypeEnum.REFRESH_TOKEN.getValue(), body.getSubject());
        Assertions.assertEquals(userName, body.get(JwtTokenUtil.CLAIM_USERNAME));
        Assertions.assertTrue(rest < 10);


    }
}
