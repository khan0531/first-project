package com.beauty.api.model.review.persist.entity;

import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "review")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String content;

  private String image;

  private Long rating;

  @OneToOne
  private ReservationEntity reservation;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
