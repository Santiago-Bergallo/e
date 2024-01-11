package com.example.eCommerce.v2.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.eCommerce.v2.model.LocalUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;


@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
            private String algorithmKey;

    @Value("${jwt.issuer}")
            private String issuer;

    @Value("${jwt.expiry.in.seconds}")
            private int expiryInSeconds;

    Algorithm algorithm;

    private static final String USERNAME = "username";
    private static final String EMAIL_KEY = "email";

    @PostConstruct
    public void postconstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String CreateJWT(LocalUser localUser) {
        return JWT.create()
                .withClaim(USERNAME, localUser.getUsername())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * expiryInSeconds))
                .sign(algorithm);
    }

    public String createVerificationToken(LocalUser localUser) {
        return JWT.create()
                .withClaim(EMAIL_KEY, localUser.getEmail())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * expiryInSeconds))
                .sign(algorithm);
    }

    public String getUserName(String token) throws JWTVerificationException {

    DecodedJWT jwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
    return jwt.getClaim(USERNAME).asString();
    }
}
