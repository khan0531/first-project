package com.beauty.api.model.reservation.domain;

import com.beauty.api.model.reservation.dto.ReservationRequest;
import com.beauty.api.model.reservation.dto.ReservationUpdateRequest;
import com.beauty.api.model.reservation.dto.constants.ReservationStatus;
import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.shop.domain.Shop;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.user.domain.Member;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

  private Long id;
  private LocalDateTime reservationTime;
  private String contents;
  private Member member;
  private Shop shop;
  private ReservationStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Long getShopId() {
    return Optional.ofNullable(shop).map(Shop::getId).orElse(null);
  }

  public boolean isWrittenBy(Member member) {
    return this.member.getId().equals(member.getId());
  }

  public static Reservation fromRequest(ReservationRequest reservationRequest, MemberEntity memberEntity,
      ShopEntity shopEntity) {
    if (reservationRequest.getReservationTime() == null) {
      throw new IllegalArgumentException("예약 시간은 필수입니다.");
    }
    return Reservation.builder()
        .reservationTime(reservationRequest.getReservationTime())
        .contents(reservationRequest.getContents())
        .member(Member.fromEntity(memberEntity))
        .shop(Shop.fromEntity(shopEntity))
        .status(ReservationStatus.WAITING)
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static Reservation fromEntity(ReservationEntity reservationEntity) {
    return Reservation.builder()
        .id(reservationEntity.getId())
        .reservationTime(reservationEntity.getReservationTime())
        .contents(reservationEntity.getContents())
        .member(Member.fromEntity(reservationEntity.getMember()))
        .shop(Shop.fromEntity(reservationEntity.getShop()))
        .status(reservationEntity.getStatus())
        .createdAt(reservationEntity.getCreatedAt())
        .updatedAt(reservationEntity.getUpdatedAt())
        .build();
  }

  public ReservationEntity toEntity(ShopEntity shopEntity, MemberEntity memberEntity) {
    return ReservationEntity.builder()
        .reservationTime(this.reservationTime)
        .contents(this.contents)
        .member(memberEntity)
        .shop(shopEntity)
        .status(this.status)
        .createdAt(this.createdAt)
        .updatedAt(this.updatedAt)
        .build();
  }

  public void update(ReservationUpdateRequest request) {
    if (this.status != ReservationStatus.WAITING) {
      throw new IllegalArgumentException("대기 상태의 예약만 수정 가능합니다.");
    }

    if (request.getStatus() != null) {
      if (request.getStatus() != ReservationStatus.WAITING) {
        throw new IllegalArgumentException("예약 상태는 대기 상태만 가능합니다.");
      }

      this.status = request.getStatus();
    }
    if (request.getReservationTime() != null) {
      this.reservationTime = request.getReservationTime();
    }
    if (request.getContents() != null) {
      this.contents = request.getContents();
    }
    this.updatedAt = LocalDateTime.now();
  }
}
