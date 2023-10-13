package com.beauty.api.model.review.service;

import com.beauty.api.model.review.dto.ReviewInput;
import com.beauty.api.model.review.dto.ReviewResponse;
import com.beauty.api.model.review.persist.entity.ReviewEntity;
import com.beauty.api.model.review.persist.repository.ReviewRepository;
import java.time.LocalDateTime;
import java.util.List;
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

  public ReviewResponse writeReview(ReviewInput reviewInput) {
    List<ReviewEntity> reviewEntities = this.reviewRepository.findByTitleAndContent(reviewInput.getTitle(),
        reviewInput.getContent());

    if (reviewEntities.size() > 0) {
      for (ReviewEntity reviewEntity : reviewEntities) {
        if (reviewEntity.getCreatedAt().isAfter(LocalDateTime.now().minusSeconds(30))) {
          throw new RuntimeException("30초 이내에 같은 제목, 같은 내용의 리뷰를 작성할 수 없습니다.");
        }
      }
    }

    ReviewEntity reviewEntity = this.reviewRepository.save(reviewInput.toEntity());

    return ReviewResponse.fromEntity(reviewEntity);
  }
}
