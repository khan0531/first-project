package com.beauty.api.model.Inquiry.persist.entity;

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

@Entity(name = "inquiry")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InquiryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String content;

  private String image;

  @ManyToOne
  private MemberEntity member;

  @ManyToOne
  private ShopEntity shop;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
