package me.ziok.application.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class JwtTokenProviderTest {

    private final String jwtSecret = "jwtTestSecret";
    private final int jwtExpirationInMs = 60000;
    private TokenProvider tokenProvider;

    @Before
    public void setup() {
        tokenProvider = new JwtTokenProvider();
        ReflectionTestUtils.setField(tokenProvider, "jwtSecret", jwtSecret);
        ReflectionTestUtils.setField(tokenProvider, "jwtExpirationInMs", jwtExpirationInMs);
    }

    @Test
    public void testReturnTrue() {
        boolean isTokenValid = tokenProvider.validateToken(createTokenCorrect());

        assertThat(isTokenValid).isEqualTo(true);
    }

    @Test
    public void testReturnFalseWhenJWThasInvalidSignature() {
        boolean isTokenValid = tokenProvider.validateToken(createTokenWithDifferentSignature());

        assertThat(isTokenValid).isEqualTo(false);
    }

    @Test
    public void testReturnFalseWhenJWTisMalformed() {
        Authentication authentication = createAuthentication();
        String token = tokenProvider.generateToken(authentication);
        String invalidToken = token.substring(1);
        boolean isTokenValid = tokenProvider.validateToken(invalidToken);

        assertThat(isTokenValid).isEqualTo(false);
    }

    @Test
    public void testReturnFalseWhenJWTisExpired() {
        ReflectionTestUtils.setField(tokenProvider, "jwtExpirationInMs", -jwtExpirationInMs);

        Authentication authentication = createAuthentication();
        String token = tokenProvider.generateToken(authentication);

        boolean isTokenValid = tokenProvider.validateToken(token);

        assertThat(isTokenValid).isEqualTo(false);
    }

    @Test
    public void testReturnFalseWhenJMTisUnsupported() {
        String unsupportedToken = createUnsupportedToken();

        boolean isTokenValid = tokenProvider.validateToken(unsupportedToken);

        assertThat(isTokenValid).isEqualTo(false);
    }

    @Test
    public void testReturnFalseWhenJWTisInvalid() {
        boolean isTokenValid = tokenProvider.validateToken("");

        assertThat(isTokenValid).isEqualTo(false);
    }


    private String createUnsupportedToken() {
        return Jwts.builder()
                .setPayload("payload")
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    private Authentication createAuthentication() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ANONYMOUS"));
        UserPrincipal userPrincipal = new UserPrincipal(0l,"anonymousEmail","anonymousePassword",authorities);
        return new UsernamePasswordAuthenticationToken(userPrincipal,"anonymous", authorities);



    }

    private String createTokenCorrect() {
        return Jwts.builder()
                .setSubject("anonymous")
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .setExpiration(new Date(new Date().getTime() + jwtExpirationInMs))
                .compact();
    }

    private String createTokenWithDifferentSignature() {
        return Jwts.builder()
                .setSubject("anonymous")
                .signWith(SignatureAlgorithm.HS512, "jwtTestSecretDifferent")
                .setExpiration(new Date(new Date().getTime() + jwtExpirationInMs))
                .compact();
    }
}