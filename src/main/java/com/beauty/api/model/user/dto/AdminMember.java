package com.beauty.api.model.user.dto;

import com.beauty.api.model.user.dto.constants.Authority;
import com.beauty.api.model.user.persist.entity.AdminMemberEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminMember implements UserDetails {

  private String email;
  private List<Authority> roles;
  private String password;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream()
        .map(Enum::name)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public static AdminMember fromEntity(AdminMemberEntity adminMemberEntity) {
    return AdminMember.builder()
        .email(adminMemberEntity.getEmail())
        .roles(adminMemberEntity.getRoles())
        .password(adminMemberEntity.getPassword())
        .build();
  }
}
