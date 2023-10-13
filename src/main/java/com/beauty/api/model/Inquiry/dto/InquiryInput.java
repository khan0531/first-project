package com.beauty.api.model.Inquiry.dto;

import com.beauty.api.model.Inquiry.persist.entity.InquiryEntity;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
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
public class InquiryInput {

  private Long shopId;

  private Long memberId;

  private String title;

  private String content;

  private String image;

  public InquiryEntity toEntity(ShopEntity shopEntity, MemberEntity memberEntity) {
    return InquiryEntity.builder()
        .title(this.title)
        .content(this.content)
        .image(this.image)
        .member(memberEntity)
        .shop(shopEntity)
        .createdAt(LocalDateTime.now())
        .build();
  }
}
