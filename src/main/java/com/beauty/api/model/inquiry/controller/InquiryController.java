package com.beauty.api.model.inquiry.controller;

import com.beauty.api.model.inquiry.dto.InquiryRequest;
import com.beauty.api.model.inquiry.dto.InquiryResponse;
import com.beauty.api.model.inquiry.dto.InquiryUpdateRequest;
import com.beauty.api.model.inquiry.service.InquiryService;
import com.beauty.api.model.user.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  //내 문의 수정
  @PatchMapping("/inquiry/{inquiryId}")
  public ResponseEntity<?> updateInquiry(@AuthenticationPrincipal Member member, @PathVariable Long inquiryId,
      @RequestBody InquiryUpdateRequest inquiryUpdateRequest) {
    InquiryResponse inquiryResponse = this.inquiryService.updateInquiry(member, inquiryUpdateRequest);
    return ResponseEntity.ok(inquiryResponse);
  }

  //문의 등록
  @PostMapping("/inquiry")
  public ResponseEntity<?> writeInquiry(@AuthenticationPrincipal Member member,
      @RequestBody InquiryRequest inquiryRequest) {

    InquiryResponse inquiryResponse = this.inquiryService.writeInquiry(inquiryRequest);
    return ResponseEntity.ok(inquiryResponse);
  }

}
