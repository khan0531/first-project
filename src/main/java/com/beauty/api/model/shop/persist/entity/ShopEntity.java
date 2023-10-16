package com.beauty.api.model.shop.persist.entity;

import com.beauty.api.model.reservation.persist.entity.ReservationEntity;
import com.beauty.api.model.shop.dto.constants.CosmeticType;
import com.beauty.api.model.user.persist.entity.AdminMemberEntity;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity(name = "shop")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  private AdminMemberEntity adminMember;

  private String address;

  private double latitude;

  private double longitude;

  @ElementCollection(fetch = FetchType.LAZY)
  @Enumerated(EnumType.STRING)
  private Set<CosmeticType> cosmeticTypes;

  private String phone;

  @Column(name = "open_time", columnDefinition = "TIME")
  private LocalTime openTime;

  @Column(name = "close_time", columnDefinition = "TIME")
  private LocalTime closeTime;

  private String description;

  private Long ratingSum;

  private Long reviewCount;

  @OneToMany(mappedBy = "shop")
  @Where(clause = "status IN ('WAITING', 'CONFIRMED') AND reservation_time > NOW()")
  private List<ReservationEntity> reservations;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
