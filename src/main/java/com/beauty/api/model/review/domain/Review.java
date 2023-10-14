package com.beauty.api.model.review.domain;

import com.beauty.api.model.reservation.domain.Reservation;
import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.review.dto.ReviewRequest;
import com.beauty.api.model.review.dto.ReviewUpdateRequest;
import com.beauty.api.model.review.persist.entity.ReviewEntity;
import com.beauty.api.model.user.domain.Member;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

  private Long id;
  private String title;
  private String content;
  private String image;
  private Long rating;
  private Reservation reservation;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public boolean isWrittenBy(Member member) {
    return this.reservation.getMember().getId().equals(member.getId());
  }

  public static Review fromRequest(ReviewRequest reviewRequest, ReservationEntity reservationEntity) {
    return Review.builder()
        .title(reviewRequest.getTitle())
        .content(reviewRequest.getContent())
        .image(reviewRequest.getImage())
        .rating(reviewRequest.getRating())
        .reservation(Reservation.fromEntity(reservationEntity))
        .createdAt(LocalDateTime.now())
        .build();
  }

  public ReviewEntity toEntity() {
    return ReviewEntity.builder()
        .title(this.title)
        .content(this.content)
        .image(this.image)
        .rating(this.rating)
        .reservation(this.reservation.toEntity())
        .createdAt(this.createdAt)
        .updatedAt(this.updatedAt)
        .build();
  }

  public static Review fromEntity(ReviewEntity reviewEntity) {
    return Review.builder()
        .id(reviewEntity.getId())
        .title(reviewEntity.getTitle())
        .content(reviewEntity.getContent())
        .image(reviewEntity.getImage())
        .rating(reviewEntity.getRating())
        .reservation(Reservation.fromEntity(reviewEntity.getReservation()))
        .createdAt(reviewEntity.getCreatedAt())
        .updatedAt(reviewEntity.getUpdatedAt())
        .build();
  }

  public Review update(ReviewUpdateRequest reviewUpdateRequest) {
    if (reviewUpdateRequest.getTitle() != null) {
      this.title = reviewUpdateRequest.getTitle();
    }

    if (reviewUpdateRequest.getContent() != null) {
      this.content = reviewUpdateRequest.getContent();
    }

    if (reviewUpdateRequest.getImage() != null) {
      this.image = reviewUpdateRequest.getImage();
    }

    if (reviewUpdateRequest.getRating() != null) {
      this.rating = reviewUpdateRequest.getRating();
    }

    this.updatedAt = LocalDateTime.now();

    return this;
  }
}
