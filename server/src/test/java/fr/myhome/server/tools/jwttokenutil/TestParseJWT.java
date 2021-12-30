package fr.myhome.server.tools.jwttokenutil;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import fr.myhome.server.exception.BadTokenException;
import fr.myhome.server.generated.model.TokenDTO;
import fr.myhome.server.generated.model.TokenTypeEnum;
import fr.myhome.server.testhelper.AbstractMotherIntegrationTest;
import fr.myhome.server.tools.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TestParseJWT extends AbstractMotherIntegrationTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${application.jwt.secret:MySuperSecretPassPhraseForApplication}")
    private String secret;
 
    @Value("${application.jwt.audience:MyHomeDev}")
    private String audience;
 
    @Value("${application.jwt.accessTokenExpirationSec:3600}") //1 h
    private long accessTokenExpirationSec;

    private String token;

    private String username;

    @Override
    protected void initDataBeforeEach() {
        this.username = this.testFactory.getRandomAlphanumericString();
        final TokenDTO tokenDTO = this.jwtTokenUtil.getAccessToken(this.username);
        this.token = tokenDTO.getJwt();
    }

    @Test
    public void testParseJWT(){
        final Jws<Claims> jwsClaims = this.jwtTokenUtil.parseJWT(this.token);

        final Claims body = jwsClaims.getBody();

        final long duration = body.getExpiration().getTime() - body.getIssuedAt().getTime();
        final long rest = Math.abs(duration - this.accessTokenExpirationSec*1000);

        Assertions.assertEquals(7, body.size());
        Assertions.assertEquals(this.audience, body.getAudience());
        Assertions.assertEquals(JwtTokenUtil.JWT_ISSUER + "_" + this.audience, body.getIssuer());
        Assertions.assertEquals(TokenTypeEnum.ACCESS_TOKEN.getValue(), body.getSubject());
        Assertions.assertEquals(this.username, body.get(JwtTokenUtil.CLAIM_USERNAME));
        Assertions.assertTrue(rest < 10);

    }

    @Test
    public void testBadIssuer() {
        final byte[] bytes = this.secret.getBytes(StandardCharsets.UTF_8);
        final SecretKey secretKey = Keys.hmacShaKeyFor(bytes);

        final String randomUUID = UUID.randomUUID().toString();

        final Date expiration = Date.from(Instant.now().plus(Duration.ofSeconds(this.testFactory.getRandomInteger())));
        final Date issuedAt = Date.from(Instant.now());

        final String randomString = this.testFactory.getRandomAlphanumericString();

        final JwtBuilder jwtBuilder = 
            Jwts.builder()
                .setId(randomUUID)
                .setSubject(TokenTypeEnum.ACCESS_TOKEN.getValue())
                .setIssuer(randomString)
                .setAudience(this.audience)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration);
        
        final String jwt = jwtBuilder.signWith(secretKey).compact();

        final BadTokenException exception = Assertions.assertThrows(BadTokenException.class,
            () -> this.jwtTokenUtil.parseJWT(jwt));

        final String expectedMessage = String.format("Problem with the token : Bad issuer - %s", randomString);

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testBadAudiance() {
        final byte[] bytes = this.secret.getBytes(StandardCharsets.UTF_8);
        final SecretKey secretKey = Keys.hmacShaKeyFor(bytes);

        final String randomUUID = UUID.randomUUID().toString();

        final Date expiration = Date.from(Instant.now().plus(Duration.ofSeconds(this.testFactory.getRandomInteger())));
        final Date issuedAt = Date.from(Instant.now());

        final String randomString = this.testFactory.getRandomAlphanumericString();

        final JwtBuilder jwtBuilder = 
            Jwts.builder()
                .setId(randomUUID)
                .setSubject(TokenTypeEnum.ACCESS_TOKEN.getValue())
                .setIssuer(JwtTokenUtil.JWT_ISSUER + "_" + this.audience)
                .setAudience(randomString)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration);
        
        final String jwt = jwtBuilder.signWith(secretKey).compact();

        final BadTokenException exception = Assertions.assertThrows(BadTokenException.class,
            () -> this.jwtTokenUtil.parseJWT(jwt));

        final String expectedMessage = String.format("Problem with the token : Bad audiance - %s", randomString);

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testParseJWTfail(){
        Assertions.assertThrows(BadTokenException.class,
            () -> this.jwtTokenUtil.parseJWT(this.testFactory.getRandomAlphanumericString()));
    }
}
