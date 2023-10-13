package com.beauty.api.model.shop.controller;

import com.beauty.api.model.inquiry.dto.InquiryInput;
import com.beauty.api.model.inquiry.dto.InquiryResponse;
import com.beauty.api.model.inquiry.service.InquiryService;
import com.beauty.api.model.review.service.ReviewService;
import com.beauty.api.model.shop.service.ShopService;
import com.beauty.api.model.user.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

  private final ShopService shopService;
  private final InquiryService inquiryService;
  private final ReviewService reviewService;

  //샵 리스트 조회
  @GetMapping
  public ResponseEntity<?> getShopList(@RequestParam String name, @RequestParam String cosmeticType,
      @RequestParam Long reviewCount, @RequestParam Double rating, Pageable pageable) {
    return null;
  }

  //샵 상세 조회
  @GetMapping("/{id}")
  public ResponseEntity<?> getShop(@PathVariable Long id) {
    var result = this.shopService.getShop(id);
    return ResponseEntity.ok(result);
  }

  //샵 리뷰 조회
  @GetMapping("/{id}/review")
  public ResponseEntity<?> getShopReviewList(@PathVariable Long id) {
    return null;
  }

  //문의 등록
  @PostMapping("/{id}/inquiry")
  public ResponseEntity<?> writeInquiry(@AuthenticationPrincipal Member member, @PathVariable Long id,
      @RequestBody InquiryInput inquiryInput) {

    InquiryResponse inquiryResponse = this.inquiryService.writeInquiry(inquiryInput);
    return ResponseEntity.ok(inquiryResponse);
  }


}
