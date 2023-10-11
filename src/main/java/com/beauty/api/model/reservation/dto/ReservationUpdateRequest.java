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
public class ReservationUpdateRequest {

  private Long id;
  private LocalDateTime reservationTime;
  private String contents;
  private ReservationStatus status;

  public ReservationEntity toEntity() {
    return ReservationEntity.builder()
        .reservationTime(this.reservationTime)
        .contents(this.contents)
        .status(this.status)
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
