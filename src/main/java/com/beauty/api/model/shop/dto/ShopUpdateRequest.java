package com.beauty.api.model.shop.dto;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopUpdateRequest {

  private Long id;
  private String name;
  private String address;
  private String phone;
  private String description;
  private LocalTime openTime;
  private LocalTime closeTime;
}
