package com.beauty.api.model.reservation.controller;

import com.beauty.api.model.reservation.dto.ReservationRequest;
import com.beauty.api.model.reservation.dto.ReservationUpdateRequest;
import com.beauty.api.model.reservation.service.ReservationService;
import com.beauty.api.model.user.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

  //예약하기
  @PostMapping
  @PreAuthorize("hasRole('ROLE_MEMBER')")
  public ResponseEntity<?> reserve(@RequestBody ReservationRequest reservationRequest) {
    var result = this.reservationService.reserve(reservationRequest);
    return ResponseEntity.ok(result);
  }

  //예약 수정
  @PatchMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_MEMBER')")
  public ResponseEntity<?> updateReservation(@AuthenticationPrincipal Member member, @PathVariable Long id,
      @RequestBody ReservationUpdateRequest reservationUpdateRequest) {
    if (!reservationUpdateRequest.getId().equals(id)) {
      throw new RuntimeException("예약 정보가 일치하지 않습니다.");
    }
    var result = this.reservationService.updateReservation(member, reservationUpdateRequest);
    return ResponseEntity.ok(result);
  }

  //예약 상세 조회
  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
  public ResponseEntity<?> getReservation(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
    var result = this.reservationService.getReservation(userDetails, id);
    return ResponseEntity.ok(result);
  }
}
