package com.beauty.api.model.review.service;

import com.beauty.api.model.reservation.dto.constants.ReservationStatus;
import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.reservation.persist.repository.ReservationRepository;
import com.beauty.api.model.review.domain.Review;
import com.beauty.api.model.review.dto.ReviewRequest;
import com.beauty.api.model.review.dto.ReviewResponse;
import com.beauty.api.model.review.dto.ReviewUpdateRequest;
import com.beauty.api.model.review.persist.entity.ReviewEntity;
import com.beauty.api.model.review.persist.repository.ReviewRepository;
import com.beauty.api.model.user.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;

  private final ReservationRepository reservationRepository;

  public ReviewResponse getReview(Long id) {
    ReviewEntity reviewEntity = this.reviewRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));

    return ReviewResponse.fromEntity(reviewEntity);
  }

  public ReviewResponse writeReview(Member member, ReviewRequest reviewRequest) {
    ReservationEntity reservationEntity = this.reservationRepository.findById(reviewRequest.getReservationId())
        .orElseThrow(() -> new RuntimeException("예약 정보가 없습니다."));

    if (!reservationEntity.getStatus().equals(ReservationStatus.FINISHED)) {
      throw new RuntimeException("예약이 완료되지 않았습니다.");
    }

    if (!reservationEntity.getMember().getId().equals(member.getId())) {
      throw new RuntimeException("예약자만 리뷰를 작성할 수 있습니다.");
    }

    this.reviewRepository.findByReservation(reservationEntity)
        .ifPresent(reviewEntity -> {
          throw new RuntimeException("이미 리뷰를 작성하셨습니다.");
        });

    ReviewEntity reviewEntity = this.reviewRepository.save(
        Review.fromRequest(reviewRequest, reservationEntity).toEntity(reservationEntity));

    return ReviewResponse.fromEntity(reviewEntity);
  }

  public ReviewResponse updateReview(Member member, ReviewUpdateRequest reviewUpdateRequest) {
    ReviewEntity reviewEntity = this.reviewRepository.findById(reviewUpdateRequest.getId())
        .orElseThrow(() -> new RuntimeException("해당 리뷰가 존재하지 않습니다."));

    ReservationEntity reservationEntity = this.reservationRepository.findById(reviewEntity.getReservation().getId())
        .orElseThrow(() -> new RuntimeException("예약 정보가 없습니다."));

    Review review = Review.fromEntity(reviewEntity);

    if (!review.isWrittenBy(member)) {
      throw new RuntimeException("해당 리뷰를 수정할 권한이 없습니다.");
    }

    return ReviewResponse.fromEntity(
        this.reviewRepository.save(review.update(reviewUpdateRequest).toEntity(reservationEntity)));
  }
}
