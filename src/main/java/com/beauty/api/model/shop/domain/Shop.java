package com.beauty.api.model.shop.domain;

import com.beauty.api.model.reservation.domain.Reservation;
import com.beauty.api.model.shop.dto.ShopRequest;
import com.beauty.api.model.shop.dto.ShopUpdateRequest;
import com.beauty.api.model.shop.dto.constants.CosmeticType;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.user.domain.AdminMember;
import com.beauty.api.model.user.persist.entity.AdminMemberEntity;
import com.beauty.api.utilities.geocoding.dto.LatLng;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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
  private LocalTime openTime;
  private LocalTime closeTime;
  private String description;
  private Long ratingSum;
  private Long reviewCount;
  private List<Reservation> reservations;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public void setLatLng(LatLng latLng) {
    this.latitude = latLng.getLat();
    this.longitude = latLng.getLng();
  }

  public static Shop fromRequest(ShopRequest shopRequest, AdminMemberEntity adminMemberEntity) {
    return Shop.builder()
        .name(shopRequest.getName())
        .adminMember(AdminMember.fromEntity(adminMemberEntity))
        .address(shopRequest.getAddress())
        .phone(shopRequest.getPhone())
        .openTime(shopRequest.getOpenTime())
        .closeTime(shopRequest.getCloseTime())
        .description(shopRequest.getDescription())
        .build();
  }

  public void validate(Reservation reservation) {
    if (reservation.getReservationTime().toLocalTime().isBefore(openTime)
        || reservation.getReservationTime().toLocalTime().isAfter(closeTime)) {
      throw new IllegalArgumentException("영업시간이 아닙니다.");
    }

    if (reservations.stream().anyMatch(r -> r.getReservationTime().equals(reservation.getReservationTime()))) {
      throw new IllegalArgumentException("이미 예약된 시간입니다.");
    }
  }

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
        .reservations(shopEntity.getReservations().stream()
            .map(Reservation::fromEntity)
            .toList())
        .createdAt(shopEntity.getCreatedAt())
        .updatedAt(shopEntity.getUpdatedAt())
        .build();
  }

  public ShopEntity toEntity(AdminMemberEntity adminMemberEntity) {
    return ShopEntity.builder()
        .name(this.name)
        .adminMember(adminMemberEntity)
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

  public Shop updateShop(ShopUpdateRequest shopUpdateRequest) {
    if (shopUpdateRequest.getName() != null) {
      this.name = shopUpdateRequest.getName();
    }

    if (shopUpdateRequest.getAddress() != null) {
      this.address = shopUpdateRequest.getAddress();
    }

    if (shopUpdateRequest.getPhone() != null) {
      this.phone = shopUpdateRequest.getPhone();
    }

    if (shopUpdateRequest.getOpenTime() != null) {
      this.openTime = shopUpdateRequest.getOpenTime();
    }

    if (shopUpdateRequest.getCloseTime() != null) {
      this.closeTime = shopUpdateRequest.getCloseTime();
    }

    if (shopUpdateRequest.getDescription() != null) {
      this.description = shopUpdateRequest.getDescription();
    }

    this.updatedAt = LocalDateTime.now();

    return this;
  }
}
