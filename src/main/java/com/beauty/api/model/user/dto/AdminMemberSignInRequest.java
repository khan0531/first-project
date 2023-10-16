package com.beauty.api.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminMemberSignInRequest {

  private String email;
  private String password;
}
