package com.beauty.api.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminMemberUpdateRequest {

  private Long id;

  private String phone;

}
