package com.beauty.api.model.reservation.persist.repository;

import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

}
