package com.beauty.api.model.user.dto;

import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

  private String email;

  private String name;

  @Email(message = "이메일 형식이 아닙니다.")
  private String updateEmail;

  private String phone;

  private LocalDate birth;

  public MemberEntity toEntity() {
    return MemberEntity.builder()
        .name(this.name)
        .email(this.updateEmail)
        .phone(this.phone)
        .birth(this.birth)
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
