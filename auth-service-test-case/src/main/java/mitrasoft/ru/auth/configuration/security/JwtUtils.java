package mitrasoft.ru.auth.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import mitrasoft.ru.auth.configuration.TokenProperties;
import mitrasoft.ru.auth.exception.http.BadRequestException;
import mitrasoft.ru.auth.model.dto.auth.TokenInfoDto;
import mitrasoft.ru.auth.model.dto.auth.TokenPayloadDto;
import mitrasoft.ru.auth.model.dto.auth.UserInfoDto;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final TokenProperties tokenProperties;


    private Claims getClaimsFromToken(String authToken) {
        String key = Base64.getEncoder().encodeToString(tokenProperties.getSecret().getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(authToken)
                .getBody();
    }

    public TokenInfoDto validateToken(String authToken) {
        try {
            Claims claims = getClaimsFromToken(authToken);

            return new TokenInfoDto(
                new UserInfoDto(claims.getSubject(), claims.get("role", List.class)),
                getClaimsFromToken(authToken)
                    .getExpiration()
                    .after(new Date())
            );
        } catch (SignatureException e) {
            throw new BadRequestException("Not valid token");
        }
    }

    public TokenPayloadDto generateToken(String username, String role) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", List.of(role));


        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + tokenProperties.getExpiration() * 1000);
        String token = Jwts.builder()
                .setClaims(claims)
                .setHeader(header)
                .setSubject(username)
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(tokenProperties.getSecret().getBytes()))
                .compact();

        return new TokenPayloadDto(token, tokenProperties.getType(), expirationDate.getTime());
    }
}
