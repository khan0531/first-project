package com.beauty.api.model.Inquiry.controller;

import com.beauty.api.model.Inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<?> getInquiry(@PathVariable String id) {
    return null;
  }

}
