package com.beauty.api.model.user.dto;

import com.beauty.api.model.user.dto.constants.Authority;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpRequest {

  private String name;

  private String email;

  @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
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
