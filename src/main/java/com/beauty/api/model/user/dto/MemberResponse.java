package com.beauty.api.model.user.dto;

import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
  private String name;
  private String email;
  private String phone;
  private LocalDate birth;

  public static MemberResponse fromEntity(MemberEntity memberEntity) {
    return MemberResponse.builder()
      .name(memberEntity.getName())
      .email(memberEntity.getEmail())
      .phone(memberEntity.getPhone())
      .birth(memberEntity.getBirth())
      .build();
  }
}
