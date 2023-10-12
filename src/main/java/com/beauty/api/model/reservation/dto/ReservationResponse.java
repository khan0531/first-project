package com.beauty.api.model.reservation.dto;

import com.beauty.api.model.reservation.dto.constants.ReservationStatus;
import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {

  private String name;
  private String shopName;
  private LocalDateTime reservationTime;
  private String contents;
  private ReservationStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static ReservationResponse fromEntity(ReservationEntity reservationEntity) {
    return ReservationResponse.builder()
        .name(reservationEntity.getMember().getName())
        .shopName(reservationEntity.getShop().getName())
        .reservationTime(reservationEntity.getReservationTime())
        .contents(reservationEntity.getContents())
        .status(reservationEntity.getStatus())
        .createdAt(reservationEntity.getCreatedAt())
        .updatedAt(reservationEntity.getUpdatedAt())
        .build();
  }
}
