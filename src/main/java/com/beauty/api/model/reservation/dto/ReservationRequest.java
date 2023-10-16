package com.beauty.api.model.reservation.dto;

import com.beauty.api.model.reservation.dto.constants.ReservationStatus;
import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

  @NotNull(message = "회원 아이디를 입력해주세요.")
  private Long memberId;

  @NotNull(message = "가게 아이디를 입력해주세요.")
  private Long shopId;

  @NotBlank(message = "예약 시간을 입력해주세요.")
  private LocalDateTime reservationTime;

  @NotBlank(message = "예약 내용을 입력해주세요.")
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
