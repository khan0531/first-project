package com.beauty.api.model.reservation.service;

import com.beauty.api.model.reservation.domain.Reservation;
import com.beauty.api.model.reservation.dto.ReservationRequest;
import com.beauty.api.model.reservation.dto.ReservationResponse;
import com.beauty.api.model.reservation.dto.ReservationUpdateRequest;
import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.reservation.persist.repository.ReservationRepository;
import com.beauty.api.model.shop.domain.Shop;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.shop.persist.repository.ShopRepository;
import com.beauty.api.model.user.domain.Member;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import com.beauty.api.model.user.persist.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;

  private final MemberRepository memberRepository;

  private final ShopRepository shopRepository;

  public ReservationResponse reserve(ReservationRequest reservationRequest) {
    ShopEntity shopEntity = this.shopRepository.findById(reservationRequest.getShopId())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

    Shop shop = Shop.fromEntity(shopEntity);

    MemberEntity memberEntity = memberRepository.findById(reservationRequest.getMemberId())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

    Reservation reservation = Reservation.fromRequest(reservationRequest, memberEntity, shopEntity);

    shop.validate(reservation);
    ReservationEntity reservationEntity = this.reservationRepository.save(
        reservation.toEntity(shopEntity, memberEntity));

    return ReservationResponse.fromEntity(reservationEntity);
  }

  public ReservationResponse updateReservation(Member member, ReservationUpdateRequest reservationUpdateRequest) {
    Reservation reservation = this.reservationRepository.findById(reservationUpdateRequest.getId())
        .map(Reservation::fromEntity)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));
    ShopEntity shopEntity = this.shopRepository.findById(reservation.getShopId())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));
    Shop shop = Shop.fromEntity(shopEntity);
    MemberEntity memberEntity = memberRepository.getById(member.getId());

    if (!reservation.isWrittenBy(member)) {
      throw new IllegalArgumentException("예약자만 수정할 수 있습니다.");
    }

    reservation.update(reservationUpdateRequest);
    shop.validate(reservation);

    return ReservationResponse.fromEntity(
        this.reservationRepository.save(reservation.toEntity(shopEntity, memberEntity)));
  }

  public ReservationResponse getReservation(Long id) {
    ReservationEntity reservationEntity = this.reservationRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));

    return ReservationResponse.fromEntity(reservationEntity);
  }
}
