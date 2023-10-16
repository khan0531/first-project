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
  @PatchMapping("/{id}")
  public ResponseEntity<?> updateInquiry(@AuthenticationPrincipal Member member, @PathVariable Long id,
      @RequestBody InquiryUpdateRequest inquiryUpdateRequest) {
    if (!inquiryUpdateRequest.getId().equals(id)) {
      throw new RuntimeException("해당 문의가 존재하지 않습니다.");
    }

    InquiryResponse inquiryResponse = this.inquiryService.updateInquiry(member, inquiryUpdateRequest);
    return ResponseEntity.ok(inquiryResponse);
  }

  //문의 등록
  @PostMapping
  public ResponseEntity<?> writeInquiry(@AuthenticationPrincipal Member member,
      @RequestBody InquiryRequest inquiryRequest) {
    if (!inquiryRequest.getMemberId().equals(member.getId())) {
      throw new RuntimeException("해당 문의를 작성할 권한이 없습니다.");
    }
    InquiryResponse inquiryResponse = this.inquiryService.writeInquiry(inquiryRequest);
    return ResponseEntity.ok(inquiryResponse);
  }

}
