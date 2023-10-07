package com.beauty.api.model.user.dto;

import com.beauty.api.model.user.dto.constants.Authority;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
  private String name;
  private String email;
  private String password;
  private String phone;
  private LocalDate birth;

  public MemberEntity toEntity() {
    return MemberEntity.builder()
      .name(this.name)
      .email(this.email)
      .password(this.password)
      .phone(this.phone)
      .birth(this.birth)
      .roles(Collections.singletonList(Authority.ROLE_USER))
      .createdAt(LocalDateTime.now())
      .build();
  }
}
