package com.np.bank.management.utilities;

import com.np.bank.management.entities.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtility {

    private final String secret = "tests231523634yerknmgwmk4tio5u3tf4u83478y46fy34ui6fyu54u";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


    public String generateJwtToken(Users user) {
        return Jwts.builder()
                .signWith(secretKey)
                .issuer("NP")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+300000000))
                .subject(user.getUserName())
                .claim("Role",user.getRoles().getRoleName())
                .compact();
    }

    public boolean validateToken(String jwt){
        try {
           return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload()
                    .getExpiration().after(new Date());
        }catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String jwt) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();
    }
}
