package com.syu.capsbe.global.jwt;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.syu.capsbe.global.jwt.dto.JwtResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtProvider {

    private final String issuer;
    private final UserDetailsService userDetailsService;
    private final JwkProvider jwkProvider;
    private static final long TOKEN_VALID_TIME = 30 * 60 * 1000L; // 30 minutes
    private final Key secretKey;

    public JwtProvider(
            @Value("${auth0.domain}") String issuer, UserDetailsService userDetailsService,
            @Value("${JWT_KEY}") String secretKey
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.issuer = issuer;
        this.userDetailsService = userDetailsService;
        this.jwkProvider = new UrlJwkProvider(issuer);
    }

    public JwtResponseDto generateToken(String email, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);

        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + TOKEN_VALID_TIME);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiresAt)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return JwtResponseDto.of(accessToken, expiresAt);
    }

    public Authentication getAuthentication(String token) {
        try {
            DecodedJWT jwt = verifyToken(token);
            String email = jwt.getSubject();

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (Exception e) {
            log.error("JWT 검증 실패", e);
            return null;
        }
    }

    public DecodedJWT verifyToken(String token) throws Exception {
        DecodedJWT decodedJWT = JWT.decode(token);

        Jwk jwk = jwkProvider.get(decodedJWT.getKeyId());
        RSAPublicKey publicKey = (RSAPublicKey) jwk.getPublicKey();

        com.auth0.jwt.algorithms.Algorithm algorithm = com.auth0.jwt.algorithms.Algorithm.RSA256(publicKey, null);
        com.auth0.jwt.JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();

        return verifier.verify(token);
    }

    public boolean validateToken(String token) {
        try {
            verifyToken(token);
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT Token", e);
            return false;
        }
    }
}
