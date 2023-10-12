package com.beauty.api.model.review.service;

import com.beauty.api.model.review.dto.ReviewResponse;
import com.beauty.api.model.review.persist.entity.ReviewEntity;
import com.beauty.api.model.review.persist.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;

  public ReviewResponse getReview(Long id) {
    ReviewEntity reviewEntity = this.reviewRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));

    return ReviewResponse.fromEntity(reviewEntity);
  }
}
