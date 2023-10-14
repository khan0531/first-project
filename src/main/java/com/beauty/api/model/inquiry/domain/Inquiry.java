package com.beauty.api.model.inquiry.domain;

import com.beauty.api.model.inquiry.dto.InquiryRequest;
import com.beauty.api.model.inquiry.dto.InquiryUpdateRequest;
import com.beauty.api.model.inquiry.persist.entity.InquiryEntity;
import com.beauty.api.model.shop.domain.Shop;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.user.domain.Member;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry {

  private Long id;
  private String title;
  private String content;
  private String image;
  private Member member;
  private Shop shop;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public boolean isWrittenBy(Member member) {
    return this.member.getId().equals(member.getId());
  }

  public static Inquiry fromRequest(InquiryRequest inquiryRequest, MemberEntity memberEntity, ShopEntity shopEntity) {
    return Inquiry.builder()
        .title(inquiryRequest.getTitle())
        .content(inquiryRequest.getContent())
        .image(inquiryRequest.getImage())
        .member(Member.fromEntity(memberEntity))
        .shop(Shop.fromEntity(shopEntity))
        .createdAt(LocalDateTime.now())
        .build();
  }

  public static Inquiry fromEntity(InquiryEntity inquiryEntity) {
    return Inquiry.builder()
        .id(inquiryEntity.getId())
        .title(inquiryEntity.getTitle())
        .content(inquiryEntity.getContent())
        .image(inquiryEntity.getImage())
        .member(Member.fromEntity(inquiryEntity.getMember()))
        .shop(Shop.fromEntity(inquiryEntity.getShop()))
        .createdAt(inquiryEntity.getCreatedAt())
        .updatedAt(inquiryEntity.getUpdatedAt())
        .build();
  }

  public InquiryEntity toEntity() {
    return InquiryEntity.builder()
        .title(this.title)
        .content(this.content)
        .image(this.image)
        .member(this.member.toEntity())
        .shop(this.shop.toEntity())
        .createdAt(this.createdAt)
        .updatedAt(this.updatedAt)
        .build();
  }

  public Inquiry update(InquiryUpdateRequest inquiryUpdateRequest) {
    if (inquiryUpdateRequest.getTitle() != null) {
      this.title = inquiryUpdateRequest.getTitle();
    }

    if (inquiryUpdateRequest.getContent() != null) {
      this.content = inquiryUpdateRequest.getContent();
    }

    if (inquiryUpdateRequest.getImage() != null) {
      this.image = inquiryUpdateRequest.getImage();
    }

    this.updatedAt = LocalDateTime.now();
    return this;
  }

}
