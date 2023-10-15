package com.beauty.api.model.user.domain;

import com.beauty.api.model.user.dto.AdminMemberSignUpRequest;
import com.beauty.api.model.user.dto.AdminMemberUpdateRequest;
import com.beauty.api.model.user.dto.constants.Authority;
import com.beauty.api.model.user.persist.entity.AdminMemberEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
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

  private Long id;
  private String name;
  private String email;
  private String password;
  private String phone;
  private List<Authority> roles;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

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

  public static AdminMember fromRequest(AdminMemberSignUpRequest adminMemberSignUpRequest) {
    return AdminMember.builder()
        .name(adminMemberSignUpRequest.getName())
        .email(adminMemberSignUpRequest.getEmail())
        .password(adminMemberSignUpRequest.getPassword())
        .phone(adminMemberSignUpRequest.getPhone())
        .roles(Collections.singletonList(Authority.ROLE_USER))
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static AdminMember fromEntity(AdminMemberEntity adminMemberEntity) {
    return AdminMember.builder()
        .id(adminMemberEntity.getId())
        .name(adminMemberEntity.getName())
        .email(adminMemberEntity.getEmail())
        .password(adminMemberEntity.getPassword())
        .phone(adminMemberEntity.getPhone())
        .roles(adminMemberEntity.getRoles())
        .createdAt(adminMemberEntity.getCreatedAt())
        .updatedAt(adminMemberEntity.getUpdatedAt())
        .build();
  }

  public AdminMemberEntity toEntity() {
    return AdminMemberEntity.builder()
        .name(this.name)
        .email(this.email)
        .password(this.password)
        .phone(this.phone)
        .roles(this.roles)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }

  public AdminMember update(AdminMemberUpdateRequest adminMemberUpdateRequest) {

    if (adminMemberUpdateRequest.getPhone() != null) {
      this.phone = adminMemberUpdateRequest.getPhone();
    }

    this.updatedAt = LocalDateTime.now();

    return this;
  }
}
