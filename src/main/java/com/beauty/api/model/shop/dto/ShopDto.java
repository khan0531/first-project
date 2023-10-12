package com.beauty.api.model.shop.dto;

import com.beauty.api.model.shop.dto.constants.CosmeticType;
import java.util.Set;

public interface ShopDto {

  Long getId();

  String getName();

  double getLatitude();

  double getLongitude();

  double getDistance();

  Long getReviewCount();

  double getRating();

  Set<CosmeticType> getCosmeticTypes();
}
