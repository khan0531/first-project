package com.beauty.api.model.Inquiry.service;

import com.beauty.api.model.Inquiry.persist.repository.InquiryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InquiryService {

  private final InquiryRepository inquiryRepository;
}
