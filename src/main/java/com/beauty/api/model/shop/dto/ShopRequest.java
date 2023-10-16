package com.beauty.api.model.shop.dto;

import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopRequest {

  @NotBlank(message = "이름을 입력해주세요.")
  private String name;

  @NotBlank(message = "관리자 아이디를 입력해주세요.")
  private Long AdminMemberId;

  @NotBlank(message = "주소를 입력해주세요.")
  private String address;

  @NotBlank(message = "전화번호를 입력해주세요.")
  @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다.")
  private String phone;

  @NotBlank(message = "오픈 시간을 입력해주세요.")
  private LocalTime openTime;

  @NotBlank(message = "마감 시간을 입력해주세요.")
  private LocalTime closeTime;

  private String description;
}
