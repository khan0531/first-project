package com.beauty.api.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberFindEmail {

  private String name;
  private String phone;
}
