package com.beauty.api.model.user.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequest {

  private Long id;

  @Email(message = "이메일 형식이 아닙니다.")
  private String email;

  private String phone;

  private LocalDate birth;
}
