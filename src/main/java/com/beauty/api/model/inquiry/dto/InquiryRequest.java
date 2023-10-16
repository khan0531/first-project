package com.beauty.api.model.inquiry.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InquiryRequest {

  @NotBlank(message = "상점 아이디를 입력해주세요.")
  private Long shopId;

  @NotBlank(message = "회원 아이디를 입력해주세요.")
  private Long memberId;

  @NotBlank(message = "제목을 입력해주세요.")
  private String title;

  @NotBlank(message = "내용을 입력해주세요.")
  private String content;

  private String image;
}
