package com.beauty.api.model.user.persist.entity;

import com.beauty.api.model.user.dto.constants.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "member")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "이름을 입력해주세요.")
  private String name;

  @Email(message = "이메일 형식이 아닙니다.")
  @Column(unique = true)
  private String email;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
  private String password;

  @NotBlank(message = "전화번호를 입력해주세요.")
  @Column(unique = true)
  private String phone;

  @NotBlank(message = "생년월일을 입력해주세요.")
  private LocalDate birth;

  @ElementCollection(fetch = FetchType.LAZY)
  @Enumerated(EnumType.STRING)
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
}
