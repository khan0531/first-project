package com.beauty.api.model.reservation.domain;

import com.beauty.api.model.reservation.dto.ReservationRequest;
import com.beauty.api.model.reservation.dto.constants.ReservationStatus;
import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.shop.domain.Shop;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.user.domain.Member;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDateTime;
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

  public static Reservation fromRequest(ReservationRequest reservationRequest, MemberEntity memberEntity,
      ShopEntity shopEntity) {
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

  public ReservationEntity toEntity() {
    return ReservationEntity.builder()
        .reservationTime(this.reservationTime)
        .contents(this.contents)
        .member(this.member.toEntity())
        .shop(this.shop.toEntity())
        .status(this.status)
        .createdAt(this.createdAt)
        .updatedAt(this.updatedAt)
        .build();
  }
}
