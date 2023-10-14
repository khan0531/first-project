package com.beauty.api.model.shop.domain;

import com.beauty.api.model.shop.dto.constants.CosmeticType;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.user.domain.AdminMember;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

  private Long id;
  private String name;
  private AdminMember adminMember;
  private String address;
  private double latitude;
  private double longitude;
  private Set<CosmeticType> cosmeticTypes;
  private String phone;
  private LocalDateTime openTime;
  private LocalDateTime closeTime;
  private String description;
  private Long ratingSum;
  private Long reviewCount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static Shop fromEntity(ShopEntity shopEntity) {
    return Shop.builder()
        .id(shopEntity.getId())
        .name(shopEntity.getName())
        .adminMember(AdminMember.fromEntity(shopEntity.getAdminMember()))
        .address(shopEntity.getAddress())
        .latitude(shopEntity.getLatitude())
        .longitude(shopEntity.getLongitude())
        .cosmeticTypes(shopEntity.getCosmeticTypes())
        .phone(shopEntity.getPhone())
        .openTime(shopEntity.getOpenTime())
        .closeTime(shopEntity.getCloseTime())
        .description(shopEntity.getDescription())
        .ratingSum(shopEntity.getRatingSum())
        .reviewCount(shopEntity.getReviewCount())
        .createdAt(shopEntity.getCreatedAt())
        .updatedAt(shopEntity.getUpdatedAt())
        .build();
  }

  public ShopEntity toEntity() {
    return ShopEntity.builder()
        .name(this.name)
        .adminMember(this.adminMember.toEntity())
        .address(this.address)
        .latitude(this.latitude)
        .longitude(this.longitude)
        .cosmeticTypes(this.cosmeticTypes)
        .phone(this.phone)
        .openTime(this.openTime)
        .closeTime(this.closeTime)
        .description(this.description)
        .ratingSum(this.ratingSum)
        .reviewCount(this.reviewCount)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
