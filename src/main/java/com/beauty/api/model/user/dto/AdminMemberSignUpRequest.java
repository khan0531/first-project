package com.beauty.api.model.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminMemberSignUpRequest {

  @NotBlank(message = "이름을 입력해주세요.")
  private String name;

  @Email(message = "이메일 형식이 아닙니다.")
  private String email;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  private String password;

  @NotBlank(message = "전화번호를 입력해주세요.")
  private String phone;
}
