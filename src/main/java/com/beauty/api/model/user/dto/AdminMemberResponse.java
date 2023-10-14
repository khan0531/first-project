package com.beauty.api.model.user.dto;

import com.beauty.api.model.user.persist.entity.AdminMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminMemberResponse {

  private String name;
  private String email;
  private String phone;

  public static AdminMemberResponse fromEntity(AdminMemberEntity adminMemberEntity) {
    return AdminMemberResponse.builder()
        .name(adminMemberEntity.getName())
        .email(adminMemberEntity.getEmail())
        .phone(adminMemberEntity.getPhone())
        .build();
  }
}
