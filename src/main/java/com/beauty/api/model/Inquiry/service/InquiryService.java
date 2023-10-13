package com.beauty.api.model.Inquiry.service;

import com.beauty.api.model.Inquiry.dto.InquiryInput;
import com.beauty.api.model.Inquiry.dto.InquiryResponse;
import com.beauty.api.model.Inquiry.persist.entity.InquiryEntity;
import com.beauty.api.model.Inquiry.persist.repository.InquiryRepository;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.shop.persist.repository.ShopRepository;
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

  public InquiryResponse writeInquiry(InquiryInput inquiryInput) {
    List<InquiryEntity> inquiryEntities = this.inquiryRepository.findByTitleAndContent(
        inquiryInput.getTitle(), inquiryInput.getContent());

    if (inquiryEntities.size() > 0) {
      for (InquiryEntity inquiryEntity : inquiryEntities) {
        if (inquiryEntity.getCreatedAt().isAfter(LocalDateTime.now().minusSeconds(30))) {
          throw new RuntimeException("30초 이내에 같은 제목, 같은 내용의 문의를 작성할 수 없습니다.");
        }
      }
    }

    ShopEntity shopEntity = this.shopRepository.findById(inquiryInput.getShopId())
        .orElseThrow(() -> new RuntimeException("존재하지 않는 가게입니다."));

    MemberEntity memberEntity = this.memberRepository.findById(inquiryInput.getMemberId())
        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

    InquiryEntity inquiryEntity = this.inquiryRepository.save(inquiryInput.toEntity(shopEntity, memberEntity));

    return InquiryResponse.fromEntity(inquiryEntity);
  }
}
