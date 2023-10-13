package com.beauty.api.model.review.persist.repository;

import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.review.persist.entity.ReviewEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

  List<ReviewEntity> findByTitleAndContent(String title, String content);

  Optional<ReviewEntity> findByReservation(ReservationEntity reservationEntity);
}
