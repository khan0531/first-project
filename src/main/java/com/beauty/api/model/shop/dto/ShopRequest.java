package com.beauty.api.model.shop.dto;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopRequest {

  private String name;
  private Long AdminMemberId;
  private String address;
  private String phone;
  private LocalTime openTime;
  private LocalTime closeTime;
  private String description;
}
