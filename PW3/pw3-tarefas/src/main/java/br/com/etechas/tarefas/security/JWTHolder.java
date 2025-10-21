package br.com.etechas.tarefas.security;

import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.enums.RoleEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class JWTHolder {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Duration expiration;

    private Key signingKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("id", ((Usuario)userDetails).getId())
                .claim("role", ((Usuario)userDetails).getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration.toMillis()))
                .signWith(signingKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public UserDetails getAuthenticated(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(signingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        var role = claims.get("role", String.class);
        return Usuario.builder().id(claims.get("id", Long.class))
                .role(RoleEnum.valueOf(role))
                .username(claims.getSubject())
                .build();
    }
}