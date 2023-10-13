package com.beauty.api.model.inquiry.controller;

import com.beauty.api.model.inquiry.dto.InquiryResponse;
import com.beauty.api.model.inquiry.service.InquiryService;
import com.beauty.api.model.user.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

  private final InquiryService inquiryService;

  //문의 상세 보기
  @GetMapping("/{id}")
  public ResponseEntity<?> getInquiry(@AuthenticationPrincipal Member member, @PathVariable Long id) {
    InquiryResponse inquiryResponse = this.inquiryService.getInquiry(id);
    return ResponseEntity.ok(inquiryResponse);
  }

}
