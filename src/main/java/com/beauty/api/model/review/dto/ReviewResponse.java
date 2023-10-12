package com.beauty.api.model.review.dto;

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
public class ReviewResponse {

  private String title;
  private String content;
  private String image;
  private Long rating;
  private Long reservationId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static ReviewResponse fromEntity(ReviewEntity reviewEntity) {
    return ReviewResponse.builder()
        .title(reviewEntity.getTitle())
        .content(reviewEntity.getContent())
        .image(reviewEntity.getImage())
        .rating(reviewEntity.getRating())
        .reservationId(reviewEntity.getReservation().getId())
        .createdAt(reviewEntity.getCreatedAt())
        .updatedAt(reviewEntity.getUpdatedAt())
        .build();
  }
}
