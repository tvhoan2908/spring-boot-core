package vn.spring.rnd.Auth.Config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import vn.spring.rnd.Auth.Constant.AuthConstant;

@Component
public class JwtTokenProvider {
  @Value("${external.app.secret_key}")
  private String secretKey;

  private final long expiredTime = AuthConstant.EXPIRATION_TIME;
  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  private SecretKey getSigninKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String createToken(String username) {
    Date now = new Date();
    Date validityDate = new Date(now.getTime() + expiredTime);

    return Jwts.builder()
        .subject(username)
        .issuedAt(now)
        .expiration(validityDate)
        .signWith(getSigninKey())
        .compact();
  }

  public String getUsernameFromJwtToken(String token) {
    return Jwts.parser()
        .verifyWith(getSigninKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  public Date getIssuedAtFromToken(String token) {
    return Jwts.parser()
        .verifyWith(getSigninKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getIssuedAt();
  }

  public String parseToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }

    return null;
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser()
          .verifyWith(getSigninKey())
          .build()
          .parseSignedClaims(token);

      return true;
    } catch (JwtException e) {
      logger.error("ERROR validateToken", e);
      return false;
    }
  }
}
