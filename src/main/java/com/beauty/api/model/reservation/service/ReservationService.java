package com.beauty.api.model.reservation.service;

import com.beauty.api.model.reservation.dto.ReservationRequest;
import com.beauty.api.model.reservation.dto.ReservationResponse;
import com.beauty.api.model.reservation.dto.ReservationUpdateRequest;
import com.beauty.api.model.reservation.dto.constants.ReservationStatus;
import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.reservation.persist.repository.ReservationRepository;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.shop.persist.repository.ShopRepository;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import com.beauty.api.model.user.persist.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;

  private final MemberRepository memberRepository;

  private final ShopRepository shopRepository;

  public ReservationResponse reservation(ReservationRequest reservationRequest) {

    MemberEntity member = this.memberRepository.findById(reservationRequest.getMemberId())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 입니다."));

    List<ReservationEntity> reservationEntities = findReservationByMember(member);

    ShopEntity shop = this.shopRepository.findById(reservationRequest.getShopId())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

    if (reservationEntities.size() > 0) {
      for (ReservationEntity reservationEntity : reservationEntities) {
        if (reservationEntity.getStatus().equals(ReservationStatus.CONFIRMED)
            || reservationEntity.getStatus().equals(ReservationStatus.WAITING)) {
          throw new IllegalArgumentException("이미 예약된 시술이 존재 합니다. 예약 취소 후 다시 시도해주세요.");
        }
      }
    }

    if (!checkReservationTime(reservationRequest.getReservationTime(), shop)) {
      throw new IllegalArgumentException("선택하신 시간대는 예약이 불가능 합니다.");
    }

    ReservationEntity reservation = this.reservationRepository.save(reservationRequest.toEntity(member, shop));

    return ReservationResponse.fromEntity(reservation);
  }

  //유저 예약 찾기
  public List<ReservationEntity> findReservationByMember(MemberEntity member) {

    List<ReservationEntity> reservationEntities = this.reservationRepository.findByMember(member);

    return reservationEntities;
  }

  //예약 시간 체크
  public boolean checkReservationTime(LocalDateTime reservationTime, ShopEntity shop) {
    if (reservationTime.isBefore(shop.getOpenTime()) || reservationTime.isAfter(shop.getCloseTime())) {
      //throw new IllegalArgumentException("예약 가능 시간은 10:00 ~ 20:00 입니다.");
      return false;
    }

    // TODO: 시간 테이블을 따로 설정해서 막아두는걸로 해야하지 않을까
    List<ReservationEntity> reservationEntities = this.reservationRepository.findByReservationTime(reservationTime);
    for (ReservationEntity reservationEntity : reservationEntities) {
      if (reservationEntity.getStatus().equals(ReservationStatus.CONFIRMED)
          || reservationEntity.getStatus().equals(ReservationStatus.WAITING)) {
        //throw new IllegalArgumentException("선택하신 시간대는 예약이 불가능 합니다.");
        return false;
      }
    }
    return true;
  }

  public ReservationResponse updateReservation(ReservationUpdateRequest reservationUpdateRequest) {
    ReservationEntity reservationEntity = this.reservationRepository.findById(reservationUpdateRequest.getId())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));

    if (reservationEntity.getStatus().equals(ReservationStatus.WAITING)) {

      if (!checkReservationTime(reservationUpdateRequest.getReservationTime(), reservationEntity.getShop())) {
        throw new IllegalArgumentException("선택하신 시간대는 예약이 불가능 합니다.");
      }

      return ReservationResponse.fromEntity(this.reservationRepository.save(reservationUpdateRequest.toEntity()));
    }

    throw new IllegalArgumentException("예약이 없습니다. 혹은 예약이 취소되었거나, 확인이 완료된 예약은 취소 및 변경이 불가능 합니다. 매장에 문의해주세요.");
  }

  public ReservationResponse getReservation(Long id) {
    ReservationEntity reservationEntity = this.reservationRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));

    return ReservationResponse.fromEntity(reservationEntity);
  }
}
