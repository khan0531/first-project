package com.beauty.api.model.shop.persist.entity;

import com.beauty.api.model.shop.dto.constants.CosmeticType;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  private String address;

  private double latitude;

  private double longitude;

  @ElementCollection(fetch = FetchType.LAZY)
  @Enumerated(EnumType.STRING)
  private Set<CosmeticType> cosmeticTypes;

  private String phone;

  private LocalDateTime openTime;

  private LocalDateTime closeTime;

  private String description;

  private Long ratingSum;

  private Long reviewCount;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
