package com.beauty.api.model.shop.controller;

import com.beauty.api.model.shop.dto.ShopRequest;
import com.beauty.api.model.shop.dto.ShopResponse;
import com.beauty.api.model.shop.service.ShopService;
import com.beauty.api.model.user.domain.AdminMember;
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

  //샵 등록
  @PostMapping
  public ResponseEntity<?> register(@AuthenticationPrincipal AdminMember adminMember,
      @RequestBody ShopRequest shopRequest) {
    ShopResponse result = this.shopService.register(adminMember, shopRequest);
    return ResponseEntity.ok(result);
  }

  //샵 정보 수정
//  @PatchMapping("/{id}")
//  public ResponseEntity<?> updateShop(@AuthenticationPrincipal AdminMember adminMember,
//      @RequestBody ShopUpdateRequest shopUpdateRequest, @PathVariable Long id) {
//    if (!adminMember.getId().equals(id)) {
//      throw new IllegalArgumentException("권한이 없습니다.");
//    }
//
//    ShopResponse result = this.shopService.updateShop(adminMember, shopUpdateRequest);
//    return ResponseEntity.ok(result);
//  }

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


}
