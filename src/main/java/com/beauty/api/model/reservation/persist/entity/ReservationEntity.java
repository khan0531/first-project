package com.beauty.api.model.reservation.persist.entity;

import com.beauty.api.model.reservation.dto.constants.ReservationStatus;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "reservation")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime reservationTime;

  private String content;

  @ManyToOne
  private MemberEntity member;

  @ManyToOne
  private ShopEntity shop;

  private ReservationStatus status;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

}
