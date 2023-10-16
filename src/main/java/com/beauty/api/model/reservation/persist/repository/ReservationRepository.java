package com.beauty.api.model.reservation.persist.repository;

import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

  List<ReservationEntity> findByMember(MemberEntity member);

  List<ReservationEntity> findByReservationTime(LocalDateTime reservationTime);

  Optional<ReservationEntity> findByShopIdAndReservationTime(Long shopId, LocalDateTime reservationTime);
}
