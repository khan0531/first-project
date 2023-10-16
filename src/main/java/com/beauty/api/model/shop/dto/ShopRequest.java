package com.beauty.api.model.shop.dto;

import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopRequest {

  private String name;

  private Long AdminMemberId;

  private String address;

  @NotBlank(message = "전화번호를 입력해주세요.")
  @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다.")
  private String phone;

  private LocalTime openTime;

  private LocalTime closeTime;

  private String description;
}
