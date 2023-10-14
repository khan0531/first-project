package com.beauty.api.model.user.dto;

import com.beauty.api.model.user.dto.constants.Authority;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

  @NotBlank(message = "이름을 입력해주세요.")
  private String name;

  @Email(message = "이메일 형식이 아닙니다.")
  private String email;

  @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
  private String password;

  @NotBlank(message = "전화번호를 입력해주세요.")
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
