package com.itransition.backend.authencation.jwt;

import com.itransition.backend.authencation.TokenType;
import com.itransition.backend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final String TOKEN_TYPE = "tokenType";
    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";
    private final Key key = Keys.hmacShaKeyFor(UUID.randomUUID().toString().getBytes());

    private final Integer expirationMinuteInMs;
    private final Long refreshExpirationDateInMs;

    public JwtTokenUtil(@Value("${jwt.expiration.minute.in.ms}") Integer expirationMinuteInMs,
                        @Value("${jwt.refresh.expiration.date.in.ms}") Long refreshExpirationDateInMs) {
        this.expirationMinuteInMs = expirationMinuteInMs;
        this.refreshExpirationDateInMs = refreshExpirationDateInMs;
    }

    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        final var claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User user) {
        return generateJwtToken(expirationMinuteInMs, user, TokenType.ACCESS.getType());
    }

    public String generateRefreshToken(User user) {
        return generateJwtToken(refreshExpirationDateInMs, user, TokenType.REFRESH.getType());
    }

    public boolean validateToken(final String token, final UserDetails userDetails) {
        final String username = extractUsername(token);
        return (Objects.equals(username, userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isRefreshToken(final String token) {
        String tokenType = extractClaim(token, claims -> claims.get(TOKEN_TYPE, String.class));
        return Objects.equals(tokenType, TokenType.REFRESH.getType());
    }

    private String generateJwtToken(final long expiration, final User user, final String tokenType) {
        long current = System.currentTimeMillis();
        return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE).setSubject(user.getName())
                .claim(USER_ID, user.getId()).claim(USER_ROLE, user.getRole().getAuthority()).claim(TOKEN_TYPE, tokenType)
                .setIssuedAt(new Date(current)).setExpiration(new Date(current + expiration))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    private Claims extractAllClaims(final String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

}
