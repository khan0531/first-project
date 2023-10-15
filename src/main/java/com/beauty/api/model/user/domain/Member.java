package com.beauty.api.model.user.domain;

import com.beauty.api.model.user.dto.MemberSignUpRequest;
import com.beauty.api.model.user.dto.MemberUpdatePassword;
import com.beauty.api.model.user.dto.MemberUpdateRequest;
import com.beauty.api.model.user.dto.constants.Authority;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
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
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements UserDetails {

  private Long id;
  private String name;
  private String email;
  private String password;
  private String phone;
  private LocalDate birth;
  private List<Authority> roles;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private PasswordEncoder passwordEncoder;

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

  public static Member fromRequest(MemberSignUpRequest memberSignUpRequest) {
    return Member.builder()
        .name(memberSignUpRequest.getName())
        .email(memberSignUpRequest.getEmail())
        .password(memberSignUpRequest.getPassword())
        .phone(memberSignUpRequest.getPhone())
        .birth(memberSignUpRequest.getBirth())
        .roles(Collections.singletonList(Authority.ROLE_USER))
        .createdAt(LocalDateTime.now())
        .build();
  }


  public static Member fromEntity(MemberEntity memberEntity) {
    return Member.builder()
        .id(memberEntity.getId())
        .name(memberEntity.getName())
        .email(memberEntity.getEmail())
        .password(memberEntity.getPassword())
        .phone(memberEntity.getPhone())
        .birth(memberEntity.getBirth())
        .roles(memberEntity.getRoles())
        .createdAt(memberEntity.getCreatedAt())
        .updatedAt(memberEntity.getUpdatedAt())
        .build();
  }

  public MemberEntity toEntity() {
    return MemberEntity.builder()
        .name(this.name)
        .email(this.email)
        .password(this.password)
        .phone(this.phone)
        .birth(this.birth)
        .roles(this.roles)
        .createdAt(this.createdAt)
        .updatedAt(this.updatedAt)
        .build();
  }

  public Member update(MemberUpdateRequest memberUpdateRequest) {
    if (memberUpdateRequest.getPhone() != null) {
      this.phone = memberUpdateRequest.getPhone();
    }

    if (memberUpdateRequest.getBirth() != null) {
      this.birth = memberUpdateRequest.getBirth();
    }

    this.updatedAt = LocalDateTime.now();

    return this;
  }

  public Member updatePassword(MemberUpdatePassword memberUpdatePassword) {
    this.password = this.passwordEncoder.encode(memberUpdatePassword.getNewPassword());
    this.updatedAt = LocalDateTime.now();

    return this;
  }
}
