package com.quironlabs.api.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.quironlabs.api.models.entities.AuthUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    
    @Value("${jwt.expires.segs}")
    private Integer EXPIRES_SEGS;

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    
    
    public String createJwt (String username) {
        logger.debug("JWT Secret: {}", SECRET_KEY);
        logger.debug("JWT expire: {}", EXPIRES_SEGS);
        
        Integer expireDuration = EXPIRES_SEGS * 1000;
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("typ", "jwt");
        
        logger.debug("Key to sign: {}", key.toString());

        Date issued = new Date();
        Date expires = new Date(System.currentTimeMillis() + expireDuration);
        
        logger.debug("JWT  issued: {}", issued);
        logger.debug("JWT expires: {}", expires);
        logger.debug("JWT  secret: {}", SECRET_KEY);
        logger.debug("JWT  duration: {}", expireDuration);

        UUID uuid = UUID.randomUUID();
        logger.debug("JWT    UUID: {}", uuid);

        return Jwts.builder()
            .id(uuid.toString())
            .issuer("QuironLabs")
            .audience().add("PublicDomain").and()
            .header().add(headers).and()
            .subject(username)
            .issuedAt(issued)
            .notBefore(issued)
            .expiration(expires)
            
            .signWith(key)
            .compact();
    }
    
    public void validateAccessToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            
            // If JWT has not been parsed correctly, an exception is thrown.
            Jwt<?, ?> jwt = Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(token);
           
            logger.info("JWT data: {}", jwt);

        } catch (JwtException e) {
            logger.error("JWT Error: {}", e.getMessage());
            
            // Throw the same exception to up level with the exception detail to report in Handler.
            throw e;
        }
    }
    
    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        
        Jwt<?, ?> jwt = Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(token);
        
        return (Claims) jwt.getPayload();
    }

    public AuthUser getUserForAuth(String basicAuth) {
        String authBase64 = basicAuth.replace("Basic ", "");
        byte[] authRawB = Base64.getDecoder().decode(authBase64);

        String authRaw = new String(authRawB);
        String[] authUser = authRaw.split(":");

        return new AuthUser(authUser[0], authUser[1]);
    }
    
    public UUID getUUID(String rawToken) {
        String token = rawToken.replace("Bearer ", "");
        Claims claims = parseClaims(token);

        return UUID.fromString((String) claims.get("jti"));
    }
    
    public Authentication getAuthentication(HttpServletRequest request, String[] roles) throws IOException {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");

        // si hay un token presente, entonces lo validamos
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            
            // If JWT has not been parsed correctly, an exception is thrown.
            Jwt<?, ?> jwt = Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(token);
           
            logger.info("JWT data: {}", jwt);
            logger.info("JWT data: {}", jwt.getPayload());
            
            return new UsernamePasswordAuthenticationToken("username", null, null);
                
        } catch (JwtException e) {
            logger.error("JWT Error: {}", e.getMessage());
            
            // Throw the same exception to up level with the exception detail to report in Handler.
            // throw e;
            return null;
        }
    }
}
