package com.beauty.api.security;

import com.beauty.api.model.user.dto.constants.Authority;
import com.beauty.api.model.user.service.AdminMemberService;
import com.beauty.api.model.user.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TokenProvider {

  private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60; // 1 hour
  private static final String KEY_ROLES = "roles";

  private final MemberService memberService;

  private final AdminMemberService adminMemberService;

  @Value("${jwt.secret}")
  private String secretKey;

  public String generateToken(String username, List<String> roles) {
    // 다음 정보들을 포함한 claims 생성
    //      - username
    //      - roles
    //      - 생성 시간
    //      - 만료 시간
    //      - signature
    Claims claims = Jwts.claims().setSubject(username);
    claims.put(KEY_ROLES, roles.get(0));

    var now = new Date();
    var expireDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME); //토큰 생성 후 한시간 유효하다.

    // jwt 발급
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expireDate)
        .signWith(SignatureAlgorithm.HS512, this.secretKey) // 사용할 암호화 알고리즘, 비밀키
        .compact();
  }

  @Transactional
  public Authentication getAuthentication(String jwt) {
    Jws<Claims> claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(jwt);
    String role = claims.getBody().get("roles", String.class);
    UserDetails userDetails;
    if (role.equals(Authority.ROLE_ADMIN.name())) {
      userDetails = this.adminMemberService.loadUserByUsername(this.getUsername(jwt));
    } else {
      userDetails = this.memberService.loadUserByUsername(this.getUsername(jwt));
    }
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);

      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

}
