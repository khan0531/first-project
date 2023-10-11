package com.beauty.api.model.shop.controller;

import com.beauty.api.model.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

  private final ShopService shopService;

  //샵 리스트 조회
  @GetMapping
  public ResponseEntity<?> getShopList(@RequestParam String name, @RequestParam String cosmeticType,
      @RequestParam Long reviewCount, @RequestParam Double rating, Pageable pageable) {
    return null;
  }

  //샵 상세 조회
  @GetMapping("/{id}")
  public ResponseEntity<?> getShop(@PathVariable String id) {
    return null;
  }

  //샵 리뷰 조회
  @GetMapping("/{id}/review")
  public ResponseEntity<?> getShopReview(@PathVariable String id) {
    return null;
  }

  //문의 등록
  @PostMapping("/{id}/inquiry")
  public ResponseEntity<?> writeInquiry(@PathVariable String id) {
    return null;
  }


}
