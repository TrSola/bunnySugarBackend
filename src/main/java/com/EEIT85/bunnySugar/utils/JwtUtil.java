package com.EEIT85.bunnySugar.utils;

import io.github.cdimascio.dotenv.Dotenv;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String jwtSecretKey;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    @Autowired
    public JwtUtil(Dotenv dotenv) {
        this.jwtSecretKey = dotenv.get("JWT_SECRET_KEY");
        if (this.jwtSecretKey == null || this.jwtSecretKey.isEmpty()) {
            throw new IllegalStateException("JWT secret key is not set");
        }
        this.algorithm = Algorithm.HMAC256(this.jwtSecretKey);
        this.verifier = JWT.require(algorithm).build();
    }

    /**
     * Generate a JWT token.
     *
     * @param claims Claims to include in the token.
     * @param expirationTime Token expiration time.
     * @return Generated JWT token.
     */
    public String generateToken(Map<String, Object> claims, Date expirationTime) {
        Map<String, Object> transformedClaims = new HashMap<>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            transformedClaims.put(entry.getKey(), entry.getValue());
        }

        return JWT.create()
                .withPayload(transformedClaims)
                .withExpiresAt(expirationTime)
                .sign(algorithm);
    }

    /**
     * Parse the JWT token and extract claims.
     *
     * @param token JWT token to parse.
     * @return Claims extracted from the token.
     * @throws IllegalArgumentException If the token is invalid or expired.
     */
    public Map<String, Object> parseToken(String token) {
        DecodedJWT decodedJWT = verifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        Map<String, Object> transformedClaims = new HashMap<>();

        for (Map.Entry<String, Claim> entry : claims.entrySet()) {
            transformedClaims.put(entry.getKey(), entry.getValue().as(Object.class));
        }

        return transformedClaims;
    }
}
