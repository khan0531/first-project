package com.beauty.api.model.inquiry.service;

import com.beauty.api.model.inquiry.domain.Inquiry;
import com.beauty.api.model.inquiry.dto.InquiryRequest;
import com.beauty.api.model.inquiry.dto.InquiryResponse;
import com.beauty.api.model.inquiry.dto.InquiryUpdateRequest;
import com.beauty.api.model.inquiry.persist.entity.InquiryEntity;
import com.beauty.api.model.inquiry.persist.repository.InquiryRepository;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.shop.persist.repository.ShopRepository;
import com.beauty.api.model.user.domain.Member;
import com.beauty.api.model.user.persist.entity.MemberEntity;
import com.beauty.api.model.user.persist.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InquiryService {

  private final InquiryRepository inquiryRepository;
  private final ShopRepository shopRepository;
  private final MemberRepository memberRepository;

  public InquiryResponse writeInquiry(InquiryRequest inquiryRequest) {
    List<InquiryEntity> inquiryEntities = this.inquiryRepository.findByTitleAndContent(
        inquiryRequest.getTitle(), inquiryRequest.getContent());

    if (inquiryEntities.size() > 0) {
      for (InquiryEntity inquiryEntity : inquiryEntities) {
        if (inquiryEntity.getCreatedAt().isAfter(LocalDateTime.now().minusSeconds(30))) {
          throw new RuntimeException("30초 이내에 같은 제목, 같은 내용의 문의를 작성할 수 없습니다.");
        }
      }
    }

    ShopEntity shopEntity = this.shopRepository.findById(inquiryRequest.getShopId())
        .orElseThrow(() -> new RuntimeException("존재하지 않는 가게입니다."));

    MemberEntity memberEntity = this.memberRepository.findById(inquiryRequest.getMemberId())
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

    InquiryEntity inquiryEntity = this.inquiryRepository.save(
        Inquiry.fromRequest(inquiryRequest, memberEntity, shopEntity).toEntity());

    return InquiryResponse.fromEntity(inquiryEntity);
  }

  public InquiryResponse updateInquiry(Member member, InquiryUpdateRequest inquiryUpdateRequest) {
    InquiryEntity inquiryEntity = this.inquiryRepository.findById(inquiryUpdateRequest.getId())
        .orElseThrow(() -> new RuntimeException("존재하지 않는 문의입니다."));

    Inquiry inquiry = Inquiry.fromEntity(inquiryEntity);

    if (!inquiry.isWrittenBy(member)) {
      throw new RuntimeException("해당 문의를 수정할 권한이 없습니다.");
    }

    return InquiryResponse.fromEntity(this.inquiryRepository.save(inquiry.update(inquiryUpdateRequest).toEntity()));
  }

  public InquiryResponse getInquiry(Long id) {
    InquiryEntity inquiryEntity = this.inquiryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 문의입니다."));

    return InquiryResponse.fromEntity(inquiryEntity);
  }
}
