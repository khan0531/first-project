package com.beauty.api.model.shop.dto;

import com.beauty.api.model.shop.persist.entity.ShopEntity;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponse {

  private String name;
  private String address;
  private String phone;
  private LocalTime openTime;
  private LocalTime closeTime;
  private String description;
  private Double rating;
  private Long reviewCount;

  public static ShopResponse fromEntity(ShopEntity shopEntity) {
    return ShopResponse.builder()
        .name(shopEntity.getName())
        .address(shopEntity.getAddress())
        .phone(shopEntity.getPhone())
        .openTime(shopEntity.getOpenTime())
        .closeTime(shopEntity.getCloseTime())
        .description(shopEntity.getDescription())
        .rating(0.0) // 소수점 둘째 자리에서 반올림
        .reviewCount(shopEntity.getReviewCount())
        .build();
  }
}
