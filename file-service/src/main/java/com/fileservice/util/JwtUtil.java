package com.fileservice.util;

import com.ziraitakip.backend.dto.UserConfirmDto;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final String secret_key = "L4B.F4R.PH4RM.L1F3";
    private long accessTokenValidity = 60 * 60 * 1000; // toke suresi 1 saat

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    private Date parseJwtClaimsExpirationDate(String token) {
        return jwtParser.parseClaimsJws(token).getBody().getExpiration();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public UserConfirmDto resolveTokenForActivation(String token) {
        Claims claims = parseJwtClaims(token);
        String activationCode = claims.get("activation_code").toString();
        Integer userId = Integer.parseInt(claims.get("id").toString());
        return new UserConfirmDto(userId, activationCode, token);
    }

    public String getMail(String token) {
        Claims claims = parseJwtClaims(token);
        return claims.get("email").toString();
    }

    public boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = parseJwtClaimsExpirationDate(token);
        return expiration.before(new Date());
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private Integer getRoles(Claims claims) {
        return Integer.parseInt(claims.get("role_id").toString());
    }

}
