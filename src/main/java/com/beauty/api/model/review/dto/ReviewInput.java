package com.beauty.api.model.review.dto;

import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.review.persist.entity.ReviewEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInput {

  private Long reservationId;
  private String title;
  private String content;
  private String image;
  private Long rating;

  public ReviewEntity toEntity(ReservationEntity reservationEntity) {
    return ReviewEntity.builder()
        .title(this.title)
        .content(this.content)
        .image(this.image)
        .rating(this.rating)
        .reservation(reservationEntity)
        .createdAt(LocalDateTime.now())
        .build();
  }
}
