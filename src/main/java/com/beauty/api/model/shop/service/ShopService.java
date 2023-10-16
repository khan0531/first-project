package com.beauty.api.model.shop.service;

import com.beauty.api.model.shop.domain.Shop;
import com.beauty.api.model.shop.dto.ShopRequest;
import com.beauty.api.model.shop.dto.ShopResponse;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.shop.persist.repository.ShopRepository;
import com.beauty.api.model.user.domain.AdminMember;
import com.beauty.api.model.user.persist.entity.AdminMemberEntity;
import com.beauty.api.model.user.persist.repository.AdminMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopService {

  private final ShopRepository shopRepository;
  private final AdminMemberRepository adminMemberRepository;

  public ShopResponse getShop(Long id) {
    ShopEntity shopEntity = this.shopRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 샵이 존재하지 않습니다."));

    return ShopResponse.fromEntity(shopEntity);
  }

  public ShopResponse register(AdminMember adminMember, ShopRequest shopRequest) {
    AdminMemberEntity adminMemberEntity = this.adminMemberRepository.findById(shopRequest.getAdminMemberId())
        .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 존재하지 않습니다."));

    if (!shopRequest.getAdminMemberId().equals(adminMember.getId())) {
      throw new IllegalArgumentException("권한이 없습니다.");
    }

    Shop shop = Shop.fromRequest(shopRequest, adminMemberEntity);

    ShopEntity shopEntity = this.shopRepository.save(shop.toEntity(adminMemberEntity));

    return ShopResponse.fromEntity(shopEntity);
  }

}
