package com.beauty.api.model.user.dto;

import java.time.LocalDate;
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

  private String phone;

  private LocalDate birth;
}
