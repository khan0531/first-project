package com.beauty.api.model.shop.service;

import com.beauty.api.model.shop.domain.Shop;
import com.beauty.api.model.shop.dto.ShopRequest;
import com.beauty.api.model.shop.dto.ShopResponse;
import com.beauty.api.model.shop.dto.ShopUpdateRequest;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.shop.persist.repository.ShopRepository;
import com.beauty.api.model.user.domain.AdminMember;
import com.beauty.api.model.user.persist.entity.AdminMemberEntity;
import com.beauty.api.model.user.persist.repository.AdminMemberRepository;
import com.beauty.api.utilities.geocoding.GeocodingUtils;
import com.beauty.api.utilities.geocoding.dto.LatLng;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopService {

  private final GeocodingUtils geocodingUtils;
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
    LatLng latLng = geocodingUtils.getLatLng(shop.getAddress());
    shop.setLatLng(latLng);

    ShopEntity shopEntity = this.shopRepository.save(shop.toEntity(adminMemberEntity));

    return ShopResponse.fromEntity(shopEntity);
  }

  public ShopResponse updateShop(AdminMember adminMember, ShopUpdateRequest shopUpdateRequest) {
    Shop shop = this.shopRepository.findById(shopUpdateRequest.getId())
        .map(Shop::fromEntity)
        .map(s -> {
          if (!s.getAdminMember().getId().equals(adminMember.getId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
          }

          return s;
        })
        .map(s -> s.updateShop(shopUpdateRequest))
        .orElseThrow(() -> new IllegalArgumentException("해당 샵이 존재하지 않습니다."));

    AdminMemberEntity adminMemberEntity = this.adminMemberRepository.findById(shop.getAdminMember().getId())
        .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 존재하지 않습니다."));

    return ShopResponse.fromEntity(this.shopRepository.save(shop.toEntity(adminMemberEntity)));
  }
}
