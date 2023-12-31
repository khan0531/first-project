package com.beauty.api.model.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpdateRequest {

  private Long id;
  private String title;
  private String content;
  private String image;
  private Long rating;
}
