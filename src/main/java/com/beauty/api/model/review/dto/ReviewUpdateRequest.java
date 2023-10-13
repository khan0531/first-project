package com.beauty.api.model.review.dto;

import com.beauty.api.model.review.persist.entity.ReviewEntity;
import java.time.LocalDateTime;
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

  public ReviewEntity toEntity() {
    return ReviewEntity.builder()
        .title(this.title)
        .content(this.content)
        .image(this.image)
        .rating(this.rating)
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
