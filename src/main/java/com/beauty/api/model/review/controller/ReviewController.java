package com.beauty.api.model.review.controller;

import com.beauty.api.model.review.dto.ReviewResponse;
import com.beauty.api.model.review.dto.ReviewUpdateRequest;
import com.beauty.api.model.review.service.ReviewService;
import com.beauty.api.model.user.domain.Member;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

  private final ReviewService reviewService;

  //리뷰 상세 조회
  @GetMapping("/{id}")
  public ResponseEntity<?> getReview(@PathVariable Long id) {
    var result = this.reviewService.getReview(id);
    return ResponseEntity.ok(result);
  }

  //내 예약에 대한 리뷰 수정
  @PatchMapping("/review/{reviewId}")
  public ResponseEntity<?> updateReview(@AuthenticationPrincipal Member member,
      @PathVariable Long reviewId, @RequestBody ReviewUpdateRequest reviewUpdateRequest) {

    if (!Objects.equals(reviewId, reviewUpdateRequest.getId())) {
      return ResponseEntity.badRequest().build();
    }

    ReviewResponse reviewResponse = this.reviewService.updateReview(member, reviewUpdateRequest);
    return ResponseEntity.ok(reviewResponse);
  }
}
