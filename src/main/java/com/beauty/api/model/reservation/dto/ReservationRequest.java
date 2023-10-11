package com.beauty.api.model.reservation.dto;

import com.beauty.api.model.reservation.dto.constants.ReservationStatus;
import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
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
public class ReservationRequest {

  private Long memberId;
  private Long shopId;
  private LocalDateTime reservationTime;
  private String contents;

  public ReservationEntity toEntity(MemberEntity member, ShopEntity shop) {
    return ReservationEntity.builder()
        .reservationTime(this.reservationTime)
        .contents(this.contents)
        .createdAt(LocalDateTime.now())
        .member(member)
        .shop(shop)
        .status(ReservationStatus.WAITING)
        .build();
  }
}
