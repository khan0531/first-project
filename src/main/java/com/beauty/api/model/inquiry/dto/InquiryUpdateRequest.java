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
public class InquiryUpdateRequest {

  private Long id;
  private String title;
  private String content;
  private String image;

  public InquiryEntity toEntity() {
    return InquiryEntity.builder()
        .title(this.title)
        .content(this.content)
        .image(this.image)
        .createdAt(LocalDateTime.now())
        .build();
  }
}
