package com.beauty.api.model.reservation.controller;

import com.beauty.api.model.reservation.dto.ReservationRequest;
import com.beauty.api.model.reservation.dto.ReservationUpdateRequest;
import com.beauty.api.model.reservation.service.ReservationService;
import com.beauty.api.model.review.dto.ReviewInput;
import com.beauty.api.model.review.dto.ReviewResponse;
import com.beauty.api.model.review.service.ReviewService;
import com.beauty.api.model.user.dto.Member;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

  private final ReservationService reservationService;

  private final ReviewService reviewService;

  //예약하기
  @PostMapping
  public ResponseEntity<?> reservation(@AuthenticationPrincipal MemberEntity memberEntity,
      @RequestBody ReservationRequest reservationRequest) {
    var result = this.reservationService.reservation(reservationRequest);
    return ResponseEntity.ok(result);
  }

  //예약 수정
  @PatchMapping("/{id}")
  public ResponseEntity<?> updateReservation(@AuthenticationPrincipal MemberEntity memberEntity, @PathVariable Long id,
      @RequestBody ReservationUpdateRequest reservationUpdateRequest) {
    var result = this.reservationService.updateReservation(reservationUpdateRequest);
    return ResponseEntity.ok(result);
  }

  //예약 상세 조회
  @GetMapping("/{id}")
  public ResponseEntity<?> getReservation(@PathVariable Long id) {
    var result = this.reservationService.getReservation(id);
    return ResponseEntity.ok(result);
  }

  //내 예약에 대한 리뷰 작성
  @PostMapping("/{id}/review")
  public ResponseEntity<?> writeReview(@AuthenticationPrincipal Member member, @PathVariable Long id,
      @RequestBody ReviewInput reviewInput) {

    if (reviewInput.getReservationId() != id) {
      throw new RuntimeException("예약 정보가 일치하지 않습니다.");
    }

    ReviewResponse reviewResponse = this.reviewService.writeReview(reviewInput);
    return ResponseEntity.ok(reviewResponse);
  }


}
