package com.beauty.api.model.shop.service;

import com.beauty.api.model.shop.dto.ShopResponse;
import com.beauty.api.model.shop.persist.entity.ShopEntity;
import com.beauty.api.model.shop.persist.repository.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopService {

  private final ShopRepository shopRepository;

  public ShopResponse getShop(Long id) {
    ShopEntity shopEntity = this.shopRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 샵이 존재하지 않습니다."));

    return ShopResponse.fromEntity(shopEntity);
  }


}
