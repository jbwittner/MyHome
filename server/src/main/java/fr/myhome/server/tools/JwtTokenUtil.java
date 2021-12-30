package fr.myhome.server.tools;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.myhome.server.exception.BadTokenException;
import fr.myhome.server.generated.model.TokenDTO;
import fr.myhome.server.generated.model.TokenTypeEnum;
import fr.myhome.server.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;

@Component
public class JwtTokenUtil {

    public static final String JWT_ISSUER = "MYHOME";

    public static final String CLAIM_USERNAME = "Username";

    @Value("${application.jwt.secret:MySuperSecretPassPhraseForApplication}")
    private String secret;
 
    @Value("${application.jwt.audience:MyHomeDev}")
    private String audience;
 
    @Value("${application.jwt.accessTokenExpirationSec:3600}") //1 h
    private long accessTokenExpirationSec;

    @Value("${application.jwt.refreshTokenExpirationSec:2592000}") //30 days
    private long refreshtokenExpirationSec;

    private SecretKey secretKey;

    private final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    @PostConstruct
    public void setUpSecretKey() throws WeakKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
        final byte[] bytes = this.secret.getBytes(StandardCharsets.UTF_8);
        logger.info("Secret bytes length : {}",bytes.length);
        this.secretKey = Keys.hmacShaKeyFor(bytes);
        logger.info("Secret key generation ok !");
    }

    public TokenDTO getToken(final String userName, final TokenTypeEnum tokenTypeEnum, final Long duration, final Map<String, Object> claims){

        final TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setType(tokenTypeEnum);
        tokenDTO.setDuration(duration);

        final Date expiration = Date.from(Instant.now().plus(Duration.ofSeconds(duration)));
        final Date issuedAt = Date.from(Instant.now());

        final String pattern = "dd-MM-yyyy HH:mm:ss";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        this.logger.info("Generation of new JWT [username : " + userName +
        " / subject : " + tokenTypeEnum.name() +
        " / issuedAt : " + simpleDateFormat.format(issuedAt) +
        " / expiration : " + simpleDateFormat.format(expiration)
        + "]");
            
        final String randomUUID = UUID.randomUUID().toString();

        final JwtBuilder jwtBuilder =
            Jwts.builder()
                .setId(randomUUID)
                .setSubject(tokenTypeEnum.name())
                .setIssuer(JWT_ISSUER + "_" + this.audience)
                .setAudience(this.audience)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration);

        jwtBuilder.addClaims(claims).claim(CLAIM_USERNAME, userName);

        final String jwt = jwtBuilder.signWith(secretKey).compact();

        tokenDTO.setJwt(jwt);

        return tokenDTO;
    }

    public TokenDTO getAccessToken(final String userName){
        final TokenDTO tokenDTO = this.getToken(userName, TokenTypeEnum.ACCESS_TOKEN, this.accessTokenExpirationSec, null);
        return tokenDTO;
    }

    public TokenDTO getRefreshToken(final String userName){
        final TokenDTO tokenDTO = this.getToken(userName, TokenTypeEnum.REFRESH_TOKEN, this.refreshtokenExpirationSec, null);
        return tokenDTO;
    }

    public Jws<Claims> parseJWT(final String jwtString) {

        Jws<Claims> claims = null;

        final String issuerApplication = JWT_ISSUER + "_" + this.audience;
 
        try {
            claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(jwtString);
            
        } catch (JwtException e) {
            throw new BadTokenException(e.getMessage());
        }

        if(!issuerApplication.equals(claims.getBody().getIssuer())){
            throw new BadTokenException(String.format("Bad issuer - %s", claims.getBody().getIssuer()));
        } else if(!this.audience.equals(claims.getBody().getAudience())){
            throw new BadTokenException(String.format("Bad audiance - %s", claims.getBody().getAudience()));
        }

        return claims;
    }

    public String getUserName(final String jwtString) {

        final Jws<Claims> claims = this.parseJWT(jwtString);
        final String subject = (String) claims.getBody().get(JwtTokenUtil.CLAIM_USERNAME);

        return subject;

    }
}
