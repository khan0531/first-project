package com.beauty.api.model.reservation.controller;

import com.beauty.api.model.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

  private final ReservationService reservationService;

  //예약하기
  @PostMapping
  public ResponseEntity<?> reservation() {
    return null;
  }

  //예약 수정
  @PatchMapping("/{id}")
  public ResponseEntity<?> updateReservation(@PathVariable String id) {
    return null;
  }

  //예약 취소
  @PatchMapping("/{id}/cancel")
  public ResponseEntity<?> cancelReservation(@PathVariable String id) {
    return null;
  }

  //예약 상세 조회
  @GetMapping("/{id}")
  public ResponseEntity<?> getReservation(@PathVariable String id) {
    return null;
  }

  //내 예약에 대한 리뷰 작성
  @PostMapping("/{id}/review")
  public ResponseEntity<?> writeReview(@PathVariable String id) {
    return null;
  }


}
