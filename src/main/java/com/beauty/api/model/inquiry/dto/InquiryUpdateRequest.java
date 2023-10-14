package com.beauty.api.model.inquiry.dto;

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
}
