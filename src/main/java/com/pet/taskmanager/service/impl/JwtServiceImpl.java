package com.pet.taskmanager.service.impl;


import com.pet.taskmanager.entity.UserProfile;
import com.pet.taskmanager.exception.CustomException;
import com.pet.taskmanager.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Log4j2
public class JwtServiceImpl implements JwtService {

    private static final long JWT_VALID_TIME = 8 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserProfile up, Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(up.getSub())
                .claim("familyName", up.getFamilyName())
                .claim("givenName", up.getGivenName())
                .claim("email", up.getUsername())
                .claim("picture", up.getPicture())
                .claim("authority",
                        up.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()) // List of roles in string
                )
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_VALID_TIME))
                .signWith(
                        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))
                )
                .compact();
    }

    public Boolean validateToken(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new CustomException("AUTH-101: ExpiredJwtException.");
        } catch (SecurityException e) {
            log.error("AUTH-102: SecurityException. " + e.getMessage());
            throw new CustomException("AUTH-102: SecurityException.");
        } catch (IllegalArgumentException e) {
            log.error("AUTH-103: IllegalArgumentException. " + e.getMessage());
            throw new CustomException("AUTH-103: IllegalArgumentException.");
        } catch (MalformedJwtException e) {
            log.error("AUTH-104: MalformedJwtException. " + e.getMessage());
            throw new CustomException("AUTH-104: MalformedJwtException.");
        }

    }

    public UserProfile getUserDetails(String token) {

        try {

            Claims body = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            ArrayList<?> authority = new ArrayList<>((Collection<?>) body.get("authority"));

            List<GrantedAuthority> grantedAuthority = authority
                    .stream()
                    .map(o -> (String) o)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return new UserProfile(
                    (String) body.get("email"),
                    null,
                    grantedAuthority,
                    body.getSubject(),
                    (String) body.get("familyName"),
                    (String) body.get("givenName"),
                    (String) body.get("picture")
            );

        } catch (ExpiredJwtException e) {
            throw new CustomException("AUTH-101: ExpiredJwtException.");
        } catch (SecurityException e) {
            log.error("AUTH-102: SecurityException. " + e.getMessage());
            throw new CustomException("AUTH-102: SecurityException.");
        } catch (IllegalArgumentException e) {
            log.error("AUTH-103: IllegalArgumentException. " + e.getMessage());
            throw new CustomException("AUTH-103: IllegalArgumentException.");
        } catch (MalformedJwtException e) {
            log.error("AUTH-104: MalformedJwtException. " + e.getMessage());
            throw new CustomException("AUTH-104: MalformedJwtException.");
        }
    }

}
