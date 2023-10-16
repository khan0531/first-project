package com.beauty.api.model.user.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
  @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다.")
  private String phone;

  private LocalDate birth;
}
