package com.beauty.api.model.inquiry.dto;

import com.beauty.api.model.inquiry.persist.entity.InquiryEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InquiryResponse {

  private String title;

  private String content;

  private String image;

  private Long memberId;

  private Long shopId;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public static InquiryResponse fromEntity(InquiryEntity inquiryEntity) {
    return InquiryResponse.builder()
        .title(inquiryEntity.getTitle())
        .content(inquiryEntity.getContent())
        .image(inquiryEntity.getImage())
        .memberId(inquiryEntity.getMember().getId())
        .shopId(inquiryEntity.getShop().getId())
        .createdAt(inquiryEntity.getCreatedAt())
        .updatedAt(inquiryEntity.getUpdatedAt())
        .build();
  }
}
